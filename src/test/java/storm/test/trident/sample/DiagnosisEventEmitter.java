/** 
 * Project Name:stormDemo 
 * File Name:DiagnosisEventEmitter.java 
 * Package Name:stormDemo 
 * Date:2015年9月8日上午11:14:56 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.ITridentSpout;
import org.apache.storm.trident.topology.TransactionAttempt;

/** 
 * ClassName:DiagnosisEventEmitter <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月8日 上午11:14:56 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class DiagnosisEventEmitter implements
    ITridentSpout.Emitter<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    AtomicInteger successfulTransactions = new AtomicInteger(0);
    
    @Override
    public void emitBatch(TransactionAttempt tx, Long
        coordinatorMeta, TridentCollector collector) {
    for (int i = 0; i < 10000; i++) {
        List<Object> events = new ArrayList<Object>();
        //double lat =  new Double(-30 + (int) (Math.random() * 75));
        //double lng =  new Double(-120 + (int) (Math.random() * 70));
        long time = System.currentTimeMillis();
        //String diag = new Integer(320 + (int) (Math.random() * 7)).toString();
        //DiagnosisEvent event = new DiagnosisEvent(lat, lng, time, diag);
        DiagnosisEvent event  = new DiagnosisEvent(1,1,time,"1");
        events.add(event);
        collector.emit(events);
    }
    }
    
    @Override
    public void success(TransactionAttempt tx) {
    successfulTransactions.incrementAndGet();
    }
    
    @Override
    public void close() {
    }
}
