/** 
 * Project Name:stormDemo 
 * File Name:StateFactory.java 
 * Package Name:com.ai.mine.trident.state 
 * Date:2015年9月6日下午3:26:40 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.state;  

import java.util.Map;

import org.apache.storm.task.IMetricsContext;
import org.apache.storm.trident.state.State;
import org.apache.storm.trident.state.StateFactory;

/** 
 * ClassName:StateFactory <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月6日 下午3:26:40 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class PersistStateFactory implements StateFactory{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    @Override
    public State makeState(Map conf, IMetricsContext metrics,
            int partitionIndex, int numPartitions) {
        return new StoreLogState();
    }

}
