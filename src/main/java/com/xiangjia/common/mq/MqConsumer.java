package com.xiangjia.common.mq;

import java.sql.Timestamp;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import blade.kit.json.JSON;
import blade.kit.json.JSONObject;

/**
 * 消费者
 * 
 * @author Administrator
 * 
 */
public class MqConsumer implements MessageListener {
	private static Logger log = LoggerFactory.getLogger(MqConsumer.class);
	static Connection connection = null;
	static Session session = null;
	String name;
	MqMessageHandlerInterface messageHandlerInterface = null;

	public MqConsumer(String name, MqMessageHandlerInterface messageHandler) {
		messageHandlerInterface = messageHandler;
		// 如果存在则不需要再创建
		if (ActiveMap.cMap.get(name) != null || ActiveMap.sMap.get(name) != null) {
			return;
		}
		MqConnectionFactory factory = new MqConnectionFactory();
		try {
			connection = factory.getFactory().createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(name);
			if (ActiveMap.cMap.get(name) == null) {
				ActiveMap.cMap.put(name, connection);
			}
			if (ActiveMap.sMap.get(name) == null) {
				ActiveMap.sMap.put(name, session);
			}
			// 消费者，消息接收者
			MessageConsumer consumer = session.createConsumer(destination);
			// 消费者，消息接收者
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			if (messageHandlerInterface == null) {
				throw new Exception("消息处理类为空");
			}
			JSONObject json = JSON.parse(textMessage.getText()).asObject();
			MqMessageModel messageModel = new MqMessageModel(json.getInt("messageType", -1),null, json.getString("messageText"), new Timestamp(json.getLong("createDate",0l)));
			messageHandlerInterface.handleMessage(messageModel);
			
		} catch (Exception e) {
			log.error("mq:" + name + "在处理消息时出现异常:" + e.getMessage() + ";session:" + session);
			e.printStackTrace();
		}finally{
			try {
				if(session != null){
					session.commit();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
