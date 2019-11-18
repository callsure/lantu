package org.lantu.utils.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.lantu.utils.math.MathUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符处理相关的工具类
 * Created by runshu.lin on 2018/2/15.
 */
public class StringUtil {
	private StringUtil() {
	}

	/**
	 * 汉字的正则表达式
	 */
	private static final Pattern CHINESE_REGEX = Pattern.compile("([\u4E00-\u9FA5\uF900-\uFA2D]*)");

	private static final Pattern EMAIL_REGEX = Pattern.compile("(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)*", Pattern.CASE_INSENSITIVE);

	private static final Pattern MOBILE_REGEX = Pattern.compile("^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$", Pattern.CASE_INSENSITIVE);

	private static final Pattern IP_REGEX = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", Pattern.CASE_INSENSITIVE);

	private static final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/***
	 * 判断是否是空字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * 判断字符串是不是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		return NumberUtils.isCreatable(str);
	}

	/**
	 * 分割字符串
	 * 跟string.split() 略有不同. 不会按照正则表达式分割. 严格按照分隔符号分割字符串.
	 * 例:  ",".split(",").length = 0
	 * gameutil.split("," , ",").length = 2
	 *
	 * @param srcStr
	 * @param splitStr
	 * @return
	 */
	public static String[] split(String srcStr, String splitStr) {
		if (srcStr == null || srcStr.length() == 0) return new String[0];

		List<String> retList = new ArrayList<>();
		int before = 0, cursor = 0, validCursor = srcStr.length();
		boolean splitValid = false;
		for (int i = 0; i < srcStr.length(); i++) {
			if (!splitValid && srcStr.charAt(i) == splitStr.charAt(0)) {
				splitValid = true;
				cursor = i;
			}
			if (splitValid) {
				if (srcStr.charAt(i) != splitStr.charAt(i - cursor)) {
					splitValid = false;
					continue;
				}
				if (i - cursor == splitStr.length() - 1) {
					validCursor = cursor;
					retList.add(srcStr.substring(before, validCursor));
					before = i + 1;
					splitValid = false;
				}
			}
		}
		retList.add(srcStr.substring(before, srcStr.length()));
		return retList.toArray(new String[retList.size()]);
	}

	/**
	 * 字符串数组 转 基础数据类型数组
	 *
	 * @param k
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T extends Number> T[] conversion(String[] k, Class<T> t) {
		T[] tt = (T[]) Array.newInstance(t, k.length);
		try {
			for (int i = 0; i < k.length; i++) {
				Method m = t.getMethod("valueOf", String.class);
				tt[i] = (T) m.invoke(t, k[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tt;
	}

	/**
	 * 分隔字符串并转类型(字符串数组 转 基础数据类型数组)
	 *
	 * @param src
	 * @param split
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T extends Number> T[] conversion(String src, String split, Class<T> t) {
		return conversion(split(src, split), t);
	}

	/**
	 * 数组拼串
	 *
	 * @param arrays
	 * @param separator
	 * @param <T>
	 * @return
	 */
	public static <T> String arraysToString(T[] arrays, String separator) {
		return arraysToString(arrays, "", "", separator);
	}

	/**
	 * 数组拼串
	 *
	 * @param arrays
	 * @param start
	 * @param end
	 * @param separator
	 * @param <T>
	 * @return
	 */
	public static <T> String arraysToString(T[] arrays, String start, String end, String separator) {
		StringBuffer sb = new StringBuffer(start);
		if (arrays == null || arrays.length == 0) return sb.append("").append(end).toString();

		int offset = arrays.length - 1;
		for (int i = 0; i < offset; i++) {
			sb.append(arrays[i].toString()).append(separator);
		}
		return sb.append(arrays[offset].toString()).append(end).toString();
	}

	/**
	 * 返回是否符合正则表达式
	 *
	 * @param matchStr
	 * @param regex
	 * @return
	 */
	public static boolean regex(String matchStr, String regex) {
		if (StringUtil.isEmpty(matchStr)) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		return regex(matchStr, p);
	}

	/**
	 * 是否完美匹配
	 *
	 * @param matchStr
	 * @param p
	 * @return
	 */
	public static boolean regex(String matchStr, Pattern p) {
		Matcher m = p.matcher(matchStr);
		return m.matches();
	}

	/***
	 * 返回符合正则表达式的个数
	 *
	 * @param matchStr
	 * @param regex
	 * @return
	 */
	public static int regexCount(String matchStr, String regex) {
		if (StringUtil.isEmpty(matchStr)) {
			return 0;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(matchStr);
		if (m.find()) {
			return m.groupCount();
		}
		return 0;
	}

	/**
	 * 是否是汉字
	 *
	 * @param matchStr
	 * @return
	 */
	public static boolean regexChinese(String matchStr) {
		return regex(matchStr, CHINESE_REGEX);
	}

	/**
	 * 是否是电子邮箱
	 *
	 * @param matchStr
	 * @return
	 */
	public static boolean isEmail(String matchStr) {
		return regex(matchStr, EMAIL_REGEX);
	}

	/**
	 * 手机号码
	 *
	 * @param matchStr
	 * @return
	 */
	public static boolean isMobile(String matchStr) {
		return regex(matchStr, MOBILE_REGEX);
	}

	/**
	 * ip地址
	 *
	 * @param matchStr
	 * @return
	 */
	public static boolean isIp(String matchStr) {
		return regex(matchStr, IP_REGEX);
	}

	public static String getIntHexVal(int val) {
		return String.format("%08x", val).toUpperCase();
	}

	public static String getByteHexVal(byte val) {
		return String.format("%02x", val).toUpperCase();
	}

	public static String getShortHexVal(short val) {
		return String.format("%04x", val).toUpperCase();
	}

	/**
	 * 生成一定长度的随机字符串
	 *
	 * @param count
	 * @return
	 */
	public static String randomString(int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(chars.charAt(MathUtil.random(chars.length())));
		}
		return sb.toString();
	}

}
