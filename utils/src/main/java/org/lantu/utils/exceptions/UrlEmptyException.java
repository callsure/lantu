package org.lantu.utils.exceptions;

/**
 * Created by runshu.lin on 2018/4/1.
 */
public class UrlEmptyException extends RuntimeException {
	public UrlEmptyException() {
		super();
	}

	public UrlEmptyException(String message) {
		super(message);
	}
}
