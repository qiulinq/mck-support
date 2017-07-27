package com.xiangjia.common.helper;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 文件名称: DateHelper.java
 * </p>
 * <p>
 * 文件描述:日期工具类
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2015
 * </p>
 * <p>
 * 公 司: 重庆翔龙科技有限公司
 * </p>
 * <p>
 * 内容摘要:
 * </p>
 * <p>
 * 其他说明:
 * </p>
 * <p>
 * 完成日期：2016-5-4
 * </p>
 * 
 * @author mike
 * @version 1.0
 */
public class DateHelper {
	/**
	 * 无参构造方法
	 */
	public DateHelper() {
	}

	/**
	 * 日志对象
	 */
	private final static Logger logger = LoggerFactory.getLogger(DateHelper.class);
	/**
	 * 默认时间格式化格式
	 */
	private final static String DEFAULT_DATE_FORMART = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @return 字符串
	 */
	public static String dateToStr(Date date) {
		try {
			return dateToStr(date, DEFAULT_DATE_FORMART);
		} catch (Exception e) {
			return dateToStr(new Date());
		}
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            时间格式
	 * @return 日期
	 */
	public static String dateToStr(Date date, String format) {
		try {
			if (StringHelper.isNotEmpty(format)) {
				return new SimpleDateFormat(format).format(date);
			}
			return new SimpleDateFormat(DEFAULT_DATE_FORMART).format(date);
		} catch (Exception e) {
			return dateToStr(new Date());
		}
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            时间格式
	 * @return 日期
	 */
	public static Date strToDate(String str, String format) {
		try {
			return new SimpleDateFormat(format).parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param str
	 *            字符串
	 * @return 日期
	 */
	public static Date strToDate(String str) {
		try {
			return new SimpleDateFormat(DEFAULT_DATE_FORMART).parse(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 时间比较
	 * 
	 * @param date1
	 *            日期
	 * @param date2
	 *            日期
	 * @return 比较结果
	 */
	public static Integer compare(Date date1, Date date2) {
		Long d1 = date1.getTime();
		Long d2 = date2.getTime();

		if (d1 > d2) {
			return 1;
		} else if (d1 < d2) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 长整形转换为日期
	 * 
	 * @param timestamp
	 *            长整形日期
	 * @return 日期
	 */
	public static Date getDate(long timestamp) {
		return new Date(timestamp);
	}

	/**
	 * 添加天数
	 * 
	 * @param date
	 *            日期
	 * @param cnt
	 *            数量
	 * @return Date
	 */
	public static Date addDate(Date date, Integer cnt) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.DATE, cnt);
		return cl.getTime();
	}

	/**
	 * 添加小时
	 * 
	 * @param date
	 *            日期
	 * @param cnt
	 *            数量
	 * @return Date
	 */
	public static Date addHour(Date date, Integer cnt) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR, cnt);
		return cl.getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 日期
	 */
	private static Date getDate() {
		return new Date();
	}

	public static long subtraction(Date d2, Date d1) {
		return d2.getTime() - d1.getTime();
	}

	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static long getMisDay(Date smallDate, Date bigDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smallDate = sdf.parse(sdf.format(smallDate));
			bigDate = sdf.parse(sdf.format(bigDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smallDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bigDate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/*
	 * 毫秒转化时分秒毫秒
	 */
	public static String formatTime(Long ms) {
		if (StringHelper.isEmpty(ms)) {
			return "";
		}
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分");
		}
		if (second > 0) {
			sb.append(second + "秒");
		}
		if (milliSecond > 0) {
			// sb.append(milliSecond+"毫秒");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @Description: TODO 日期加减
	 * @param @param date 需要加减的日期
	 * @param @param addType 加减的类型 1：年份加减 2：月份加减 3：周加减 5：天加减 11小时加减
	 * @param @param addNum 需要加减的数量
	 * @author 邱林
	 * @date 2015年11月20日 下午4:46:46
	 */
	public static Date dateAdd(Date date, int addType, int addNum) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(addType, addNum);
		return gc.getTime();
	}

	public static void main(String[] args) {
		System.out.println(getMisDay(new Date(), DateHelper.strToDate("2017-09-11 23:59:59")));
	}

	public static String timestamp2Str(Timestamp time, String format) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			// 方法一
			tsStr = sdf.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	public static String timestamp2Str(Timestamp time) {
		return timestamp2Str(time, "yyyy-MM-dd HH:mm:ss");
	}
}