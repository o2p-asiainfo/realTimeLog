/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsStoreDAO.java 
 * Package Name:com.asianinfo.log.dao 
 * Date:2015年9月21日下午5:19:46 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao;  

import java.util.List;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;



/** 
 * ClassName:StatisticsStoreDAO <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月21日 下午5:19:46 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public interface StatisticsStoreDAO {

    public void insert(String statementName, Object parameterObject);
    public int update(String statementName, Object parameterObject);
    public void batchInsert(List<Object> list);
    public void batchUpdate(List<Object> list);
    public void saveOrUpdateUseSec(List<UseStatCntSec> list);
    public void saveOrUpdateUseOther(List<UseStatCntRecent> list);
    public void saveOrUpdateRegSec(List<RegStatSec> list);
    public void saveOrUpdateRegOther(List<RegStatRecent> list);
    
}
