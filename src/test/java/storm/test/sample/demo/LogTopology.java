/** 
 * Project Name:stormDemo 
 * File Name:LogTopology.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月23日下午11:53:16 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  


import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.InvalidTopologyException;


/** 
 * ClassName:LogTopology <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月23日 下午11:53:16 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LogTopology {

    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        
//        Config conf = new Config();
//        conf.setDebug(true);
//        conf.setNumWorkers(1);
//        conf.setMaxSpoutPending(10000);//spout可以缓存的最大tuple数量
//        conf.registerSerialization(LogMessageObject.class);
//        TopologyBuilder builder = new TopologyBuilder();
//        builder.setSpout("spout", new AmqSpout(), 6);//第三个参数：每个workers设置的线程
//        builder.setBolt("save", new SaveMysqlBolt(), 6).shuffleGrouping("spout");
//        LocalCluster local = new LocalCluster();
//        local.submitTopology("savelogInLocal", conf, builder.createTopology());
       
        
        
        
        int i = 5;
        System.out.println((i<5)?10.9:9);
        char x ='X';
        System.out.println(false?10:x);
        
    }
}
