package org.lantu.cache;

/**
 * 缓存的移除或者新增监听接口
 * Created by runshu.lin on 2018/12/17.
 */
public interface CacheListener<K, V> {

	void onListener(K key, V value);

}
