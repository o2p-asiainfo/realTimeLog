/** 
 * Project Name:stormDemo 
 * File Name:LogServerTopology.java 
 * Package Name:com.ai.mine.trident.topology 
 * Date:2015年9月7日下午10:55:17 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  


import org.apache.activemq.ActiveMQConnection;

import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;

import storm.test.trident.spout.TridentAmqBatchSpout;
import storm.test.trident.spout.TridentAmqGenerSpout;
import storm.test.trident.state.PersistStateFactory;
import storm.test.trident.state.StoreLogUpdater;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;



/** 
 * ClassName:LogServerTopology <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月7日 下午10:55:17 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LogServerTopology {

    
    
    
    public static void main(String[] args){
        String url = "tcp://localhost:61616";
        String username = null;
        String password = null;
        String queue = "o2pLogQueue";
        ActiveMqConfig activeMqConfig = new ActiveMqConfig(url,username,password,queue);
//        TridentBatchSpout spout = new TridentBatchSpout(activeMqConfig,2,new Fields("logs"));
        TridentAmqBatchSpout spout = new TridentAmqBatchSpout(activeMqConfig,2,new Fields("logs"));
        Config conf = new Config();
        conf.setDebug(true);
        conf.setMaxSpoutPending(10000);
        
        
        TridentTopology trident = new TridentTopology ();
        Stream stream = trident.newStream("logSpout", spout).parallelismHint(6);
        stream.partitionPersist(new PersistStateFactory(),new Fields("logs"), new StoreLogUpdater());
        
        LocalCluster local = new LocalCluster();
        local.submitTopology("tridentsavelogInLocal", conf, trident.build());
    }
}
