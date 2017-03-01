/** 
 * Project Name:realTimeLog 
 * File Name:StoreStatisticsBolt.java 
 * Package Name:com.asiainfo.integration.o2p.log.statistics.bolt 
 * Date:2015年12月9日下午5:05:50 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.statistics.bolt;  

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.common.util.SpringContext;
import com.asiainfo.integration.o2p.log.dao.StatisticsStoreDAO;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

/** 
 * ClassName:StoreStatisticsBolt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年12月9日 下午5:05:50 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StoreStatisticsOfUseBolt extends BaseRichBolt{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 335370554226596829L;
    private  static final Logger LOG =  LoggerFactory.getLogger(StoreStatisticsOfUseBolt.class);
    private transient OutputCollector collector;
    private transient StatisticsStoreDAO statisticsStoreDAO;
    private transient Map<String,UseStatCntRecent> otherCache;
    private transient long intervalTime;
    private transient long s1 = 0l;
    

    @Override
    public void prepare(Map conf, TopologyContext context,
            OutputCollector collector) {
        try {
            LogUtils.setZkAddrEntrance(conf);
            this.collector = collector;
            if(statisticsStoreDAO==null){
            	this.statisticsStoreDAO = (StatisticsStoreDAO) SpringContext.getBeansContext().getBean("statisticsStoreDao");
            }
            this.intervalTime = Long.parseLong(Properties.getInstance().getInternalTime());
            if(LOG.isInfoEnabled()){
                LOG.info("usePersistentCount bolt init finished!");
            }
        } catch (Exception e) {
            LOG.error("usePersistentCount bolt init fail,cause by",e);
        }
    }

    @Override
    public void execute(Tuple tuple) {
        
        try{
            if(!"refresh".equals(tuple.getValue(0))){
                Map<String, UseStatCntRecent> tmpCache = (Map<String, UseStatCntRecent>) tuple.getValue(0);
                if(otherCache == null){
                    otherCache =  tmpCache;
                    s1 = System.currentTimeMillis();
                }else{
                    
                    Set<Map.Entry<String, UseStatCntRecent>> entrys = tmpCache.entrySet();
                    for(Map.Entry<String, UseStatCntRecent> entry:entrys){
                        UseStatCntRecent cacheValue= otherCache.get(entry.getKey());
                        if(cacheValue != null){
                            int totalTrans = cacheValue.getTotalTrans();
                            int qos = cacheValue.getQos();
                            int totalBizErrs = cacheValue.getTotalBizErr();
                            int totalSysErrs = cacheValue.getTotalSysErr();
                            cacheValue.setTotalTrans(totalTrans+entry.getValue().getTotalTrans());
                            cacheValue.setTotalBizErr(totalBizErrs+entry.getValue().getTotalBizErr());
                            cacheValue.setTotalSysErr(totalSysErrs+entry.getValue().getTotalSysErr());
                            cacheValue.setQos((qos*totalTrans+entry.getValue().getQos())/(totalTrans+entry.getValue().getTotalTrans()));
                        }else{
                          otherCache.put(entry.getValue().getDataSourceKey(), entry.getValue());
                        }
                    }
                }
                
                if(System.currentTimeMillis()-s1>=this.intervalTime){
                    long t1 = System.currentTimeMillis();
                     this.statisticsStoreDAO.saveOrUpdateUseOther(LogUtils.mapToList(otherCache));
                     LOG.info("push use count data to db size:"+otherCache.size()
                             +"  using time:"+(System.currentTimeMillis()-t1));
                     otherCache.clear();
                     s1 = System.currentTimeMillis();
                }
               
                this.collector.ack(tuple);
            }else{
                //清理残余对象
                if(otherCache != null && !otherCache.isEmpty()){
                    long t1 = System.currentTimeMillis();
                    this.statisticsStoreDAO.saveOrUpdateUseOther(LogUtils.mapToList(otherCache));
                    LOG.info("push reg count data to db size:"+otherCache.size()
                            +"  using time:"+(System.currentTimeMillis()-t1));
                    otherCache.clear();
                    s1 = System.currentTimeMillis();
                }
            }
        }catch(Exception e){
            LOG.error("push stat data to db err,cause by:",e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("useEndCountValue"));
    }

}
