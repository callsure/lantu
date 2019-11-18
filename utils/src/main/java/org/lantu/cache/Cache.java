package org.lantu.cache;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import org.lantu.utils.logger.LoggerManager;
import org.lantu.utils.logger.LoggerType;
import org.lantu.utils.logger.log.MyLogger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存结构实体
 * Created by runshu.lin on 2018/12/17.
 */
public abstract class Cache<K, V> {

	private static final MyLogger logger = LoggerManager.getLogger(LoggerType.LANTU_UTILS);

	private LoadingCache<K, V> loadingCache;

	private CacheListener<K, V> addListener;

	private CacheListener<K, V> removeListener;

	private ConcurrentHashMap<K, V> permanentMap;

	/**
	 * 元素增加事件监听处理
	 */
	protected V addElementListener(K key) {
		V value = load(key);
		if (addListener != null) {
			addListener.onListener(key, value);
		}
		return value;
	}

	/**
	 * 元素移除事件监听处理
	 */
	protected void removalElementListener(K key, V value, RemovalCause cause) {
		if (removeListener != null) {
			removeListener.onListener(key, value);
		}
		logger.info("key:" + key + ",value:" + value + "," + cause);
	}

	protected abstract V load(K k);

	public V get(K k) {
		if (permanentMap != null) {
			return permanentMap.get(k);
		}
		return loadingCache.get(k);
	}

	/**
	 * 移除数据的方法
	 */
	public void remove(K k) {
		if (permanentMap != null) {
			permanentMap.remove(k);
		} else {
			loadingCache.invalidate(k);
		}
	}

	/**
	 * 比较缓存数据是否存在
	 */
	public boolean containsKey(K k) {
		if (permanentMap != null) {
			return permanentMap.containsKey(k);
		}
		return loadingCache.asMap().containsKey(k);
	}

	public LoadingCache<K, V> getLoadingCache() {
		return loadingCache;
	}

	public void setLoadingCache(LoadingCache<K, V> loadingCache) {
		this.loadingCache = loadingCache;
	}

	public CacheListener<K, V> getAddListener() {
		return addListener;
	}

	public void setAddListener(CacheListener<K, V> addListener) {
		this.addListener = addListener;
	}

	public CacheListener<K, V> getRemoveListener() {
		return removeListener;
	}

	public void setRemoveListener(CacheListener<K, V> removeListener) {
		this.removeListener = removeListener;
	}

	public ConcurrentHashMap<K, V> getPermanentMap() {
		return permanentMap;
	}

	public void setPermanentMap(ConcurrentHashMap<K, V> permanentMap) {
		this.permanentMap = permanentMap;
	}

	public void put(K key, V value) {
		if (permanentMap != null) {
			permanentMap.put(key, value);
		} else {
			loadingCache.put(key, value);
		}
	}

}
