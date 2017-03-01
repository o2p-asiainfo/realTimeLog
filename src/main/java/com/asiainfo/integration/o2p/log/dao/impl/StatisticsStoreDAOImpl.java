/** 
 * Project Name:realTimeLog 
 * File Name:StatisticsStoreDAO.java 
 * Package Name:com.asianinfo.log.dao 
 * Date:2015年9月21日下午5:19:46 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.dao.impl;  

import java.util.List;

import org.apache.hadoop.hbase.client.HConnection;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.asiainfo.integration.o2p.log.common.bo.RegStatRecent;
import com.asiainfo.integration.o2p.log.common.bo.RegStatSec;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntRecent;
import com.asiainfo.integration.o2p.log.common.bo.UseStatCntSec;
import com.asiainfo.integration.o2p.log.dao.StatisticsStoreDAO;

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
public class StatisticsStoreDAOImpl extends SqlSessionDaoSupport  implements StatisticsStoreDAO{

    @Override
    public void insert(String statementName, Object parameterObject) {
        this.getSqlSession().insert(statementName, parameterObject);
    }

    @Override
    public int update(String statementName, Object parameterObject) {
        return this.getSqlSession().update(statementName, parameterObject);
    }
    
    @Override
    public void batchInsert(List<Object> list) {
    }


    @Override
    public void batchUpdate(List<Object> list) {
    }

    @Override
    public void saveOrUpdateUseSec(List<UseStatCntSec> list) {
        for(UseStatCntSec useSec : list){
            try{
                this.insert("mapper.statistics.useSec.insertUseSec", useSec);
            }catch(Exception e){
                this.update("mapper.statistics.useSec.updateUseSec", useSec);
            }
        }
    }

    @Override
    public void saveOrUpdateUseOther(List<UseStatCntRecent> list) {
        for(UseStatCntRecent useOther : list){
            try{
                this.insert("mapper.statistics.useOther.insertUseOther", useOther);
            }catch(Exception e){
                this.update("mapper.statistics.useOther.updateUseOther", useOther);
            }
            
            
            int affectRow = this.update("mapper.statistics.useOther.updateUseOther", useOther);
            if(affectRow != 1){
                this.insert("mapper.statistics.useOther.insertUseOther", useOther);
            }
        }
    }

    @Override
    public void saveOrUpdateRegSec(List<RegStatSec> list) {
        for(RegStatSec regSec : list){
            try{
                this.insert("mapper.statistics.regSec.insertRegSec", regSec);
            }catch(Exception e){
                this.update("mapper.statistics.regSec.updateRegSec", regSec);
            }
            
            int affectRow = this.update("mapper.statistics.regSec.updateRegSec", regSec);
            if(affectRow != 1){
                this.insert("mapper.statistics.regSec.insertRegSec", regSec);
            }
        }
    }

    @Override
    public void saveOrUpdateRegOther(List<RegStatRecent> list) {
        for(RegStatRecent regOther : list){
            
            try{
                this.insert("mapper.statistics.regOther.insertRegOther", regOther);
            }catch(Exception e){
                this.update("mapper.statistics.regOther.updateRegOther", regOther);
            }
            
            int affectRow = this.update("mapper.statistics.regOther.updateRegOther", regOther);
            if(affectRow != 1){
                this.insert("mapper.statistics.regOther.insertRegOther", regOther);
            }
        }
    }
    
}
