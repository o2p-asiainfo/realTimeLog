/** 
 * Project Name:stormDemo 
 * File Name:AmqEmiiter.java 
 * Package Name:com.ai.mine.trident.spout 
 * Date:2015年9月6日下午5:35:11 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.spout;  

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout.Emitter;
import org.apache.storm.trident.topology.TransactionAttempt;

import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:AmqEmiiter <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月6日 下午5:35:11 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class AmqEmitter implements Emitter<Long>,Serializable{

    
    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    private  AtomicInteger  successFullTransactions = new AtomicInteger(0);
    private  final int batchNum = 100;
    private List<Object> lists = new ArrayList<Object>();
    private int count;
    
    private ActiveMqConfig config;
    private ActiveMqClient client;
    
    public AmqEmitter(ActiveMqConfig config){
        this.config = config;
        this.client = new ActiveMqClient(config);
        
    }
    
   

    @Override
    public void emitBatch(TransactionAttempt tx, Long coordinatorMeta,
            TridentCollector collector) {
        LogMessageObject log = ActiveMqUtils.getTuples(client);
        for(int i=0;i<101;i++){
                
            //LogMessageObject log = new LogMessageObject();
            this.count ++;
            if(log != null){
                lists.add(log);
                if(lists.size() == 100){
                    collector.emit(lists);
                }
            }
//            else{
//                collector.emit(lists);
//                System.out.println("consumed complete");
//            }
        }
        System.out.println("rec logs :"+count);
    }

    @Override
    public void success(TransactionAttempt tx) {
        successFullTransactions.incrementAndGet();
    }

    @Override
    public void close() {
    }

}
