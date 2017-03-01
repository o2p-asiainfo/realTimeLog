/** 
 * Project Name:stormDemo 
 * File Name:ActiveMqUtils.java 
 * Package Name:com.ai.mine.util 
 * Date:2015年9月7日上午10:51:42 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.utils;  

import java.io.Serializable;

import javax.jms.ObjectMessage;

import com.ailk.eaap.op2.bo.LogMessageObject;


/** 
 * ClassName:ActiveMqUtils <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月7日 上午10:51:42 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public final class ActiveMqUtils implements Serializable{

    
    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    

    public static LogMessageObject  getTuples(ActiveMqClient client){
       return client.getMessage();
    }
    
    public static ObjectMessage getMessage(ActiveMqClient client){
        return  client.getObjectMessage();
    }
    public static void commit(ActiveMqClient client){
        client.commit();
    }
    public static void rollback(ActiveMqClient client){
        client.rollback();
    }
    private ActiveMqUtils(){
    }
}
