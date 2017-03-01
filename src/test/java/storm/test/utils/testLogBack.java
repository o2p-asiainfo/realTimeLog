/** 
 * Project Name:realTimeLog 
 * File Name:testLogBack.java 
 * Package Name:storm.test.utils 
 * Date:2015年11月17日上午11:59:09 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.utils;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * ClassName:testLogBack <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年11月17日 上午11:59:09 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class testLogBack {

    private static Logger log = LoggerFactory.getLogger(testLogBack.class); 
    
    public static void main(String[] args) {
        log.trace("======trace");  
        log.debug("======debug");  
        log.info("======info");  
        log.warn("======warn");  
        log.error("======error");  
    }
}
