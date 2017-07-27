package com.xiangjia.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataUtil {

	private static Logger log = LoggerFactory.getLogger(DataUtil.class);

	public static String salesman() {
		try {
			if (SessionUtil.hasRole("salesman") && !SessionUtil.hasRole("admin")) {
				return " and create_user_id = " + SessionUtil.getUserId();
			}
		} catch (Exception e) {
		}
		return "";
	}

	public static String salesman(Integer isCheck) {
		if (isCheck != null && isCheck - 0 == 0) {
			return "";
		}
		return salesman();
	}

	public static String salesman(Integer isCheck, String colName) {
		log.info("===》进入用户数据过滤，当前用户为：【{}】", SessionUtil.getRealName());
		if (isCheck != null && isCheck - 0 == 0) {
			return "";
		}
		try {
			if (SessionUtil.hasRole("salesman") && !SessionUtil.hasRole("admin")) {
				if (!colName.contains("[userId]")) {
					return " and " + colName + " = " + SessionUtil.getUserId();
				} else {
					return colName.replace("[userId]", SessionUtil.getUserId() + "");
				}
			}
		} catch (Exception e) {
			log.error("用户过滤数据异常：{}", e);
			return "";
		}
		return "";
	}

	// 没有这些角色时 过滤数据
	public static String hasRoles(Integer isCheck, String roleCodes, String colName) {
		if (isCheck != null && isCheck - 0 == 0) {
			return "";
		}
		try {
			for (String roleCode : roleCodes.split(",")) {
				if (SessionUtil.hasRole(roleCode)) {
					String str = "";
					if (!colName.contains("[userId]")) {
						str = " and " + colName + " = " + SessionUtil.getUserId();
					} else {
						str = colName.replace("[userId]", SessionUtil.getUserId() + "");
					}
					return str;
				}
			}
		} catch (Exception e) {
			log.error("用户过滤数据异常：{}", e);
			return "";
		}
		return "";
	}

	// 没有这些角色时 过滤数据
	public static String hasNotRoles(Integer isCheck, String roleCodes, String colName) {
		if (isCheck != null && isCheck - 0 == 0) {
			return "";
		}
		try {
			boolean flag = true;
			for (String roleCode : roleCodes.split(",")) {
				flag = flag && !SessionUtil.hasRole(roleCode);
			}
			if (flag) {
				String str = "";
				if (!colName.contains("[userId]")) {
					str = " and " + colName + " = " + SessionUtil.getUserId();
				} else {
					str = colName.replace("[userId]", SessionUtil.getUserId() + "");
				}
				return str;
			}
		} catch (Exception e) {
			log.error("用户过滤数据异常：{}", e);
			return "";
		}
		return "";
	}

}
