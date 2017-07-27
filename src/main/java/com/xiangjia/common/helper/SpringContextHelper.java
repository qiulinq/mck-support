package com.xiangjia.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * spring工具类
 * <p>
 * 完成日期：2016年6月12日
 * </p>
 * 
 * @author hj
 * @version 1.0
 */
public class SpringContextHelper implements ApplicationContextAware {
	/**
	 * spring上下文
	 */
	private static ApplicationContext ctx;
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(SpringContextHelper.class);

	/**
	 * 无参构造方法
	 */
	public SpringContextHelper() {
	}

	/**
	 * 注入spring上下文
	 * 
	 * @param applicationContext
	 * @throws org.springframework.beans.BeansException
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}

	/**
	 * 按id获取对象实例
	 * 
	 * @param id
	 *            键
	 * @return 对象实例
	 */
	public static Object getBeanById(String id) {
		return ctx.getBean(id);
	}

	/**
	 * 按id获取对象实例
	 * 
	 * @param beanName
	 *            键
	 * @return 对象实例
	 * @throws org.springframework.beans.BeansException
	 * @throws ClassNotFoundException
	 */
	public static <T> T getBean(String beanName, Class<T> cls) throws BeansException, ClassNotFoundException {
		return (T) ctx.getBean(beanName);
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}

	/**
	 * 按类类型获取对象实例
	 * 
	 * @param cls
	 *            类类型
	 * @return 对象实例
	 * @throws org.springframework.beans.BeansException
	 */
	public static <T> T getBean(Class<T> cls) throws BeansException {
		return ctx.getBean(cls);
	}

}
