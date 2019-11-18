package org.lantu.utils.logger.log;


import org.slf4j.Logger;

/**
 * Created by runshu.lin on 2018/2/14.
 */
public class LogbackLogger implements MyLogger {

	private Logger logger;

	public LogbackLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public boolean isDebugEnabled() {
		return this.logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return this.logger.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return this.logger.isTraceEnabled();
	}

	@Override
	public void debug(Object msg) {
		this.logger.debug(msg.toString());
	}

	@Override
	public void debug(Object msg, Throwable t) {
		this.logger.debug(msg.toString(), t);
	}

	@Override
	public void info(Object msg) {
		this.logger.info(msg.toString());
	}

	@Override
	public void info(Object msg, Throwable t) {
		this.logger.info(msg.toString(), t);
	}

	@Override
	public void warn(Object msg) {
		this.logger.warn(msg.toString());
	}

	@Override
	public void warn(Object msg, Throwable t) {
		this.logger.warn(msg.toString(), t);
	}

	@Override
	public void error(Object msg) {
		this.logger.error(msg.toString());
	}

	@Override
	public void error(Object msg, Throwable t) {
		this.logger.error(msg.toString(), t);
	}

	@Override
	public boolean isEnabledFor(String level) {
//		return this.logger.isEnabledFor(Level.toLevel(level));
		return false;
	}
}
