package org.lantu.utils.pool;

import org.lantu.utils.data.IKeyValueData;
import org.lantu.utils.exceptions.PoolException;
import org.lantu.utils.logger.LoggerManager;
import org.lantu.utils.logger.LoggerType;
import org.lantu.utils.logger.log.MyLogger;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 池的配置
 * Created by runshu.lin on 2018/2/15.
 */
public abstract class BasicPool<T> implements Pool<T> {
	protected MyLogger logger = LoggerManager.getLogger(LoggerType.LANTU_UTILS);

	/** 锁*/
	private Lock lock;

	private String simpleClassName;

	/** 最大空闲 多余的对象.不再回收.*/
	private int maxIdle=50;
	/** 最小空闲 将创建 initSize - minIdel 个*/
	private int minIdle=10;
	/**如果 超出最大活跃, 则打印日志.*/
	private int maxActive=100;
	/**如果超时则返回null*/
	private int maxWaitTimeout=3000;

	/**空闲的. 需要在里面去一个 没有就创建*/
	private LinkedBlockingQueue<T> idles = new LinkedBlockingQueue();
	/**活跃的数量*/
	private AtomicInteger activeCount = new AtomicInteger();

	public BasicPool(IKeyValueData keyValueData) {
		this.lock = new ReentrantLock();

		this.simpleClassName = getClass().getSimpleName();

		this.maxIdle = keyValueData.getInt(getKey("maxIdle"), 30);
		this.minIdle = keyValueData.getInt(getKey("minIdle"), 5);
		this.maxActive = keyValueData.getInt(getKey("maxActive"), 200);
		this.maxWaitTimeout = keyValueData.getInt(getKey("maxWaitTimeout"), 3000);
	}

	/**
	 * 创建T
	 * @return
	 */
	protected abstract T create();
	/***
	 * 有需要释放的操作.
	 * @param t
	 */
	protected abstract void clear(T t);
	/**
	 * 关闭对象.
	 * @param t
	 */
	protected abstract void close(T t);

	@Override
	public T getFromPool() {
		T t = null;
		if (idles.size() < minIdle && (idles.size() + activeCount.get()) < maxActive) {
			t = this.create();
		}
		if (t == null) {
			try {
				t = idles.poll(maxWaitTimeout, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				logger.error("getFromPool wait timeout ");
				e.printStackTrace();
			}
		}
		if (t == null) {
			throw new PoolException("ActiveCount ["+getActiveCount()+"] is already max ");
		}else {
			activeCount.incrementAndGet();
		}
		return t;
	}

	@Override
	public void recycle(T t) {
		if (t == null) return;
		try {
			lock.lock();
			activeCount.decrementAndGet();
			if (idles.size() < maxIdle) {
				//只有小于最小空闲时
				this.clear(t);
				idles.add(t);
			} else {
				clear(t);
			}
		} finally {
			lock.unlock();
		}
	}

	/***
	 * 得到对应的key
	 * @param subKey  子key
	 * @return  返回真实的key
	 */
	private String getKey(String subKey){
		return simpleClassName+"."+subKey;
	}

	@Override
	public int getMaxIdle() {
		return maxIdle;
	}

	@Override
	public int getMinIdle() {
		return minIdle;
	}

	@Override
	public int getMaxActive() {
		return maxActive;
	}

	@Override
	public int getMaxWaitTimeout() {
		return maxWaitTimeout;
	}

	@Override
	public int getIdelCount() {
		return idles.size();
	}

	@Override
	public int getActiveCount() {
		return activeCount.get();
	}

	@Override
	public String toString() {
		return "IdleCount["+getIdelCount()+"] ActiveCount["+getActiveCount()+"]";
	}
}
