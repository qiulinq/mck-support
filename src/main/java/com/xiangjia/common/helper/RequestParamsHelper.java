package com.xiangjia.common.helper;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RequestParamsHelper {

	@SuppressWarnings({ "rawtypes" })
	public static void printAllparams(HttpServletRequest request) {
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			System.err.println("**********" + paraName + ": " + request.getParameter(paraName));
		}
	}
}
