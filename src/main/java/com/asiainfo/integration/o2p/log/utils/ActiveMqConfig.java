/** 
 * Project Name:stormDemo 
 * File Name:ActiveMqConfig.java 
 * Package Name:com.ai.mine.util 
 * Date:2015年9月7日上午10:53:55 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.utils;  

import java.io.Serializable;


import org.apache.commons.lang.StringUtils;

/** 
 * ClassName:ActiveMqConfig <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月7日 上午10:53:55 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ActiveMqConfig implements Serializable{

    
    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    private String url;
    private String username;
    private String password;
    private String queue;
    
    public ActiveMqConfig(){
    }
    
    public ActiveMqConfig(String url,String username,String password,String queue){
    	if(StringUtils.isEmpty(username)){
            this.username = null;
        }else{
            this.username = username;
        }
        if(StringUtils.isEmpty(password)){
            this.password = null;
        }else{
            this.password = password;
        }
        this.url = url;
        this.queue = queue;
    }



    public String getUrl() {
        return url;
    }



    public void setUrl(String url) {
        this.url = url;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getQueue() {
        return queue;
    }



    public void setQueue(String queue) {
        this.queue = queue;
    }
    
    
    
    
}
