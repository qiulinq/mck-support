package com.xiangjia.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static String FINAL_CN = "yyyy/MM/dd HH:mm:ss";

	public static String formatDate(Date date) {
		return new SimpleDateFormat(FINAL_CN).format(date);
	}

	public static String formatDate1(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 
	 * @param date
	 * @param sec
	 * @return
	 */
	public static String getLasSec(Date date, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, sec);
		Date dateNew = cal.getTime();
		return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(dateNew);
	}

	/**
	 * 
	 * @param date
	 * @param sec
	 * @return
	 */
	public static String getLasDate(Date date, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, sec);
		Date dateNew = cal.getTime();
		return new SimpleDateFormat("yyyy/MM/dd").format(dateNew);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static Date paseDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
