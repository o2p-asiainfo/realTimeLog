/** 
 * Project Name:stormDemo 
 * File Name:KafkaSpout.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月23日下午9:55:07 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;

/** 
 * ClassName:KafkaSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月23日 下午9:55:07 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class KafkaSpout extends BaseRichSpout{

    public void nextTuple() {
        // TODO Auto-generated method stub
        
    }

    public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
        // TODO Auto-generated method stub
        
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {
        // TODO Auto-generated method stub
        
    }

}
