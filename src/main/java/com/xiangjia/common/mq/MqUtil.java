package com.xiangjia.common.mq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

public class MqUtil {

	public static void closeMq(String name) {
		Connection connection = ActiveMap.cMap.get(name);
		Session session = ActiveMap.sMap.get(name);
		try {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
