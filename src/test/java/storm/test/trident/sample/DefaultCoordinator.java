/** 
 * Project Name:stormDemo 
 * File Name:DefaultCoordinator.java 
 * Package Name:stormDemo 
 * Date:2015年9月8日上午11:12:46 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.storm.trident.spout.ITridentSpout;

/** 
 * ClassName:DefaultCoordinator <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月8日 上午11:12:46 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class DefaultCoordinator implements
    ITridentSpout.BatchCoordinator<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    
    @Override
    public boolean isReady(long txid) {
    return true;
    }
    
    @Override
    public void close() {
    }
    
    
    @Override
    public Long initializeTransaction(long txid, Long prevMetadata, Long currMetadata) {
    return null;
    }
    
    @Override
    public void success(long txid) {
    System.out.println("Successful Transaction [" + txid + "]");
    }
}