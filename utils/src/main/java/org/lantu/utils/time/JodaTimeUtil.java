package org.lantu.utils.time;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.lantu.utils.string.StringUtil;

import java.util.Date;

/**
 * JodaTime工具
 * Created by runshu.lin on 2018/5/1.
 */
public class JodaTimeUtil {
	private JodaTimeUtil() {
	}

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeZone DATE_TIME_ZONE = DateTimeZone.forID("Asia/Shanghai");

	/**
	 * 获取当前系统的时间（毫秒级）
	 * @return
	 */
	public static long getCurrentSecondMillis() {
		return DateTimeUtils.currentTimeMillis();
	}

	/**
	 * 当前时间
	 * @return
	 */
	public static Date getCurrentDate() {
		DateTime dateTime = new DateTime();
		return dateTime.toDate();
	}

	/**
	 * 获取当天的开始时间
	 * @return
	 */
	public static long getStartOfDay() {
		return getStartOfDay(new Date());
	}

	/**
	 * 获取某天的开始时间
	 * @param date
	 * @return
	 */
	public static long getStartOfDay(Date date) {
		DateTime dateTime = new DateTime(date);
		DateTime startOfDay = dateTime.withTimeAtStartOfDay();
		return startOfDay.getMillis();
	}

	/**
	 * 获取当天的结束时间
	 * @return
	 */
	public static long getEndOfDay() {
		return getEndOfDay(new Date());
	}

	/**
	 * 获取某天的结束时间
	 * @param date
	 * @return
	 */
	public static long getEndOfDay(Date date) {
		DateTime dateTime = new DateTime(date);
		DateTime endOfDay = dateTime.millisOfDay().withMaximumValue();
		return endOfDay.getMillis();
	}

	/**
	 * 获取现在距离今天结束还有多长时间
	 * @return
	 */
	public static long endOfToday() {
		DateTime nowTime = new DateTime();
		DateTime endOfDay = nowTime.millisOfDay().withMaximumValue();
		return endOfDay.getMillis() - nowTime.getMillis();
	}

	/**
	 * 计算两个日期的相隔天数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getBetweenDay(long startTime, long endTime) {
		DateTime startDay = new DateTime(startTime);
		DateTime endDay = new DateTime(endTime);
		return Days.daysBetween(startDay, endDay).getDays();
	}

	/**
	 * 对比两个时间是否同一天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isEqualsSameDay(long startTime, long endTime) {
		DateTime startDay = new DateTime(startTime);
		DateTime endDay = new DateTime(endTime);
		Period p = new Period(startDay, endDay, PeriodType.days());
		int days = p.getDays();
		return days == 0;
	}

	/**
	 * 生成指定的时间
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param min 分
	 * @param sec 秒
	 * @param msec 毫秒
	 * @return
	 */
	public static Date generateDate(int year, int month, int day, int hour, int min, int sec, int msec) {
		DateTime dt = new DateTime(year, month, day, hour, min, sec, msec);
		return dt.toDate();
	}

	public static Date generateDate(int year, int month, int day, int hour, int min, int sec) {
		DateTime dt = new DateTime(year, month, day, hour, min, sec);
		return dt.toDate();
	}

	public static Date generateDate(int year, int month, int day) {
		LocalDate localDate = new LocalDate(year, month, day);
		return localDate.toDate();
	}

	public static Date generateDateTime(int hour, int min, int sec, int msec) {
		LocalTime localTime = new LocalTime(hour, min, sec, msec);
		return localTime.toDateTimeToday().toDate();
	}

	public static Date generateDateTime(int hour, int min, int sec) {
		LocalTime localTime = new LocalTime(hour, min, sec);
		return localTime.toDateTimeToday().toDate();
	}

	/**
	 * 格式化日期，转化为string
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateToString(Date date, String format) {
		if (StringUtil.isEmpty(format)) format = DEFAULT_FORMAT;
		DateTime dt = new DateTime(date);
		return dt.toString(format);
	}

	public static String formatDateToString(long time, String format) {
		if (StringUtil.isEmpty(format)) format = DEFAULT_FORMAT;
		DateTime dt = new DateTime(time);
		return dt.toString(format);
	}

	/**
	 * 格式化日期，转化为string（默认为：yyyy-MM-dd HH:mm:ss）
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date) {
		return formatDateToString(date, DEFAULT_FORMAT);
	}

	public static String formatDateToString(long time) {
		return formatDateToString(time, DEFAULT_FORMAT);
	}

	/**
	 * 字符串转日期
	 * @param dateTimeStr
	 * @param formatStr 转化规则
	 * @return
	 */
	public static Date parseStringToDate(String dateTimeStr, String formatStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
		DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
		return dateTime.toDate();
	}

	/**
	 * 获取星期
	 * 周日用7,周一~周六用1~6
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getDayOfWeek();
	}

	public static int getYear(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getYear();
	}

	public static int getMonth(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getMonthOfYear();
	}

	public static int getDay(Date date) {
		DateTime dt = new DateTime(date);
		return dt.getDayOfMonth();
	}

	/**
	 * 增加年
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date addYears(Date date, int year) {
		DateTime dt = new DateTime(date);
		dt = dt.plusYears(year);
		return dt.toDate();
	}

	/**
	 * 增加月
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonths(Date date, int month) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMonths(month);
		return dt.toDate();
	}

	/**
	 * 增加日
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, int day) {
		DateTime dt = new DateTime(date);
		dt = dt.plusDays(day);
		return dt.toDate();
	}

	/**
	 * 增加星期
	 * @param date
	 * @param week
	 * @return
	 */
	public static Date addWeeks(Date date, int week) {
		DateTime dt = new DateTime(date);
		dt = dt.plusWeeks(week);
		return dt.toDate();
	}

	/**
	 * 增加时
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHours(Date date, int hour) {
		DateTime dt = new DateTime(date);
		dt = dt.plusHours(hour);
		return dt.toDate();
	}

	/**
	 * 增加分
	 * @param date
	 * @param milli
	 * @return
	 */
	public static Date addMillis(Date date, int milli) {
		DateTime dt = new DateTime(date);
		dt = dt.plusMillis(milli);
		return dt.toDate();
	}

	/**
	 * 增加秒
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date addSeconds(Date date, int second) {
		DateTime dt = new DateTime(date);
		dt = dt.plusSeconds(second);
		return dt.toDate();
	}

}
