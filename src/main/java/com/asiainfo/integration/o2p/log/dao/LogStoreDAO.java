/** 
 * Project Name:stormDemo 
 * File Name:IbatisDAO.java 
 * Package Name:com.ai.mine.rmdb 
 * Date:2015年8月25日上午9:41:25 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao;  

import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.HConnection;

import com.ailk.eaap.op2.bo.LogMessageObject;
import com.ailk.eaap.op2.bo.OriLogClob;


/** 
 * ClassName:IbatisDAO <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月25日 上午9:41:25 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public interface LogStoreDAO {

     void insert(String statementName, Object parameterObject);
     void batchInsert(List<LogMessageObject> list);
     Map insertLog(LogMessageObject log);
     void insertOriClobs(List<OriLogClob> oris);
     void putHbase(LogMessageObject log,HConnection connection);
     void putHbase(List<LogMessageObject> logs,HConnection connection);
    
}
