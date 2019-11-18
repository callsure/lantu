package org.lantu.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 缓存构造器
 * Created by runshu.lin on 2018/12/17.
 */
public class CacheBuilder<K, V, T extends Cache<K, V>> {

	private final static int DEFAULT_MAX_SIZE = 3000;

	private final static int DEFAULT_EXPIRE_TIME_MINUTES = 30;

	/** 缓存最大的个数 */
	protected Integer maxSize = DEFAULT_MAX_SIZE;

	/** 存活时间，分钟 */
	protected Integer expireTime = DEFAULT_EXPIRE_TIME_MINUTES;

	/** 缓存元素增加监听事件 */
	protected CacheListener<K, V> addListener;

	/** 缓存元素减少监听事件 */
	protected CacheListener<K, V> removeListener;

	/** 是否长时间加载到内存里面 */
	protected boolean isPermanent;

	protected T cache;

	public CacheBuilder(T cache) {
		this.cache = cache;
	}

	/**
	 * 缓存最大的容量
	 */
	public CacheBuilder<K, V, T> setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
		return this;
	}

	/**
	 * 策略过期时间（读写获取）
	 */
	public CacheBuilder<K, V, T> setExpireTime(Integer expireTime) {
		this.expireTime = expireTime;
		return this;
	}

	public CacheBuilder<K, V, T> setAddListener(CacheListener<K, V> addListener) {
		this.addListener = addListener;
		return this;
	}

	public CacheBuilder<K, V, T> setRemoveListener(CacheListener<K, V> removeListener) {
		this.removeListener = removeListener;
		return this;
	}

	/**
	 * 常驻内存类型<忽略大小和过期时间>
	 */
	public CacheBuilder<K, V, T> setIsPermanent(boolean isPermanent) {
		this.isPermanent = isPermanent;
		return this;
	}

	public T build() {
		if (isPermanent) {
			cache.setPermanentMap(new ConcurrentHashMap<K, V>());
		} else {
			cache.setAddListener(addListener);
			cache.setRemoveListener(removeListener);
			LoadingCache<K, V> loadingCache = Caffeine.newBuilder()
					.maximumSize(maxSize)
					.expireAfterAccess(expireTime, TimeUnit.MINUTES)
					.removalListener((K k, V v, RemovalCause cause) -> cache.removalElementListener(k, v, cause))
					.build((K) -> cache.addElementListener(K));
			cache.setLoadingCache(loadingCache);
		}
		return cache;
	}

}
