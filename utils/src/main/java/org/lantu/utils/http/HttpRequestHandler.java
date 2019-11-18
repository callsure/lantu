package org.lantu.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.lantu.utils.enums.CharsetEnum;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 数据处理接口
 * Created by runshu.lin on 2018/4/1.
 */
public interface HttpRequestHandler<T> {
	/**
	 * get 方法的处理
	 * @param params
	 * @return
	 */
	String getMethodHandler(String url, Map<String, Object> params);
	/**
	 * cookie 的处理
	 * @param cookies
	 * @return
	 */
	String cookieHandler(Map<String, Object> cookies, CharsetEnum charset)throws Exception;
	/**
	 *  post 方法的处理
	 * @param params
	 * @return
	 */
	HttpEntity postMethodHandler(Map<String, Object> params)throws UnsupportedEncodingException;
	/***
	 * 错误的处理 默认打印参数
	 * @param params
	 * @param cookies
	 * @param httpStatus
	 * @param url
	 */
	void errorHandler(Map<String, Object> params, Map<String, Object> cookies, int httpStatus, String url);
	/**
	 * 处理结果
	 * @param response
	 * @return
	 */
	void handlerResult(HttpResponse response, CharsetEnum charset);
	/**
	 * 返回结果
	 * @return
	 */
	T returnResult();
}
