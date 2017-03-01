/** 
 * Project Name:stormDemo 
 * File Name:TransactionalTridentAmqSpout.java 
 * Package Name:com.ai.mine.trident.spout 
 * Date:2015年9月2日下午3:38:07 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.spout;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.IBatchSpout;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:TransactionalTridentAmqSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月2日 下午3:38:07 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class TridentAmqBatchSpout implements IBatchSpout{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    
    private Fields fields;
    private ActiveMqConfig config;
    private ActiveMqClient client;
    HashMap<Long, List<Object>> batches = new HashMap<Long, List<Object>>();
    private int maxBatchSize;
    public TridentAmqBatchSpout(ActiveMqConfig config,int maxBatchSize,Fields fields){
        this.config = config;
        this.maxBatchSize = maxBatchSize;
        this.fields = fields;
    }

    @Override
    public void open(Map conf, TopologyContext context) {
        client = new ActiveMqClient(config);
    }

    @Override
    public void emitBatch(long batchId, TridentCollector collector) {
        List<Object> batch = this.batches.get(batchId);
        if(batch == null){
            batch = new ArrayList<Object>();
        }
        LogMessageObject log = ActiveMqUtils.getTuples(client);
        while(batch.size() != this.maxBatchSize && log !=null){
            batch.add(log);
            if(batch.size() == this.maxBatchSize){
                batches.put(batchId, batch);
            }
            log = ActiveMqUtils.getTuples(client);
        }
        if(batch.size()>0){
            collector.emit(new Values(batch));
        }
    }

    @Override
    public void ack(long batchId) {
        this.batches.remove(batchId);
    }

    @Override
    public void close() {
        
    }

    @Override
    public Map getComponentConfiguration() {
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return fields;
    }
    
    
}
