/** 
 * Project Name:stormDemo 
 * File Name:AmqSpout.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月22日下午12:25:32 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.spout;  

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import com.ailk.eaap.o2p.common.spring.config.ZKCfgCacheHolder;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
import com.asiainfo.integration.o2p.log.utils.ContextHelper;
import com.ailk.eaap.op2.bo.LogMessageObject;

/** 
 * ClassName:AmqSpout <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年8月22日 下午12:25:32 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class AmqSpout extends BaseRichSpout{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;

    public static final Logger LOG = LoggerFactory.getLogger(AmqSpout.class);

    private transient SpoutOutputCollector collector;
    
    private transient ContextHelper<Boolean> sendLocal;
    
    private transient ActiveMqClient client;
    
    private transient ContextHelper<Integer> counter;
    
    private transient long sleepTime;
    
    public void nextTuple() {
//        int count = counter.get();
        ObjectMessage message = null;
        LogMessageObject logMessageObject = null;
        try {
            message = ActiveMqUtils.getMessage(client);
            if(message != null){
//                LOG.info("receive obj class type:"+message.getObject());
                logMessageObject = (LogMessageObject) message.getObject();
                logMessageObject.setAllContractInteractionId(LogUtils.getRowKeyByUUID());
                collector.emit(new Values(logMessageObject),logMessageObject);
//                count++;
//                counter.set(count);
//                if(counter.get() == 100000){
//                    Utils.sleep(1000);
//                    counter.set(0);
//                }
            }else{
                if(sendLocal.get()){
                    this.collector.emit("noticeStream", new Values("refresh"));
                }
                Utils.sleep(this.sleepTime);
            }
        } catch (JMSException e) {
            ActiveMqUtils.rollback(client);
            LOG.error("fetch message from activemq error,cause by:", e);
        }catch(Exception e){
            //防止出现系统运行时异常线程退出
            ActiveMqUtils.rollback(client);
            LOG.error("fetch message from activemq error,cause by:", e);
        }
    }

    
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
       try{
           LogUtils.setZkAddrEntrance(conf);
            ActiveMqConfig config = new ActiveMqConfig(
                Properties.getInstance().getActiveMqURL(),
                Properties.getInstance().getActiveMqUsename(),
                Properties.getInstance().getActiveMqPassword(),
                Properties.getInstance().getActiveMqQueue()
            );
           this.client = new ActiveMqClient(config);
           this.sendLocal = new ContextHelper<Boolean>();
           this.sendLocal.set(true);
           this.counter = new ContextHelper<Integer>();
           this.counter.set(0);
           this.collector = collector;
           this.sleepTime = Long.parseLong(Properties.getInstance().getSpoutSleepTime());
           if(ZKCfgCacheHolder.PROP_ITEMS == null){
               if(LOG.isInfoEnabled()){
                   LOG.info("ZKCfgCacheHolder.PROP_ITEMS is empty");
               }
           }else{
               if(LOG.isInfoEnabled()){
                   LOG.info("ZKCfgCacheHolder.PROP_ITEMS size is:"+
                           ZKCfgCacheHolder.PROP_ITEMS.size());
               }
           }
           
           if(LOG.isInfoEnabled()){
               LOG.info("AMQSpout init finished!");
           }
       }catch(Exception e){
           LOG.error("AMQSpout init fail,cause by:", e);
       }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("log"));
        declarer.declareStream("noticeStream", new Fields("noticeStream"));
    }
    
    @Override
    public void ack(Object msgId) {
        ActiveMqUtils.commit(client);
        if(LOG.isDebugEnabled()){
            LOG.debug("consumed success!");
        }
        LOG.info("consumed success!");
    }
    
    @Override
    public void fail(Object msgId) {
        //处理失败，进行重发
        //如果异常的日志较多，在内存循环发送，可能oom
        LogMessageObject rollbackLog = (LogMessageObject) msgId;
        if(!CollectionUtils.isEmpty(rollbackLog.getContractInteractionList())
                && rollbackLog.getContractInteractionList().get(0) != null){
            LOG.error("process err ,the contractionID is :"+
                    rollbackLog.getContractInteractionList().get(0).getContractInteractionId()
                    +"， rollback to activemq");
        }
        ActiveMqUtils.rollback(client);
        //collector.emit(new Values(rollbackLog), rollbackLog);
    }
    
    
}
