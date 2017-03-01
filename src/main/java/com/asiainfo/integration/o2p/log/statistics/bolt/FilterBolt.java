/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsBolt.java 
 * Package Name:com.asianinfo.log.statistics 
 * Date:2015年9月17日下午3:39:18 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.statistics.bolt;  

import java.util.Map;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import com.ailk.eaap.op2.bo.LogMessageObject;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

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
public class FilterBolt extends  BaseRichBolt {

    //BaseRichBolt  IBasicBolt
    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    private  static final Logger LOG =  LoggerFactory.getLogger(FilterBolt.class);
    
    private transient OutputCollector collector;
    
    private transient int count;
    
    @Override
    public void prepare(Map stormConf, TopologyContext context,
            OutputCollector collector) {
        this.collector = collector;
        if(LOG.isInfoEnabled()){
            LOG.info("Filter bolt init finished!");
        }
    }

    @Override
    public void execute(Tuple tuple) {
        
        try {
            LogMessageObject log = (LogMessageObject) tuple.getValue(0);
            count++;
            if(!"O2P-client".equals(log.getSrcSysSign())){
                this.collector.emit(new Values(log));
            }
            this.collector.ack(tuple);
            if(LOG.isDebugEnabled()){
                LOG.debug("Filter rec logs count："+count);
            }
            LOG.info("Filter rec logs count："+count);
        } catch (Exception e) {
           LOG.error("filter bolt err,cause by:",e);
        }
    }

    
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("preStatisticsLog"));
    }
}
