package com.xiangjia.common.mq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.alibaba.fastjson.JSON;

public class MqProducer {

	public static void sendMessage(MqMessageModel messageModel) {
		MqConnectionFactory factory = new MqConnectionFactory();
		Session session = null;
		Connection connection = null;
		try {
			connection = factory.getFactory().createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

			Destination destination = session.createQueue(messageModel.getDestination());
			// MessageProducer：消息生产者
			MessageProducer p = session.createProducer(destination);
			// 设置不持久化
			p.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage message = session.createTextMessage(JSON.toJSONString(messageModel));
			// 通过消息生产者发出消息
			p.send(message);
			// 发送一条消息

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {

			try {
				if (session != null)
					session.commit();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	// Connection connection = null;
	// Session session = null;
	//
	// private MqProducer() {
	// MqConnectionFactory factory = new MqConnectionFactory();
	// try {
	// connection = factory.getFactory().createConnection();
	// connection.start();
	// session = connection.createSession(Boolean.TRUE,
	// Session.AUTO_ACKNOWLEDGE);
	// } catch (JMSException e) {
	// e.printStackTrace();
	// }
	//
	// }

	/**
	 * 在指定的会话上，通过指定的消息生产者发出一条消息
	 * 
	 * @param session
	 *            消息会话
	 * @param producer
	 *            消息生产者
	 */
	// public void sendMsg(String name, String msg) throws JMSException {
	// Destination destination = this.session.createQueue(name);
	// // MessageProducer：消息生产者
	// MessageProducer p = this.session.createProducer(destination);
	// // 设置不持久化
	// p.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	// TextMessage message = this.session.createTextMessage(msg);
	// // 通过消息生产者发出消息
	// p.send(message);
	// // 发送一条消息
	// this.session.commit();
	// if (connection != null)
	// this.connection.close();
	// }
}
