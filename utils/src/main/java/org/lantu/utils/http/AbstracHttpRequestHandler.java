package org.lantu.utils.http;

import org.lantu.utils.enums.CharsetEnum;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by runshu.lin on 2019/3/5.
 */
public abstract class AbstracHttpRequestHandler<T> implements HttpRequestHandler<T> {

	/**
	 * URL编码
	 * @param input
	 */
	protected String encodeUrl(String input, CharsetEnum charset) throws Exception {
		try {
			return URLEncoder.encode(input, charset.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "";
	}

}
