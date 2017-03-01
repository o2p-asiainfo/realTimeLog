/** 
 * Project Name:stormDemo 
 * File Name:StoreMysqlState.java 
 * Package Name:com.ai.mine.trident.state 
 * Date:2015年9月6日下午3:27:21 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.state;  

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.apache.storm.trident.state.map.IBackingMap;

import com.asiainfo.integration.o2p.log.dao.LogStoreDAO;
import com.ailk.eaap.op2.bo.ContractInteraction;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:StoreMysqlState <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月6日 下午3:27:21 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class StoreLogStateBacking implements IBackingMap<LogMessageObject>{

    
    private transient  LogStoreDAO mybatis;
    
    public StoreLogStateBacking(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:stormRMDB-ds.xml");
        this.mybatis = (LogStoreDAO) ctx.getBean("mybatisDao");
    }
    
    @Override
    public List<LogMessageObject> multiGet(List<List<Object>> keys) {
        return null;
    }

    @Override
    public void multiPut(List<List<Object>> keys, List<LogMessageObject> vals) {
        for(LogMessageObject log:vals){
            ContractInteraction ci = (ContractInteraction) log.getContractInteractionList().get(0);
            ci.setTabSuffix("_1509");
            ci.setContractInteractionId("23232323");
            this.mybatis.insert("mapper.contractInteraction.insertContractInteraction",ci);
        }
    }

}
