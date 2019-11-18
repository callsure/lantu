package org.lantu.utils.http;

/**
 * 默认的http
 * Created by runshu.lin on 2018/4/1.
 */
public class DefaultHttpUtil extends AbstractHttpUtil {
	private volatile static DefaultHttpUtil instance;

	private DefaultHttpUtil() {
		if (instance != null) throw new RuntimeException("Instance Duplication!");
		instance = this;
	}

	public static DefaultHttpUtil getInstance() {
		if (instance == null) {
			synchronized (DefaultHttpUtil.class) {
				if (instance == null)
				{
					new DefaultHttpUtil();
				}
			}
		}
		return instance;
	}
}
