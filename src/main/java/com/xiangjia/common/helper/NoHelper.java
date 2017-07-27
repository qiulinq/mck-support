package com.xiangjia.common.helper;

import java.util.Date;

public class NoHelper {

	public static String getOrderNo() {
		return DateHelper.dateToStr(new Date(), "yyyyMMddHHmmss");
	}
}
