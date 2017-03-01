/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsBolt.java 
 * Package Name:com.asianinfo.log.statistics 
 * Date:2015年9月17日下午3:39:18 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.statistics.bolt;  

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.common.util.SpringContext;
import com.asiainfo.integration.o2p.log.dao.StatisticsStoreDAO;

/** 
 * ClassName:StatisticsBolt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月17日 下午3:39:18 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class UseCountBolt extends  BaseRichBolt{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    private  static final Logger LOG =  LoggerFactory.getLogger(UseCountBolt.class);
    
    private transient OutputCollector collector;
    
    
    private transient Map<String,UseStatCntSec> secCache;
    private transient Map<String,UseStatCntRecent> otherCache;
    
    private transient StatisticsStoreDAO statisticsStoreDAO;
    private transient long intervalTime;
    private transient long s1 = 0l;

    @Override
    public void prepare(Map conf, TopologyContext context,
            OutputCollector collector) {
        try {
            LogUtils.setZkAddrEntrance(conf);
            this.collector = collector;
            if(statisticsStoreDAO==null){
            	this.statisticsStoreDAO = (StatisticsStoreDAO) SpringContext.getBeansContext()
                    .getBean("statisticsStoreDao");
            }
            this.intervalTime = Long.parseLong(Properties.getInstance().getInternalTime());
            if(LOG.isInfoEnabled()){
                LOG.info("useCount bolt init finished!");
            }
        } catch (Exception e) {
            LOG.error("useCount bolt init fail,cause by",e);
        }
    }

    @Override
    public void execute(Tuple tuple) {
        try {
            if(this.secCache == null){
                s1 = System.currentTimeMillis();
                this.secCache = new HashMap<String,UseStatCntSec>();
            }
            if(this.otherCache == null){
                this.otherCache = new HashMap<String,UseStatCntRecent>();
            }
            if(tuple.size()>1){
                UseStatCntSec useSec = (UseStatCntSec) tuple.getValueByField("useSec");
                UseStatCntRecent useMin = (UseStatCntRecent) tuple.getValueByField("useMin");
                UseStatCntRecent useHour = (UseStatCntRecent) tuple.getValueByField("useHour");
                UseStatCntRecent useDay = (UseStatCntRecent) tuple.getValueByField("useDay");
                if(secCache.get(useSec.getDataSourceKey()) == null){
                    secCache.put(useSec.getDataSourceKey(), useSec);
                }else{
                    updateCacheValueWithSec(useSec);
                }
                if(otherCache.get(useMin.getDataSourceKey()) == null){
                    otherCache.put(useMin.getDataSourceKey(), useMin);
                }else{
                    updateCacheValueWithOther(useMin);
                }
                if(otherCache.get(useHour.getDataSourceKey()) == null){
                    otherCache.put(useHour.getDataSourceKey(), useHour);
                }else{
                    updateCacheValueWithOther(useHour);
                }
                if(otherCache.get(useDay.getDataSourceKey()) == null){
                    otherCache.put(useDay.getDataSourceKey(), useDay);
                }else{
                    updateCacheValueWithOther(useDay);
                }
                if(System.currentTimeMillis()-s1>=this.intervalTime){
                    //flushCommit();
                    emitPartCountResult();
                    s1 = System.currentTimeMillis();
                }
            }else{
                if("refresh".equals(tuple.getString(0))){
                    //flushCommit();
                    emitPartCountResult();
                }
            }
            this.collector.ack(tuple);
        } catch (Exception e) {
            LOG.error("useCount bolt err when summary data,cause by:",e);
            //this.collector.fail(tuple);
        }
       
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("useCountValue"));
    }
    
    public void updateCacheValueWithSec(UseStatCntSec useSec){
        UseStatCntSec cacheValue = (UseStatCntSec) secCache.get(useSec.getDataSourceKey());
        int totalTrans = cacheValue.getTotalTrans();
        int qos = cacheValue.getQos();
        int totalBizErrs = cacheValue.getTotalBizErr();
        int totalSysErrs = cacheValue.getTotalSysErr();
        cacheValue.setTotalTrans(totalTrans+1);
        cacheValue.setTotalBizErr(totalBizErrs+useSec.getTotalBizErr());
        cacheValue.setTotalSysErr(totalSysErrs+useSec.getTotalSysErr());
        cacheValue.setQos((qos*totalTrans+useSec.getQos())/(totalTrans+1));
        secCache.put(useSec.getDataSourceKey(), cacheValue);
        
    }
    public void updateCacheValueWithOther(UseStatCntRecent useOther){
        UseStatCntRecent cacheValue = (UseStatCntRecent) otherCache.get(useOther.getDataSourceKey());
        int totalTrans = cacheValue.getTotalTrans();
        int qos = cacheValue.getQos();
        int totalBizErrs = cacheValue.getTotalBizErr();
        int totalSysErrs = cacheValue.getTotalSysErr();
        cacheValue.setTotalTrans(totalTrans+1);
        cacheValue.setTotalBizErr(totalBizErrs+useOther.getTotalBizErr());
        cacheValue.setTotalSysErr(totalSysErrs+useOther.getTotalSysErr());
        cacheValue.setQos((qos*totalTrans+useOther.getQos())/(totalTrans+1));
        otherCache.put(useOther.getDataSourceKey(), cacheValue);
        
    }
    public void emitPartCountResult(){
        if(!secCache.isEmpty()){
            // 屏蔽秒表
/*            this.statisticsStoreDAO.saveOrUpdateRegSec(LogUtils.MapToList(secCache));
            if(LOG.isDebugEnabled()){
                LOG.debug("write reg sec stat entry size:"+secCache.size());   
            }*/
            secCache.clear(); 
        }
        if(!otherCache.isEmpty()){
            Map<String,UseStatCntRecent> cloneObj = new HashMap<String,UseStatCntRecent>();
            cloneObj.putAll(otherCache);
            this.collector.emit(new Values(cloneObj));
            LOG.info("send sub use count result to persisit bolt:"+cloneObj.size());   
            otherCache.clear();
        }
    }
    
    public void flushCommit(){
        if(!secCache.isEmpty()){
            //屏蔽秒表
/*            this.statisticsStoreDAO.saveOrUpdateUseSec(LogUtils.MapToList(secCache));
            if(LOG.isDebugEnabled()){
                LOG.debug("write use sec stat entry size:"+secCache.size());
            }*/
            secCache.clear();
        }
        if(!otherCache.isEmpty()){
            this.statisticsStoreDAO.saveOrUpdateUseOther(LogUtils.mapToList(otherCache));
            LOG.info("write use other stat entry size:"+otherCache.size());
            otherCache.clear();
        }
    }
    

}