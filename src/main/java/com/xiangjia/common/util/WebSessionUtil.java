package com.xiangjia.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.xiangjia.base.model.ShiroUser;

public class WebSessionUtil {

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static ShiroUser getUser() {
		try {
			Object obj = getSubject().getPrincipal();
			if (obj == null)
				return null;
			return (ShiroUser) obj;
		} catch (Exception e) {
			return null;
		}
	}

	public static Long getUserId() {
		ShiroUser user = getUser();
		if (user == null)
			return Constants.DEFAULT_CREATE_USER_ID;
		return user.getId();
	}

	public static String getRealName() {
		ShiroUser user = getUser();
		if (user == null)
			return "";
		return user.getUserName();
	}

	public static boolean hasRole(String roleCode) {
		return getSubject().hasRole(roleCode);
	}

	public static boolean hasRoleOrAdmin(String roleCode) {
		return getSubject().hasRole(roleCode) || getSubject().hasRole("admin");
	}

	public static boolean isPermitted(String permission) {
		return getSubject().isPermitted(permission);
	}
}
