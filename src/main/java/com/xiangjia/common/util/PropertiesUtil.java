package com.xiangjia.common.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author qiulinq
 * properties工具类
 */
public class PropertiesUtil {
	/**
	 * @param key 需要读取的key
	 * @param propertiesName peoperties文件名
	 * @return 读取到key对应的值
	 */
	public static String readProperties(String key, String propertiesName) {
		ResourceBundle bundle = PropertyResourceBundle
				.getBundle(propertiesName);
		if (!bundle.containsKey(key)) {
			return null;
		}
		return bundle.getString(key);
	}
	
	public static void main(String[] args) {
		System.out.println(readProperties("mail.smtp.host", "mail"));;
	}
}
