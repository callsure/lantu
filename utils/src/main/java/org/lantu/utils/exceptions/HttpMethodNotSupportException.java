package org.lantu.utils.exceptions;

/**
 * Created by runshu.lin on 2018/4/1.
 */
public class HttpMethodNotSupportException extends RuntimeException {
	public HttpMethodNotSupportException() {
		super();
	}

	public HttpMethodNotSupportException(String message) {
		super(message);
	}
}
