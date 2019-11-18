package org.lantu.utils.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用线程变量存储些东西
 * Created by runshu.lin on 2018/2/14.
 */
public class ThreadContextData {
	private ThreadContextData() {}

	private static final ThreadLocal<Map<String, Object>> servletRequestContext;

	static {
		servletRequestContext = new ThreadLocal<>();
	}

	private static Map<String, Object> getThreadParamMap() {
		Map<String, Object> paramsMapInThreadLocal = servletRequestContext.get();
		if (paramsMapInThreadLocal == null) {
			paramsMapInThreadLocal = new HashMap<String, Object>(32);
			servletRequestContext.set(paramsMapInThreadLocal);
		}
		return paramsMapInThreadLocal;
	}

	/**
	 * 查看线程是否为null
	 * @return
	 */
	public boolean isEmpty() {
		return getThreadParamMap().isEmpty();
	}

	/**
	 * 线程变量存放一个对象.
	 * @param key
	 * @param val
	 */
	public static void put(String key, Object val) {
		if (val == null) val = "NULL";
		getThreadParamMap().put(key, val);
	}

	/**
	 * 从线程变量获取一个已有对象.
	 * @param key
	 * @param <T>
	 * @return
	 */
	public static <T> T get(String key) {
		Object val = getThreadParamMap().get(key);
		if ("NULL".equals(val)) return null;
		return (T) val;
	}

	/**
	 * 移除指定的key
	 * @param key
	 */
	public static void remove(String key) {
		getThreadParamMap().remove(key);
	}

	/**
	 * 清除线程变量
	 */
	public static void removeAll() {
		servletRequestContext.remove();
	}
}
