package org.lantu.utils.http;


import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.lantu.utils.data.IKeyValueData;
import org.lantu.utils.data.KeyValueData;
import org.lantu.utils.pool.BasicPool;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * httpclient 连接池
 * Created by runshu.lin on 2018/4/1.
 */
public class HttpsClientPool extends BasicPool<HttpClient> {
	private HttpClientBuilder builder;

	public HttpsClientPool() {
		this(new KeyValueData(new HashMap()));
	}

	public HttpsClientPool(IKeyValueData keyValueData) {
		super(keyValueData);

		SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		SSLConnectionSocketFactory sslsf = null;
		try {
			sslContextBuilder.loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true);
			sslsf = new SSLConnectionSocketFactory(sslContextBuilder.build());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		this.builder = HttpClientBuilder.create();
		this.builder.setMaxConnTotal(getMaxActive());
		this.builder.setSSLSocketFactory(sslsf);
	}

	@Override
	protected HttpClient create() {
		return builder.build();
	}

	@Override
	protected void clear(HttpClient httpClient) {
		//close 后使用可能会有问题
	}

	@Override
	protected void close(HttpClient httpClient) {
		if (httpClient instanceof CloseableHttpClient) {
			try {
				((CloseableHttpClient) httpClient).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
