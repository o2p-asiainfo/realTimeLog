/** 
 * Project Name:stormDemo 
 * File Name:StoreLogState.java 
 * Package Name:com.ai.mine.trident.state 
 * Date:2015年9月14日下午5:52:52 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.state;  

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.storm.trident.state.State;

import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:StoreLogState <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月14日 下午5:52:52 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StoreLogState implements State {

    private transient  LogStoreDAO mybatis;
    
    public StoreLogState(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:stormRMDB-ds.xml");
        this.mybatis = (LogStoreDAO) ctx.getBean("mybatisDao");
    }
    
    @Override
    public void beginCommit(Long txid) {
        
    }

    @Override
    public void commit(Long txid) {
        
    }
    
    public void BatchInsert(List<LogMessageObject> logs){
        for(LogMessageObject log:logs){
            ContractInteraction ci = (ContractInteraction) log.getContractInteractionList().get(0);
            ci.setTabSuffix("_1509");
            ci.setContractInteractionId("23232323");
            this.mybatis.insert("mapper.contractInteraction.insertContractInteraction",ci);
        }
    }


}
