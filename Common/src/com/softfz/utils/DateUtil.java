package com.softfz.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换
 * 
 * @author Administrator
 * 
 */
public class DateUtil {

	/**
	 * 日期转字符串 先创建日期/时间格式化类： new SimpleDateFormat(pattern)，再调用格式化类的format方法。
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return 返回相应格式的日期字符串信息
	 */
	public static String dateToString(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		String dateStr = df.format(date);
		return dateStr;
	}

	/**
	 * 字符串转日期 先创建日期/时间格式化类： new SimpleDateFormat(pattern)，再调用格式化类的parse方法。
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return 返回日期
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = (Date) df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @return 返回固定格式的日期字符串信息
	 */
	public static String dateToString(Date date) {
		/* 1. 调用dateToString(Date date, "yyyy-MM-dd HH:mm:ss") */   
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 将相应格式字符串转换为日期
	 */
	public static Date stringToDate(String dateStr) {
		/* 1. 调用stringToDate(String dateStr, "yyyy-MM-dd HH:mm:ss")。 */
		return stringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");

	}
	
	/**
	 * 字符串转SqlDate类型
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static java.sql.Date toSqlDate(String dateStr, String pattern) {
		return new java.sql.Date(stringToDate(dateStr, pattern).getTime());
	}

	/**
	 * 字符串转TimeStamp类型
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static java.sql.Timestamp StringtoTimestamp(String dateStr, String pattern) {
		SimpleDateFormat sp = new SimpleDateFormat(pattern);
		java.util.Date utildate = null;
		java.sql.Timestamp timestampdate = null;
		try {
			utildate = sp.parse(dateStr);
			timestampdate = new java.sql.Timestamp(utildate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestampdate;	
	}
}
