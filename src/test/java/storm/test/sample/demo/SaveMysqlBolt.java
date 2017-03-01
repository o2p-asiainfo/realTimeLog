/** 
 * Project Name:stormDemo 
 * File Name:SaveMysqlBolt.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月22日下午12:27:32 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.LogMessageObject;

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
public class SaveMysqlBolt extends BaseRichBolt {

    private int count = 0;
    private OutputCollector collector;
    
    private transient  LogStoreDAO mybatis;
    
    public SaveMysqlBolt(){
        super();
    }
    public SaveMysqlBolt(LogStoreDAO mybatis){
        super();
        this.mybatis = mybatis;
    }
    
    public void execute(Tuple tuple) {
        LogMessageObject logMessageObject = (LogMessageObject) tuple.getValue(0);
        count++;
        if(logMessageObject != null){
            ContractInteraction ci = (ContractInteraction) logMessageObject.getContractInteractionList().get(0);
            ci.setTabSuffix("_1509");
            ci.setContractInteractionId("23232323");
            this.mybatis.insert("mapper.contractInteraction.insertContractInteraction",ci);
            //collector.emit(new Values(logMessageObject));
            collector.ack(tuple);
            //System.out.println("rec value ："+logMessageObject.getContractInteractionList().size());
            System.out.println("save bolt has rec logs ："+count);
        }
    }

    public void prepare(Map arg0, TopologyContext arg1, OutputCollector collector) {
        this.collector = collector;
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:stormRMDB-ds.xml");
        this.mybatis = (LogStoreDAO) ctx.getBean("mybatisDao");
        System.out.println("prepare in saveBolt");
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        
    }

}
