/** 
 * Project Name:stormDemo 
 * File Name:StatisticBolt.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月22日下午12:28:58 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:StatisticBolt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月22日 下午12:28:58 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StatisticBolt extends BaseRichBolt{

    private int count = 0;
    private OutputCollector collector;
    
    public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
        this.collector = collector;
    }
    public void execute(Tuple tuple) {
        LogMessageObject logMessageObject = (LogMessageObject) tuple.getValue(0);
        count++;
        if(logMessageObject != null){
            ContractInteraction ci = (ContractInteraction) logMessageObject.getContractInteractionList().get(0);
            ci.setTabSuffix("_1508");
            ci.setContractInteractionId("23232323");
            //this.mybatis.insert("mapper.contractInteraction.insertContractInteraction",ci);
            collector.emit(new Values(logMessageObject));
            collector.ack(tuple);
            //System.out.println("rec value ："+logMessageObject.getContractInteractionList().size());
            System.out.println("statistic bolt has rec logs ："+count);
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        
    }

    
}
