package org.lantu.utils.data;

/**
 * 通用的 key val类
 * Created by runshu.lin on 2018/2/15.
 */
public class CommonData<K, V> {
	private K key;
	private V val;

	public CommonData(K key, V val) {
		this.key = key;
		this.val = val;
	}

	public K getKey() {
		return key;
	}

	public V getVal() {
		return val;
	}

	public void setVal(V val) {
		this.val = val;
	}
}
