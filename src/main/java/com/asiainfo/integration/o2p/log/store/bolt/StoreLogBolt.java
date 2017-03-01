/** 
 * Project Name:stormDemo 
 * File Name:SaveMysqlBolt.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月22日下午12:27:32 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.store.bolt;  

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.asiainfo.integration.o2p.log.common.util.CacheUtils;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.common.util.SpringContext;
import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.asiainfo.integration.o2p.log.service.IMessageLogTimeZoneService;
import com.ailk.eaap.o2p.common.cache.ICacheFactory;
import com.ailk.eaap.o2p.common.security.SecurityUtil;
import com.ailk.eaap.op2.bo.JdbcDataSource;
import com.ailk.eaap.op2.bo.LogMessageObject;
import com.esotericsoftware.minlog.Log;

/** 
 * ClassName:SaveMysqlBolt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月22日 下午12:27:32 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StoreLogBolt extends BaseRichBolt {

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    
    public final static Logger LOG = LoggerFactory.getLogger(StoreLogBolt.class); 
    
    private transient OutputCollector collector;
    
    private transient LogStoreDAO logStoreDao;
    private transient IMessageLogTimeZoneService timeZoneService; 
    
    private transient HConnection connection;
    
    
    private transient List<Tuple> tuples;
    private transient int maxBatchSize  = 200;
    
    
    public void execute(Tuple tuple) {
        try{
            if(tuples == null){
                tuples = new ArrayList<Tuple>();
            }
            if(!"refresh".equals(tuple.getValue(0))){
                tuples.add(tuple);
                if(tuples.size() == this.maxBatchSize){
                    processTuples();
                }
            }else{
                if(tuples.size()>0){
                    processTuples();
                }   
            }
        }catch(Exception e){
            LOG.error("batch insert logs error,cause by:", e);
            for(Tuple tpl : tuples){
                this.collector.fail(tpl);
            }
            tuples.clear();
        }
    }

    
    @Override
    public void cleanup() {
    }
    
    
    public void prepare(Map conf, TopologyContext arg1, OutputCollector collector) {
        try{
            LogUtils.setZkAddrEntrance(conf);
            this.collector = collector;
            this.maxBatchSize = Integer.valueOf(Properties.getInstance().getBatchNum());
            if(timeZoneService==null){
            	this.timeZoneService = (IMessageLogTimeZoneService) SpringContext.getBeansContext().getBean("messageLogTimeZoneService");
            }
            if(!"rmdb".equals(Properties.getInstance().getDatabaseType())){
//                Configuration hbaseConf = HBaseConfiguration.create();
                Configuration hbaseConf = (Configuration) SpringContext.getBeansContext().getBean("configuration");
                connection = HConnectionManager.createConnection(hbaseConf);
            }
            if(LOG.isInfoEnabled()){
                LOG.info("storelog bolt init finish!");
            }

        }catch(Exception e){
            LOG.error("storelog bolt init fail! cause by:",e);
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declare) {
        declare.declare(new Fields("statlog"));
        //if("rmdb".equals(Properties.getInstance().getDatabaseType())){
            //ci,ei,ori
            //declare.declareStream("ci",new Fields("ci"));
            //declare.declareStream("ei",new Fields("ei"));
        //}
    }
    @SuppressWarnings("unchecked")
	public void processTuples(){
        List<LogMessageObject> logs = new ArrayList<LogMessageObject>();
        for(Tuple tpl:tuples){
            LogMessageObject log =  (LogMessageObject) tpl.getValue(0);
            if(log.getContractInteractionList() != null && !log.getContractInteractionList().isEmpty()) {
            	String timeZone = log.getContractInteractionList().get(0).getTimeZone();
            	timeZoneService.convertLogTimeZone(log, timeZone);
            }
            logs.add(log);
        }
        long t1 = System.currentTimeMillis();
        if("rmdb".equals(Properties.getInstance().getDatabaseType())){
        	ICacheFactory<String, Object> cacheFactory = (ICacheFactory<String, Object>) SpringContext.getBeansContext().getBean("cacheFactory");
        	Map<String, Object> map = CacheUtils.getAllDataSource(cacheFactory.getCacheClient());
        	if(map==null || map.isEmpty()){
        		LOG.warn("There is no datasource in cache?");
        		return;
        	}
        	List<JdbcDataSource> allDataSourceList = (List<JdbcDataSource>)map.get(CacheUtils.ALL_DATASOURCE_LIST);
    		Map<String, Object> allDatasourceMap = (Map<String, Object>)map.get(CacheUtils.ALL_DATASOURCE_MAP);
        	if(allDataSourceList.isEmpty()){
        		Log.warn("There is no datasource config data in cache for any tenant, please check the tennat or datasource config data in db!");
        		return;
        	}
            //set datasourcekey and table subfix
        	Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
        	Map<String, List<LogMessageObject>> logsMap = new HashMap<String, List<LogMessageObject>>();
        	for(JdbcDataSource jds : allDataSourceList){
        		logsMap.put(jds.getTenantId().toString() + String.valueOf(jds.getDataSourceId()), new ArrayList<LogMessageObject>());
        	}
	   		LogUtils.setMultiDatasource(logs, allDatasourceMap, logsMap, currTimestamp);        	
        	//batch insert into db by datasource
        	for(JdbcDataSource jds : allDataSourceList){
        		List<LogMessageObject> lmos = logsMap.get(jds.getTenantId().toString() + String.valueOf(jds.getDataSourceId()));
        		if(lmos.isEmpty()){
        			continue;
        		}
        		jds.setPassword(SecurityUtil.getInstance().decryMsg(jds.getPassword()));
        		logStoreDao = (LogStoreDAO) LogUtils.getDaoObject("logStoreDao", jds);
        		this.logStoreDao.batchInsert(lmos);
        		LOG.info("batch insert logs finished!");
        	}
        }else{
            this.logStoreDao.putHbase(logs, connection);
        }
        long t2 = System.currentTimeMillis();
        LOG.info("insert logs success:"+logs.size()+"  using time:"+(t2-t1));
        for(Tuple tpl:tuples){
            LogMessageObject log =  (LogMessageObject) tpl.getValue(0);
            this.collector.emit(new Values(log));
            this.collector.ack(tpl);
        } 
        tuples.clear();
    }
    
    
}
