/** 
 * Project Name:stormDemo 
 * File Name:TransactionalTridentAmqSpout.java 
 * Package Name:com.ai.mine.trident.spout 
 * Date:2015年9月2日下午3:38:07 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.spout;  

import java.util.Map;

import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;

import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;

/** 
 * ClassName:TransactionalTridentAmqSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月2日 下午3:38:07 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class TridentAmqGenerSpout implements ITridentSpout<Long>{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    
    private ActiveMqConfig config;
    BatchCoordinator<Long> batchCoordinator;
    Emitter<Long> emitter;
    
    public TridentAmqGenerSpout(ActiveMqConfig config){
        this.config = config;
        this.batchCoordinator = new AmqCoordinator();
        this.emitter = new AmqEmitter(this.config);
    }

	@Override
	public org.apache.storm.trident.spout.ITridentSpout.BatchCoordinator<Long> getCoordinator(
			String txStateId, Map conf, TopologyContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public org.apache.storm.trident.spout.ITridentSpout.Emitter<Long> getEmitter(
			String txStateId, Map conf, TopologyContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fields getOutputFields() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
