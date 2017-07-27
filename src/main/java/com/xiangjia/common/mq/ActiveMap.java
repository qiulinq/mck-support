package com.xiangjia.common.mq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.Session;

public class ActiveMap {

	public static Map<String, Connection> cMap = new HashMap<String, Connection>();

	public static Map<String, Session> sMap = new HashMap<String, Session>();
}
