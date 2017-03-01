/** 
 * Project Name:stormDemo 
 * File Name:AmqCoordinator.java 
 * Package Name:com.ai.mine.trident.spout 
 * Date:2015年9月6日下午5:34:56 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.spout;  

import java.io.Serializable;

import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.spout.ITridentSpout.BatchCoordinator;

/** 
 * ClassName:AmqCoordinator <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月6日 下午5:34:56 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class AmqCoordinator implements ITridentSpout.BatchCoordinator<Long>,Serializable{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    

    @Override
    public Long initializeTransaction(long txid, Long prevMetadata,
            Long currMetadata) {
        System.out.println("init trans ["+txid+"]");
        return null;
    }

    @Override
    public void success(long txid) {
        System.out.println("success trans ["+txid+"]");
    }

    @Override
    public boolean isReady(long txid) {
        return true;
    }

    @Override
    public void close() {
        
    }

}
