package org.lantu.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.lantu.utils.enums.CharsetEnum;
import org.lantu.utils.json.FastJsonUtil;
import org.lantu.utils.logger.LoggerManager;
import org.lantu.utils.logger.LoggerType;
import org.lantu.utils.logger.log.MyLogger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 默认的接口实现类
 * Created by runshu.lin on 2018/4/1.
 */
public class DefaultHttpRequestHandler extends AbstracHttpRequestHandler<String> {
	private static final MyLogger logger = LoggerManager.getLogger(LoggerType.LANTU_UTILS);
	private String result;

	@Override
	public String getMethodHandler(String url, Map<String, Object> params) {
		StringBuilder sb = new StringBuilder(url);
		if (params != null && !params.isEmpty()) {
			if (sb.indexOf("?") != -1) sb.append("&");
			else sb.append("?");
			for (Map.Entry<String, Object> en : params.entrySet()) {
				sb.append(en.getKey()).append("=").append(en.getValue()).append("&");
			}
			if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	@Override
	public String cookieHandler(Map<String, Object> cookies, CharsetEnum charset) throws Exception {
		if (cookies ==  null || cookies.isEmpty()) return "";

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> en : cookies.entrySet()) {
			sb.append(en.getKey()).append("=").append(encodeUrl(String.valueOf(en.getValue()), charset)).append(";");
		}
		return sb.toString();
	}

	@Override
	public HttpEntity postMethodHandler(Map<String, Object> params) throws UnsupportedEncodingException {
		if (params != null) {
			List<NameValuePair> list = new ArrayList<>(params.size());
			for (Map.Entry<String, Object> en : params.entrySet()) {
				list.add(new BasicNameValuePair(en.getKey(), String.valueOf(en.getValue())));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Charset.forName("UTF-8"));
			return entity;
		}
		return null;
	}

	@Override
	public void errorHandler(Map<String, Object> params, Map<String, Object> cookies, int httpStatus, String url) {
		logger.error("httpstatus: ["+httpStatus+"] URL ["+url+"]");
		logger.error("params : ["+ FastJsonUtil.toJsonString(params)+"]");
		logger.error("cookies : ["+ FastJsonUtil.toJsonString(cookies)+"]");
	}

	@Override
	public void handlerResult(HttpResponse response, CharsetEnum charset) {
		try {
			this.result = EntityUtils.toString(response.getEntity(), charset.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String returnResult() {
		return result;
	}
}
