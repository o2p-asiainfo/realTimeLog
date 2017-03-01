/** 
 * Project Name:realTimeLog 
 * File Name:QueryLogBolt.java 
 * Package Name:com.asiainfo.log.drpc.process 
 * Date:2015年10月7日下午6:04:41 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.drpc.process;  

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/** 
 * ClassName:QueryLogBolt <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年10月7日 下午6:04:41 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class QueryLogBolt extends BaseBasicBolt {

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        
        String arg = tuple.getString(0);
        Object retInfo = tuple.getValue(1);
        collector.emit(new Values(arg + "!!!", retInfo));
        
//        String input = tuple.getString(1);
//        collector.emit(new Values(tuple.getValue(0), input + "$$$$$$$$$$$$$"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("result", "result-info"));
    }

}
