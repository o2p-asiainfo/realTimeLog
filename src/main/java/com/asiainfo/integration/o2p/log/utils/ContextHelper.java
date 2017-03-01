/** 
 * Project Name:realTimeLog 
 * File Name:ContextHelper.java 
 * Package Name:com.asianinfo.log.utils 
 * Date:2015年9月28日下午3:22:22 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.utils;  

import java.io.Serializable;

/** 
 * ClassName:ContextHelper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月28日 下午3:22:22 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ContextHelper<T> extends ThreadLocal<T> implements Serializable{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

}
