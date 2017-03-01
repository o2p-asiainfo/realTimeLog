/** 
 * Project Name:stormDemo 
 * File Name:AmqSpout.java 
 * Package Name:com.ai.mine.stormDemo 
 * Date:2015年8月22日下午12:25:32 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package storm.test.sample.demo;  

import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.pool.PooledConnectionFactory;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import com.asiainfo.integration.o2p.log.utils.ActiveMqClient;
import com.asiainfo.integration.o2p.log.utils.ActiveMqConfig;
import com.asiainfo.integration.o2p.log.utils.ActiveMqUtils;
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

    private SpoutOutputCollector collector;
    
    //从activeq读取
    private PooledConnectionFactory pooledConnectionFactory;
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;
    
    private ActiveMqClient client;
    private int count = 0;
    public void nextTuple() {
        ObjectMessage message = null;
        LogMessageObject logMessageObject = null;
        try {
            //message = (ObjectMessage) consumer.receive(1000);
            message = ActiveMqUtils.getMessage(client);
            if(message != null){
                logMessageObject = (LogMessageObject) message.getObject();
                count ++;
                System.out.println("in spout log src:"+logMessageObject.getSrcSysSign());
                collector.emit(new Values(logMessageObject));
                System.out.println("spout rec logs "+count);
            }else{
                System.out.println("all logs are consumed");
                Utils.sleep(1000);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    
    public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector collector) {
       ActiveMqConfig config = new ActiveMqConfig();
       config.setUsername( ActiveMQConnection.DEFAULT_USER);
       config.setPassword( ActiveMQConnection.DEFAULT_PASSWORD);
       config.setUrl("tcp://192.168.56.1:61616");
       config.setQueue("o2pLogQueue");
       this.client = new ActiveMqClient(config);
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//                ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, 
//                "tcp://192.168.56.1:61616");
//        try {
//            connection = connectionFactory.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            destination = session.createQueue("o2pLogQueue");
//            consumer = session.createConsumer(destination);
//            connection.start();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
        this.collector = collector;
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("log"));
    }
    
    @Override
    public void ack(Object msgId) {
        super.ack(msgId);
    }
    
    @Override
    public void fail(Object msgId) {
        super.fail(msgId);
    }
    
    
}
