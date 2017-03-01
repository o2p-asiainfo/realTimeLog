/** 
 * Project Name:stormDemo 
 * File Name:DiagnosisEventSpout.java 
 * Package Name:stormDemo 
 * Date:2015年9月8日上午11:11:23 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;
import org.apache.storm.trident.spout.ITridentSpout;

/** 
 * ClassName:DiagnosisEventSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月8日 上午11:11:23 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class DiagnosisEventSpout implements ITridentSpout<Long>{

    private static final long serialVersionUID = 1L;
    SpoutOutputCollector collector;
    BatchCoordinator<Long> coordinator = new DefaultCoordinator();
    Emitter<Long> emitter = new DiagnosisEventEmitter();

    @Override
    public BatchCoordinator<Long> getCoordinator(
            String txStateId, Map conf, TopologyContext
            context) {
        return coordinator;
    }

    @Override
    public Emitter<Long> getEmitter(String txStateId, Map conf, TopologyContext context) {
        return emitter;
    }

    @Override
    public Map getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("event");
    }
}
