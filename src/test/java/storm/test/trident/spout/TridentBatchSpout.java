/** 
 * Project Name:stormDemo 
 * File Name:TridentBatchSpout.java 
 * Package Name:com.ai.mine.trident.spout.activemq 
 * Date:2015年9月15日下午4:16:47 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.spout;  
/** 
 * ClassName:TridentBatchSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月15日 下午4:16:47 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.IBatchSpout;
import org.apache.storm.Config;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
import com.ailk.eaap.op2.bo.LogMessageObject;


public class TridentBatchSpout implements IBatchSpout {

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    Fields fields;
    List<Object>[] outputs;
    int maxBatchSize;
    HashMap<Long, List<List<Object>>> batches = new HashMap<Long, List<List<Object>>>();
    
    public TridentBatchSpout(Fields fields, int maxBatchSize, List<Object>... outputs) {
        this.fields = fields;
        this.outputs = outputs;
        this.maxBatchSize = maxBatchSize;
    }
    
    ActiveMqConfig config;
    ActiveMqClient client;
    public TridentBatchSpout(ActiveMqConfig config,int maxBatchSize,Fields fields){
        this.config = config;
        this.maxBatchSize = maxBatchSize;
        this.fields = fields;
    }
    
    int index = 0;
    boolean cycle = false;
    
    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }
    
    @Override
    public void open(Map conf, TopologyContext context) {
        index = 0;
        client = new ActiveMqClient(config);
    }

    @Override
    public void emitBatch(long batchId, TridentCollector collector) {
//        List<List<Object>> batch = this.batches.get(batchId);
//        if(batch == null){
//            batch = new ArrayList<List<Object>>();
//            if(index>=outputs.length && cycle) {
//                index = 0;
//            }
//            for(int i=0; index < outputs.length && i < maxBatchSize; index++, i++) {
//                batch.add(outputs[index]);
//            }
//            this.batches.put(batchId, batch);
//        }
//        for(List<Object> list : batch){
//            collector.emit(list);
//        }
        
        
        List<List<Object>> batch = new ArrayList<List<Object>>();
        LogMessageObject log = ActiveMqUtils.getTuples(client);
        while(batch.size() != this.maxBatchSize && log !=null){
            batch.add(new Values(log));
            log = ActiveMqUtils.getTuples(client);
        }
        if(batch.size()>0){
            for(List<Object> list : batch){
                collector.emit(list);
            }
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
        Config conf = new Config();
        //conf.setMaxTaskParallelism(1);
        return conf;
    }

    @Override
    public Fields getOutputFields() {
        return fields;
    }
    
}