/** 
 * Project Name:stormDemo 
 * File Name:LogTopology.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月23日下午11:53:16 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.topology;  



import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.LocalDRPC;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.spout.AmqSpout;
import com.asiainfo.integration.o2p.log.spout.ExceptionTaskAmqSpout;
import com.asiainfo.integration.o2p.log.statistics.bolt.FilterBolt;
import com.asiainfo.integration.o2p.log.statistics.bolt.RegCountBolt;
import com.asiainfo.integration.o2p.log.statistics.bolt.SplitBolt;
import com.asiainfo.integration.o2p.log.statistics.bolt.StoreStatisticsOfRegBolt;
import com.asiainfo.integration.o2p.log.statistics.bolt.StoreStatisticsOfUseBolt;
import com.asiainfo.integration.o2p.log.statistics.bolt.UseCountBolt;
import com.asiainfo.integration.o2p.log.store.bolt.StoreExceptionTaskBolt;
import com.asiainfo.integration.o2p.log.store.bolt.StoreLogBolt;


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
public final class LogTopology {

    private final static Logger LOG = LoggerFactory.getLogger(LogTopology.class);
    //线性扩展倍数 --> 允许集群executor数量增长因子
    private final static int  EXTEND_MULTIPLE = 1;
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
        Map<String,String> evn = new HashMap<String,String>();
        Config conf = new Config();
        if(args != null && args.length == 2){
            evn.put("PROPERTY_NAME_SPACE",args[0]);
            evn.put("CONNECT_STRING", args[1]);
            LogUtils.setZkAddrEntrance(evn);
            conf.put(LogUtils.PROPERTY_NAME_SPACE, args[0]);
            conf.put(LogUtils.CONNECT_STRING, args[1]);
            LOG.info("main args length:"+args.length);
            LOG.info("PROPERTY_NAME_SPACE :"+ args[0]+",CONNECT_STRING:"+args[1]);
        }
        conf.setDebug(false);
        conf.setMessageTimeoutSecs(60*60*24);
        conf.setNumWorkers(Integer.valueOf(Properties.getInstance().getWorkNum()));
        conf.setNumAckers(Integer.valueOf(Properties.getInstance().getWorkNum()));
        conf.setMaxSpoutPending(500);//spout可以缓存的最大tuple数量
        if(args != null && args.length>0){
            //running in cluster mode
            //读取配置信息入口参数
            StormSubmitter.submitTopology("realTimelogSys", conf, getTopology());
        }else{
            //running in local mode
            try{
                LocalCluster local = new LocalCluster();
                LocalDRPC drpc = new LocalDRPC();
                local.submitTopology("realTimelogSys", conf, getTopology(drpc));
            }catch(Exception e){
                LOG.error("start log topology occur err,cause by:",e);
            }
            
            //drpc test
/*            for(int i =0 ;i<100;i++){
                try{
                System.out.println(drpc.execute("queryLog", "params"));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }*/
        }
        
        
    }
    
    //cluster mode
    public static StormTopology getTopology(){
        TopologyBuilder builder = new TopologyBuilder();
        
        builder.setSpout("spout", new AmqSpout(), Integer.valueOf(Properties.getInstance().getSpoutThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getSpoutThreadNum()) * EXTEND_MULTIPLE);
        builder.setBolt("saveBolt", new StoreLogBolt(), Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum())* EXTEND_MULTIPLE)
                .shuffleGrouping("spout").shuffleGrouping("spout", "noticeStream");
        
        //统计数据流
        builder.setBolt("filterBolt", new FilterBolt(),Integer.valueOf(Properties.getInstance().getStatSplitThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStatSplitThreadNum())*EXTEND_MULTIPLE)
                .shuffleGrouping("saveBolt");
        builder.setBolt("splitBolt", new SplitBolt(),Integer.valueOf(Properties.getInstance().getStatSplitThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStatSplitThreadNum())*EXTEND_MULTIPLE)
                .shuffleGrouping("filterBolt");
        
        builder.setBolt("useCountBolt", new UseCountBolt(),Integer.valueOf(Properties.getInstance().getStatGroupThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStatGroupThreadNum())*EXTEND_MULTIPLE)
                .fieldsGrouping("splitBolt", new Fields("useSec","useMin","useHour","useDay"))
                .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("regCountBolt", new RegCountBolt(),Integer.valueOf(Properties.getInstance().getStatGroupThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStatGroupThreadNum())*EXTEND_MULTIPLE)
                .fieldsGrouping("splitBolt", new Fields("regSec","regMin","regHour","regDay"))
                .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("storeRegStatBolt", 
         new StoreStatisticsOfRegBolt(),Integer.valueOf(Properties.getInstance().getStatGroupStoreThreadNum()))
                .shuffleGrouping("regCountBolt")
                .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("storeUseStatBolt", 
         new StoreStatisticsOfUseBolt(),Integer.valueOf(Properties.getInstance().getStatGroupStoreThreadNum()))
                .shuffleGrouping("useCountBolt")
                .shuffleGrouping("spout", "noticeStream");
        
        
        builder.setSpout("exceptionSpout", new ExceptionTaskAmqSpout(), Integer.valueOf(Properties.getInstance().getSpoutThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getSpoutThreadNum()) * EXTEND_MULTIPLE);
        
        builder.setBolt("saveExceptionBolt", new StoreExceptionTaskBolt(), Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum())* EXTEND_MULTIPLE)
        .shuffleGrouping("exceptionSpout").shuffleGrouping("exceptionSpout", "exceptiontaskStream");

       /* 
        //drpc
        //实现读写分离，查询不在这里实现
        DRPCSpout drpcSpout = new DRPCSpout("queryLog");
        builder.setSpout("drpcInput", drpcSpout,2);  
        builder.setBolt("query", new QueryLogBolt(), 2).shuffleGrouping("drpcInput");  
        builder.setBolt("return", new ReturnResults(),2).shuffleGrouping("query"); 
        */
        
        
        return builder.createTopology();
        
    }
    //local mode
    public static StormTopology getTopology(LocalDRPC drpc){
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new AmqSpout(), Integer.valueOf(Properties.getInstance().getSpoutThreadNum()))
                .setNumTasks(Integer.valueOf(Properties.getInstance().getSpoutThreadNum()) * EXTEND_MULTIPLE);
        builder.setBolt("saveBolt", new StoreLogBolt(), Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum()))
                .shuffleGrouping("spout").shuffleGrouping("spout", "noticeStream")
                .setNumTasks(Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum())* EXTEND_MULTIPLE);

        //统计数据流
        builder.setBolt("filterBolt", new FilterBolt(),Integer.valueOf(Properties.getInstance().getStatSplitThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getStatSplitThreadNum())*EXTEND_MULTIPLE)
        .shuffleGrouping("saveBolt");
        builder.setBolt("splitBolt", new SplitBolt(),Integer.valueOf(Properties.getInstance().getStatSplitThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getStatSplitThreadNum())*EXTEND_MULTIPLE)
        .shuffleGrouping("filterBolt");

        builder.setBolt("useCountBolt", new UseCountBolt(),Integer.valueOf(Properties.getInstance().getStatGroupThreadNum()))
            .setNumTasks(Integer.valueOf(Properties.getInstance().getStatGroupThreadNum())*EXTEND_MULTIPLE)
            .fieldsGrouping("splitBolt", new Fields("useSec","useMin","useHour","useDay"))
            .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("regCountBolt", new RegCountBolt(),Integer.valueOf(Properties.getInstance().getStatGroupThreadNum()))
            .setNumTasks(Integer.valueOf(Properties.getInstance().getStatGroupThreadNum())*EXTEND_MULTIPLE)
            .fieldsGrouping("splitBolt", new Fields("regSec","regMin","regHour","regDay"))
            .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("storeRegStatBolt", new StoreStatisticsOfRegBolt(),1).globalGrouping("regCountBolt")
            .shuffleGrouping("spout", "noticeStream");
        builder.setBolt("storeUseStatBolt", new StoreStatisticsOfUseBolt(),1).globalGrouping("useCountBolt")
            .shuffleGrouping("spout", "noticeStream");
       
        //drpc
//        DRPCSpout drpcSpout = new DRPCSpout("queryLog",drpc);
//        builder.setSpout("drpcInput", drpcSpout,1);  
//        //真正处理的Bolt   
//        builder.setBolt("query", new QueryLogBolt(), 1).shuffleGrouping("drpcInput");  
//        //结束的ReturnResults  
//        builder.setBolt("return", new ReturnResults(),1).shuffleGrouping("query"); 
        builder.setSpout("exceptionSpout", new ExceptionTaskAmqSpout(), Integer.valueOf(Properties.getInstance().getSpoutThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getSpoutThreadNum()) * EXTEND_MULTIPLE);
        
        builder.setBolt("saveExceptionBolt", new StoreExceptionTaskBolt(), Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum()))
        .setNumTasks(Integer.valueOf(Properties.getInstance().getStoreBoltThreadNum())* EXTEND_MULTIPLE)
        .shuffleGrouping("exceptionSpout").shuffleGrouping("exceptionSpout", "exceptiontaskStream");
        
        return builder.createTopology();
        
    }
    
    private LogTopology(){
    }
}
