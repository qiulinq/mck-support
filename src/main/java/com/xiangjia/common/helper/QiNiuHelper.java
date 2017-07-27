package com.xiangjia.common.helper;

import com.xiangjia.common.util.PropertiesUtil;

public class QiNiuHelper {

	private static final String QINIU_CONFIG_PATH = "com/mck/config/properties/qiniu";

	public static String getAk() {
		return PropertiesUtil.readProperties("AccessKey", QINIU_CONFIG_PATH);
	}

	public static String getSk() {
		return PropertiesUtil.readProperties("SecretKey", QINIU_CONFIG_PATH);
	}

	public static String getBucket() {
		return PropertiesUtil.readProperties("bucket", QINIU_CONFIG_PATH);
	}
}
