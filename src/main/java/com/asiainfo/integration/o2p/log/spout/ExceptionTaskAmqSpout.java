package com.asiainfo.integration.o2p.log.spout;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ailk.eaap.o2p.common.spring.config.ZKCfgCacheHolder;
import com.ailk.eaap.op2.bo.ExceptionDealInfo;
import com.asiainfo.integration.o2p.log.common.util.LogUtils;
import com.asiainfo.integration.o2p.log.common.util.Properties;
import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
import com.asiainfo.integration.o2p.log.utils.ContextHelper;

public class ExceptionTaskAmqSpout extends BaseRichSpout{

	private static final long serialVersionUID = 1L;
	public static final Logger LOG = LoggerFactory.getLogger(ExceptionTaskAmqSpout.class);
	private transient SpoutOutputCollector collector;
    
    private transient ContextHelper<Boolean> sendLocal;
    
    private transient ActiveMqClient client;
    
    private transient ContextHelper<Integer> counter;
    
    private transient long sleepTime;
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		try{
	           LogUtils.setZkAddrEntrance(conf);
	            ActiveMqConfig config = new ActiveMqConfig(
	                Properties.getInstance().getActiveMqURL(),
	                Properties.getInstance().getActiveMqUsename(),
	                Properties.getInstance().getActiveMqPassword(),
	                Properties.getInstance().getExceptionDestination()
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
	               LOG.info("ExceptionTaskAmqSpout init finished!");
	           }
	       }catch(Exception e){
	           LOG.error("ExceptionTaskAmqSpout init fail,cause by:", e);
	       }
	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub

      ObjectMessage message = null;
      ExceptionDealInfo exceptionDealInfo = null;
      try {
          message = client.getObjectMessage();
          if(message != null){

        	  exceptionDealInfo = (ExceptionDealInfo) message.getObject();
        	  
              collector.emit(new Values(exceptionDealInfo),exceptionDealInfo);

          }else{
              if(sendLocal.get()){
                  this.collector.emit("exceptiontaskStream", new Values("refresh"));
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

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("exceptiontask"));
        declarer.declareStream("exceptiontaskStream", new Fields("exceptiontaskStream"));
	}

}
