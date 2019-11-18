package org.lantu.utils.logger;

import org.lantu.utils.logger.log.Log4j1Logger;
import org.lantu.utils.logger.log.Log4j2Logger;
import org.lantu.utils.logger.log.LogbackLogger;
import org.lantu.utils.logger.log.MyLogger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by runshu.lin on 2018/2/14.
 */
public final class LoggerManager {
	private static final Map<String, MyLogger> loggerMap = new ConcurrentHashMap<>();

	private static final boolean use_log4j2; //   是否使用log4j2 了
	private static final boolean use_logback; //  是否使用logback 了

	static {
		use_log4j2 = LoggerManager.class.getResource("/log4j2.xml") != null;
		use_logback = LoggerManager.class.getResource("logback.xml") != null;
	}

	private LoggerManager() {}

	/**
	 * 得到自己的日志
	 * @param loggerType
	 * @return
	 */
	public static MyLogger getLogger(LoggerType loggerType) {
		return getLogger(loggerType.name());
	}

	/**
	 * 得到自己的日志
	 * @param clazz
	 * @return
	 */
	public static MyLogger getLogger(Class clazz) {
		return getLogger(clazz.getName());
	}

	/**
	 * 得到自己的日志
	 * @param loggerName
	 * @return
	 */
	public static MyLogger getLogger(String loggerName) {
		if (! loggerMap.containsKey(loggerName)) {
			if (use_logback) {
				org.slf4j.Logger logger = LoggerFactory.getLogger(loggerName);
				loggerMap.put(loggerName, new LogbackLogger(logger));
			} else if (use_log4j2) {
				org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(loggerName);
				loggerMap.put(loggerName, new Log4j2Logger(logger));
			} else {
				org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(loggerName);
				loggerMap.put(loggerName, new Log4j1Logger(logger));
			}
		}
		return loggerMap.get(loggerName);
	}
}
