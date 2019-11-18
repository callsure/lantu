package org.lantu.utils.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.lantu.utils.enums.CharsetEnum;
import org.lantu.utils.enums.HttpMethodEnum;
import org.lantu.utils.exceptions.HttpMethodNotSupportException;
import org.lantu.utils.exceptions.UrlEmptyException;
import org.lantu.utils.string.StringUtil;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 需要子类指定 httpClient pool的配置信息等
 * 该类做成单例模式
 * Created by runshu.lin on 2018/4/1.
 */
public abstract class AbstractHttpUtil {
	private HttpsClientPool httpsClientPool;
	/**
	 * 设置请求和传输超时时间
	 **/
	protected RequestConfig REQUESTCONFIG;

	public AbstractHttpUtil() {
		this(new HttpsClientPool());
	}

	protected AbstractHttpUtil(HttpsClientPool httpsClientPool) {
		this.httpsClientPool = httpsClientPool;
		this.REQUESTCONFIG = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();
	}

	protected AbstractHttpUtil(HttpsClientPool httpsClientPool, RequestConfig REQUESTCONFIG) {
		this.httpsClientPool = httpsClientPool;
		this.REQUESTCONFIG = REQUESTCONFIG;
	}

	public String httpRequest(String url, Map<String, Object> params){
		return httpRequest(url, params, null);
	}

	public String httpRequest(String url, HttpMethodEnum method, Map<String, Object> params){
		return httpRequest(url, method, params, null);
	}

	public String httpRequest(String url, Map<String, Object> params, Map<String, Object> cookies) {
		return httpRequest(url, HttpMethodEnum.POST, params, cookies, DefaultHttpRequestHandler::new);
	}

	public String httpRequest(String url, HttpMethodEnum method, Map<String, Object> params, Map<String, Object> cookies) {
		return httpRequest(url, method, params, cookies, DefaultHttpRequestHandler::new);
	}

	public <T> T httpRequest(String url, HttpMethodEnum method, Map<String, Object> params, Map<String, Object> cookies, Supplier< ? extends AbstracHttpRequestHandler<T>> handler) {
		AbstracHttpRequestHandler<T> abstracHttpRequestHandler = handler.get();
		httpRequest(url, method, params, cookies, CharsetEnum.UTF_8, abstracHttpRequestHandler);
		return abstracHttpRequestHandler.returnResult();
	}

	/**
	 * 实现一个http 请求方法
	 *
	 * @param url
	 * @param method get or post
	 * @param params 参数
	 * @param cookies cookies
	 * @param charset charset utf8 default
	 * @param handler request handler
	 */
	public <T> void httpRequest(String url, HttpMethodEnum method, Map<String, Object> params, Map<String, Object> cookies, CharsetEnum charset, AbstracHttpRequestHandler<T> handler) {
		if (StringUtil.isEmpty(url)) throw new UrlEmptyException();

		HttpRequestBase request = null;
		HttpResponse response;
		HttpClient client = null;
		try {
			switch (method) {
				case GET:
					request = new HttpGet(handler.getMethodHandler(url, params));
					break;
				case POST:
					request = new HttpPost(url);
					if (params != null && !params.isEmpty()) {
						((HttpPost)request).setEntity(handler.postMethodHandler(params));
					}
					break;
				default:
					throw new HttpMethodNotSupportException(method == null ? "null" : method.toString());
			}
			if (cookies != null && !cookies.isEmpty()) {
				request.setHeader("Cookie", handler.cookieHandler(cookies, charset));
			}

			request.setConfig(REQUESTCONFIG);
			client = httpsClientPool.getFromPool();
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				handler.errorHandler(params, cookies, response.getStatusLine().getStatusCode(), url);
				return;
			}
			handler.handlerResult(response, charset);
		} catch (Exception e) {
			e.printStackTrace();
			if (request != null) {
				request.abort();
			}
		} finally {
			if(client != null){
				httpsClientPool.recycle(client);
			}
		}
	}
}
