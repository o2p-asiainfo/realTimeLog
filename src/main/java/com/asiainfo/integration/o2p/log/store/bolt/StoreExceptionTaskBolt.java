package com.asiainfo.integration.o2p.log.store.bolt;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ailk.eaap.o2p.common.cache.ICacheFactory;
import com.ailk.eaap.o2p.common.security.SecurityUtil;
import com.ailk.eaap.op2.bo.ExceptionDealInfo;
import com.ailk.eaap.op2.bo.JdbcDataSource;
import com.asiainfo.integration.o2p.log.common.util.CacheUtils;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.common.util.SpringContext;
import com.asiainfo.integration.o2p.log.dao.ExceptionTaskDao;
import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.asiainfo.integration.o2p.log.service.IMessageLogTimeZoneService;
import com.esotericsoftware.minlog.Log;

public class StoreExceptionTaskBolt extends BaseRichBolt{

	 private static final long serialVersionUID = 1L;
	    
	    public final static Logger LOG = LoggerFactory.getLogger(StoreExceptionTaskBolt.class); 
	    
	    private transient OutputCollector collector;
	    
	    private transient IMessageLogTimeZoneService timeZoneService; 
	    private ExceptionTaskDao exceptionTaskDao;

	    private transient List<Tuple> tuples;
	    private transient int maxBatchSize  = 200;
		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			// TODO Auto-generated method stub
	        try{
	            LogUtils.setZkAddrEntrance(stormConf);
	            this.collector = collector;
	            this.maxBatchSize = Integer.valueOf(Properties.getInstance().getBatchNum());
	            if(timeZoneService==null){
	            	this.timeZoneService = (IMessageLogTimeZoneService) SpringContext.getBeansContext().getBean("messageLogTimeZoneService");
	            	this.exceptionTaskDao = (ExceptionTaskDao)SpringContext.getBeansContext().getBean("exceptionTaskDao");
	            }
	            
	            if(LOG.isInfoEnabled()){
	                LOG.info("storeexceptiontask bolt init finish!");
	            }

	        }catch(Exception e){
	            LOG.error("storeexceptiontask bolt init fail! cause by:",e);
	        }
		}
		@Override
		public void execute(Tuple input) {
			// TODO Auto-generated method stub
			try{
	            if(tuples == null){
	                tuples = new ArrayList<Tuple>();
	            }
	            if(!"refresh".equals(input.getValue(0))){
	                tuples.add(input);
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
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			declarer.declare(new Fields("statexceptiontask"));
		}
		
		
		@SuppressWarnings("unchecked")
		public void processTuples(){
	        List<ExceptionDealInfo> eds = new ArrayList<ExceptionDealInfo>();
	        for(Tuple tpl:tuples){
	        	ExceptionDealInfo log =  (ExceptionDealInfo) tpl.getValue(0);
	        	eds.add(log);
	        	
	        }
	        long t1 = System.currentTimeMillis();
	        
	        exceptionTaskDao.saveExceptionDealInfo(eds);
	        
	        long t2 = System.currentTimeMillis();
	        LOG.info("insert logs success:"+eds.size()+"  using time:"+(t2-t1));
	        for(Tuple tpl:tuples){
	        	ExceptionDealInfo log =  (ExceptionDealInfo) tpl.getValue(0);
	            this.collector.emit(new Values(log));
	            this.collector.ack(tpl);
	        } 
	        tuples.clear();
	    }
}
