package org.lantu.utils.properties;

import org.lantu.utils.data.KeyValueData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by runshu.lin on 2018/2/15.
 */
public abstract class LoaderProperties extends KeyValueData<Object, Object> {
	private String fileName;

	/***
	 * 要求传入的绝对地址
	 */
	public LoaderProperties(String fileName) {
		super(new HashMap<>());
		this.fileName = fileName;
		this.reload();
	}

	/**
	 * 加载指定名称的 properties
	 * @return
	 */
	private Properties load() {
		Properties properties = new Properties();
		InputStreamReader isr = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(fileName));
			isr = new InputStreamReader(fis, "UTF-8");
			properties.load(isr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isr != null) isr.close();
				if (fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	/**
	 * 重新加载
	 */
	public final void reload() {
		super.load(this.load());
		this.onReloadOver();
	}

	/**
	 * 加载完成. 如果子类需要做什么. 覆盖这个方法.
	 */
	protected void onReloadOver() {
	}
}
