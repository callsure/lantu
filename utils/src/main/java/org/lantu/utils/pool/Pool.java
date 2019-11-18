package org.lantu.utils.pool;

/**
 * Created by runshu.lin on 2018/2/15.
 */
public interface Pool<T> {
	/**
	 * 从池里拿出来一个
	 *
	 * @return
	 */
	T getFromPool();

	/**
	 * 回收对象
	 *
	 * @param t
	 */
	void recycle(T t);

	/**
	 * 得到最大的空闲值
	 *
	 * @return 值
	 */
	int getMaxIdle();

	/***
	 * 得到允许的最小空闲
	 * @return 值
	 */
	int getMinIdle();

	/**
	 * 得到允许的最大活跃
	 *
	 * @return 值
	 */
	int getMaxActive();

	/**
	 * 得到等待超时时间 ms
	 *
	 * @return
	 */
	int getMaxWaitTimeout();

	/**
	 * 返回idel count
	 *
	 * @return
	 */
	int getIdelCount();

	/**
	 * 得到当前活跃数
	 *
	 * @return 值
	 */
	int getActiveCount();

	/**
	 * 返回打印
	 *
	 * @return
	 */
	String toString();
}
