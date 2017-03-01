/** 
 * Project Name:stormDemo 
 * File Name:TridentLocalTopologyWordCount.java 
 * Package Name:stormDemo 
 * Date:2015年9月14日上午11:17:18 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.trident.sample;  

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.BaseFunction;
import org.apache.storm.trident.operation.TridentCollector;
import org.apache.storm.trident.spout.IBatchSpout;
import org.apache.storm.trident.tuple.TridentTuple;

/** 
 * ClassName:TridentLocalTopologyWordCount <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月14日 上午11:17:18 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class TridentLocalTopologyWordCount {

    public static class DataSource implements IBatchSpout{

        HashMap<Long, List<List<Object>>> batches = new HashMap<Long, List<List<Object>>>();
        public void ack(long batchId) {
            this.batches.remove(batchId);
        }

        public void close() {
            
        }

        public void emitBatch(long batchId, TridentCollector collector) {
            try {
                Collection<File> files = FileUtils.listFiles(new File("e:\\test"), new String[]{"txt"}, true);
                for (File file : files) {
                    List<String> lines = FileUtils.readLines(file);
                    for (String line : lines) {
                        collector.emit(new Values(line));
                    }
                    FileUtils.moveFile(file, new File(file.getAbsolutePath()+System.currentTimeMillis()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Map getComponentConfiguration() {
            Config config = new Config();
            return config;
        }

        public Fields getOutputFields() {
            return new Fields("sentence");
        }

        public void open(Map arg0, TopologyContext arg1) {
            
        }
    }
    public static class SpilitBolt extends BaseFunction{

        public void execute(TridentTuple tuple, TridentCollector collector) {
            String line = tuple.getString(0);
            String[] words = line.split("\t");
            for (String word : words) {
                collector.emit(new Values(word));
            }
        }
        
    }
    public static class SumBolt extends BaseFunction{

        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        public void execute(TridentTuple tuple, TridentCollector collector) {
            String word = tuple.getString(0);
            //countMap.put(word, MapUtils.getInteger(countMap, word, 0)+1);
            System.out.println("===============================================");
            for (Entry<String, Integer> entry : countMap.entrySet()) {
                System.out.println(entry);
            }
        }
        
    }
    public static void main(String[] args) {
//      FixedBatchSpout spout = new FixedBatchSpout(new Fields("sentence"), 1, new Values(1));
//      spout.setCycle(true); //false就只会输出一次
        
        TridentTopology tridentTopology = new TridentTopology();
        tridentTopology.newStream("spout1", new DataSource())
        .each(new Fields("sentence"), new SpilitBolt(), new Fields("word"))
        .each(new Fields("word"), new SumBolt(),new Fields(""));
        
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("TridentTopology", new Config(), tridentTopology.build());
    }
}
