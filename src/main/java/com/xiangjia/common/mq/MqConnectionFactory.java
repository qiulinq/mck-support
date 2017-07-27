package com.xiangjia.common.mq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MqConnectionFactory {
	
	private static final String MQ_HOST_URL = "tcp://139.196.222.90:61616";

	public static ConnectionFactory connectionFactory;

	public ConnectionFactory getFactory() {
		if (connectionFactory == null) {
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD,
					MQ_HOST_URL);
			return connectionFactory;
		}
		return connectionFactory;
	}
}
