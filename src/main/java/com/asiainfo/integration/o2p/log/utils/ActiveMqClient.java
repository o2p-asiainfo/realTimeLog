/** 
 * Project Name:stormDemo 
 * File Name:ActiveMqClient.java 
 * Package Name:com.ai.mine.util 
 * Date:2015年9月7日下午10:38:58 
 * Copyright (c) 2015, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.integration.o2p.log.utils;  

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ailk.eaap.op2.bo.LogMessageObject;


/** 
 * ClassName:ActiveMqClient <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2015年9月7日 下午10:38:58 <br/> 
 * @author   daimq 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ActiveMqClient implements Serializable{

    /** 
     * serialVersionUID:TODO. 
     * @since JDK 1.6 
     */  
    private static final long serialVersionUID = 1L;
    
    public  static final Logger LOG = LoggerFactory.getLogger(ActiveMqClient.class);
    
    private transient ActiveMqConfig config;
    
    private  transient ConnectionFactory connectionFactory;
    private  transient Connection connection = null;
    private  transient Session session;
    private  transient Destination destination;
    private  transient MessageConsumer consumer;
    
    public ActiveMqClient(ActiveMqConfig config){
        this.config = config;
        this.connectionFactory = new ActiveMQConnectionFactory(
                this.config.getUsername(), this.config.getPassword(),this.config.getUrl() 
               );
        try {
            this.connection = connectionFactory.createConnection();
            this.session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            this.destination = session.createQueue(config.getQueue());
            this.consumer = session.createConsumer(destination);
            this.connection.start();
        } catch (JMSException e) {
            LOG.error("activeMQ init error,cause by:",e);
        }
    }
    
    
    public LogMessageObject getMessage(){
        LogMessageObject log = null;
        try {
            ObjectMessage msg = (ObjectMessage) consumer.receive();
            if(msg != null){
                log = (LogMessageObject) msg.getObject();
            }
            return log;
        } catch (JMSException e) {
            LOG.error("fetch message from activemq fail,cause by:",e);
            return null;
        }
    }
    
    public ObjectMessage getObjectMessage(){
        try {
            return  (ObjectMessage) consumer.receive(1000);
        } catch (JMSException e) {
            LOG.error("fetch message from activemq fail,cause by:",e);
            return null;
        }
    }
    
    public void commit(){
        try {
            session.commit();
        } catch (JMSException e) {
            LOG.error("activemq commit opt error,cause by:",e);
        }
    }
    
    public void rollback(){
        try {
            session.rollback();
        } catch (JMSException e) {
            LOG.error("activemq rollback opt error,cause by:",e);
        }
    }
    
    
    
    
    

}
