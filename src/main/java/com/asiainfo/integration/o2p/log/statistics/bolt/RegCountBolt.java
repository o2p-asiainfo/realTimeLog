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
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
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
public class RegCountBolt extends  BaseRichBolt{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    private  static final Logger LOG =  LoggerFactory.getLogger(RegCountBolt.class);
    
    private transient OutputCollector collector;
    
    
    private transient Map<String,RegStatSec> secCache ;
    
    private transient Map<String,RegStatRecent> otherCache ;
    
    private transient StatisticsStoreDAO statisticsStoreDAO;
    
    private transient long intervalTime;
    
    private transient long s1 = 0l;
    
    @Override
    public void prepare(Map conf, TopologyContext context,
            OutputCollector collector) {
        try{
            LogUtils.setZkAddrEntrance(conf);
            this.collector = collector;
            if(statisticsStoreDAO==null){
            	this.statisticsStoreDAO = (StatisticsStoreDAO) SpringContext.getBeansContext().getBean("statisticsStoreDao");
            }
            this.intervalTime = Long.parseLong( Properties.getInstance().getInternalTime());
            if(LOG.isInfoEnabled()){
                LOG.info("regCount bolt init finished!");
            }
        }catch(Exception e){
            LOG.error("regCount bolt init fail,cause by:",e);
        }
    }

    @Override
    public void execute(Tuple tuple) {
        
        try {
            if(this.secCache == null){
                s1 = System.currentTimeMillis();
                this.secCache = new HashMap<String,RegStatSec>();
            }
            if(this.otherCache == null){
                this.otherCache = new HashMap<String,RegStatRecent>();
            }
            if(tuple.size()>1){
                RegStatSec regSec = (RegStatSec) tuple.getValueByField("regSec");
                RegStatRecent regMin =  (RegStatRecent) tuple.getValueByField("regMin");
                RegStatRecent regHour = (RegStatRecent) tuple.getValueByField("regHour");
                RegStatRecent regDay =  (RegStatRecent) tuple.getValueByField("regDay");
                
                if(secCache.get(regSec.getDataSourceKey()) == null){
                    secCache.put(regSec.getDataSourceKey(), regSec);
                }else{
                    updateCacheValueWithSec(regSec);
                }
                if(otherCache.get(regMin.getDataSourceKey()) == null){
                    otherCache.put(regMin.getDataSourceKey(), regMin);
                }else{
                    updateCacheValueWithOther(regMin);
                }
                if(otherCache.get(regHour.getDataSourceKey()) == null){
                    otherCache.put(regHour.getDataSourceKey(), regHour);
                }else{
                    updateCacheValueWithOther(regHour);
                }
                if(otherCache.get(regDay.getDataSourceKey()) == null){
                    otherCache.put(regDay.getDataSourceKey(), regDay);
                }else{
                    updateCacheValueWithOther(regDay);
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
            LOG.error("regCount bolt err when summary data,cause by:",e);
            //this.collector.fail(tuple);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("regCountValue"));
    }
    
    public void updateCacheValueWithSec(RegStatSec regSec){
        RegStatSec cacheValue = (RegStatSec) secCache.get(regSec.getDataSourceKey());
        int totalTrans = cacheValue.getTotalTrans();
        long qos = cacheValue.getQos();
        int avgUsing = cacheValue.getAvgUsing();
        int totalBizErrs = cacheValue.getTotalBizErr();
        int totalSysErrs = cacheValue.getTotalSysErr();
        cacheValue.setTotalTrans(totalTrans+1);
        cacheValue.setTotalBizErr(totalBizErrs+regSec.getTotalBizErr());
        cacheValue.setTotalSysErr(totalSysErrs+regSec.getTotalSysErr());
        cacheValue.setQos((qos*totalTrans+regSec.getQos())/(totalTrans+1));
        cacheValue.setAvgUsing((avgUsing*totalTrans+regSec.getAvgUsing())/(totalTrans+1));
        secCache.put(regSec.getDataSourceKey(), cacheValue);
    }
    public void updateCacheValueWithOther(RegStatRecent regOther){
        RegStatRecent cacheValue = (RegStatRecent) otherCache.get(regOther.getDataSourceKey());
        int totalTrans = cacheValue.getTotalTrans();
        long avgDst = cacheValue.getAvgUsingDst();
        int  avgUsing = cacheValue.getAvgUsing();
        int totalBizErrs = cacheValue.getTotalBizErr();
        int totalSysErrs = cacheValue.getTotalSysErr();
        cacheValue.setTotalBizErr(totalBizErrs+regOther.getTotalBizErr());
        cacheValue.setTotalSysErr(totalSysErrs+regOther.getTotalSysErr());
        cacheValue.setTotalTrans(totalTrans+1);
        cacheValue.setAvgUsing((avgUsing*totalTrans+regOther.getAvgUsing())/(totalTrans+1));
        cacheValue.setAvgUsingDst((avgDst*totalTrans+regOther.getAvgUsingDst())/(totalTrans+1));
        otherCache.put(regOther.getDataSourceKey(), cacheValue);
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
            Map<String,RegStatRecent> cloneObj = new HashMap<String,RegStatRecent>();
            cloneObj.putAll(otherCache);
            this.collector.emit(new Values(cloneObj));
            LOG.info("send sub reg count result to persisit bolt:"+cloneObj.size());   
            otherCache.clear();
        }
    }
    
    public void flushCommit(){
        if(!secCache.isEmpty()){
            // 屏蔽秒表
/*            this.statisticsStoreDAO.saveOrUpdateRegSec(LogUtils.MapToList(secCache));
            if(LOG.isDebugEnabled()){
                LOG.debug("write reg sec stat entry size:"+secCache.size());   
            }*/
            secCache.clear(); 
        }
        if(!otherCache.isEmpty()){
            this.statisticsStoreDAO.saveOrUpdateRegOther(LogUtils.mapToList(otherCache));
            LOG.info("write reg other stat entry size:"+otherCache.size());   
            otherCache.clear();
        }
    }
}
