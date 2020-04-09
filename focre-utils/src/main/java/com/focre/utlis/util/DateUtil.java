package com.focre.utlis.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 时间操作类
 * @Author ye21st
 * @Date 2020/4/9 5:39 下午:44
 */
public class DateUtil {

	/**
	 * @description [通过字符串转化为LocalDateTime]
	 * @title formatDate
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:43 下午
	 * @param date [时间格式字符串]
	 * @return java.time.LocalDateTime
	 **/
	public static LocalDateTime formatDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(date, formatter);
	}

	/**
	 * @description [通过字符串转化为LocalDateTime]
	 * @title formatDate
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:44 下午
	 * @param date [时间格式字符串]
	 * @param pattern [格式化字符串]
	 * @return java.time.LocalDateTime
	 **/
	public static LocalDateTime formatDate(String date, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(date, formatter);
	}

	/**
	 * @description [输出标准时间字符串]
	 * @title formatDate
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:51 下午
	 * @return java.lang.String 时间字符串
	 **/
	public static String formatDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.now();
		return dateTime.format(formatter);
	}

	/**
	 * @description [输出时间字符串]
	 * @title formatDate
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:56 下午
	 * @param localDateTime [日期参数]
	 * @return java.lang.String 时间字符串
	 **/
	public static String formatDate(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return localDateTime.format(formatter);
	}

	/**
	 * @description [输出时间字符串]
	 * @title formatDate
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:56 下午
	 * @param localDateTime [日期参数]
	 * @param pattern [格式化时间字符串]
	 * @return java.lang.String
	 **/
	public static String formatDate(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}

	/**
	 * @description [输出时间字符串]
	 * @title dateString
	 * @author ye21st
	 * @date 2020/4/9
	 * @time 5:59 下午
	 * @return java.lang.String
	 **/
	public static String formatAllDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return LocalDateTime.now().format(formatter);
	}
}
