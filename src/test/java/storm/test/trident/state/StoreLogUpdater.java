/** 
 * Project Name:stormDemo 
 * File Name:StoreLogUpdater.java 
 * Package Name:com.ai.mine.trident.state 
 * Date:2015年9月15日上午11:07:13 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.state;  

import java.util.ArrayList;
import java.util.List;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.state.BaseStateUpdater;
import org.apache.storm.trident.tuple.TridentTuple;

import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:StoreLogUpdater <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月15日 上午11:07:13 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StoreLogUpdater extends BaseStateUpdater<StoreLogState>{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    @Override
    public void updateState(StoreLogState state, List<TridentTuple> tuples,
            TridentCollector collector) {
        List<Object> list = (List<Object>) tuples.get(0).get(0);
        List<LogMessageObject> logs = new ArrayList<LogMessageObject>();
        for(Object o : list){
            logs.add((LogMessageObject) o);
        }
        
        state.BatchInsert(logs);
    }

}
