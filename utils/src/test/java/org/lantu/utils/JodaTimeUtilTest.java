package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.time.JodaTimeUtil;

import java.util.Date;

/**
 * Created by runshu.lin on 2018/5/1.
 */
public class JodaTimeUtilTest {
	@Test
	public void getCurrentSecondMillis() throws Exception {
		System.out.println(JodaTimeUtil.formatDateToString(System.currentTimeMillis()));
		System.out.println(JodaTimeUtil.formatDateToString(JodaTimeUtil.getCurrentDate()));
	}

	@Test
	public void getStartOfDay() throws Exception {
		System.out.println(JodaTimeUtil.formatDateToString(JodaTimeUtil.getStartOfDay()));
	}

	@Test
	public void getEndOfDay() throws Exception {
		System.out.println(JodaTimeUtil.formatDateToString(JodaTimeUtil.getEndOfDay()));
	}

	@Test
	public void getBetweenDay() throws Exception {
		Date date1 = JodaTimeUtil.generateDate(2018, 5, 1);
		Date date2 = JodaTimeUtil.generateDate(2018, 5, 4);
		System.out.println(JodaTimeUtil.getBetweenDay(date1.getTime(), date2.getTime()));
	}

	@Test
	public void isEqualsSameDay() throws Exception {
		Date date1 = JodaTimeUtil.generateDate(2018, 5, 4, 23, 59, 59);
		Date date2 = JodaTimeUtil.generateDate(2018, 5, 4);
		System.out.println(JodaTimeUtil.isEqualsSameDay(date1.getTime(), date2.getTime()));
	}

	@Test
	public void parseStringToDate() throws Exception {
		String s = "20181212";
		System.out.println(JodaTimeUtil.parseStringToDate(s, "yyyyMMdd"));
	}

	@Test
	public void getWeek() throws Exception {
		String s = "20181020";
		Date yyyyMMdd = JodaTimeUtil.parseStringToDate(s, "yyyyMMdd");
		System.out.println(JodaTimeUtil.getDay(yyyyMMdd));
	}

	@Test
	public void addYears() throws Exception {
		Date date = JodaTimeUtil.addYears(new Date(), -1);
		System.out.println(JodaTimeUtil.formatDateToString(date));
	}
}