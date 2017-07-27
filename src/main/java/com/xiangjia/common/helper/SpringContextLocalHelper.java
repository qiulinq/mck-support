package com.xiangjia.common.helper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextLocalHelper {

	private static ApplicationContext context = null;
	static {
		initSpring();
	}

	public static void initSpring() {
		if (context == null) {
			context = SpringContextHelper.getCtx();
		}
		if (context == null) {
			context = new ClassPathXmlApplicationContext("classpath*:com/xiangjia/config/xml/application.xml");
		}
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}
}
