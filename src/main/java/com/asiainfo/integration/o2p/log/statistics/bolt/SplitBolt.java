/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsBolt.java 
 * Package Name:com.asianinfo.log.statistics 
 * Date:2015年9月17日下午3:39:18 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.statistics.bolt;  

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.utils.StatisticsHelper;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.CtgLogs;
import com.ailk.eaap.op2.bo.ExceptionLogs;
import com.ailk.eaap.op2.bo.LogMessageObject;

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
public class SplitBolt extends  BaseRichBolt{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    public  static final Logger LOG =  LoggerFactory.getLogger(SplitBolt.class);
    
    private transient OutputCollector collector;
    
    private transient int count;
    
    @Override
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        this.collector = collector;
        if(LOG.isInfoEnabled()){
            LOG.info("split bolt init finished!");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(Tuple tuple) {
        
        try{
            LogMessageObject log = (LogMessageObject) tuple.getValue(0);
            ContractInteraction ci = (ContractInteraction) log.getContractInteractionList().get(0);
            StatisticsHelper.validCINotNullColunm(ci);
            List<ExceptionLogs> exps = log.getExceptionLogsList();
            List<CtgLogs> ctgs = log.getCtgLogsList();
            int  expNum = CollectionUtils.isEmpty(exps)?0:1;
            int ctgNum = CollectionUtils.isEmpty(ctgs)?0:1;
            UseStatCntSec useSec = StatisticsHelper.generateUseStatCntSec(ci, StatisticsHelper.SECOND_TIME_TYPE,expNum,ctgNum);
            UseStatCntRecent useMin = StatisticsHelper.generateUseStatCntRecent(ci, StatisticsHelper.MINUTE_TIME_TYPE,expNum,ctgNum);
            UseStatCntRecent useHour = StatisticsHelper.generateUseStatCntRecent(ci, StatisticsHelper.HOUR_TIME_TYPE,expNum,ctgNum);
            UseStatCntRecent useDay = StatisticsHelper.generateUseStatCntRecent(ci, StatisticsHelper.DAY_TIME_TYPE,expNum,ctgNum);
            
            RegStatSec regSec = StatisticsHelper.generateRegStatSec(ci, StatisticsHelper.SECOND_TIME_TYPE,expNum,ctgNum);
            RegStatRecent regMin =  StatisticsHelper.generateRegStatRecent(ci,StatisticsHelper.MINUTE_TIME_TYPE,expNum,ctgNum);
            RegStatRecent regHour = StatisticsHelper.generateRegStatRecent(ci, StatisticsHelper.HOUR_TIME_TYPE,expNum,ctgNum);
            RegStatRecent regDay =  StatisticsHelper.generateRegStatRecent(ci, StatisticsHelper.DAY_TIME_TYPE,expNum,ctgNum);
            this.collector.emit(new Values(useSec,useMin,useHour,useDay,regSec,regMin,regHour,regDay));
            this.collector.ack(tuple);
            count++;
            if(LOG.isDebugEnabled()){
                LOG.debug("splitBolt rec logs count:"+count);
            }
        }catch(Exception e){
            LOG.error("split bolt execute fail,cause by:",e);
        }
        
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("useSec","useMin","useHour","useDay","regSec","regMin","regHour","regDay"));
    }
    

}
