package org.lantu.utils.gzip;

import org.lantu.utils.logger.LoggerManager;
import org.lantu.utils.logger.LoggerType;
import org.lantu.utils.logger.log.MyLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 压缩、解压缩工具类
 * Created by runshu.lin on 2018/12/21.
 */
public class GzipUtil {

	private static final MyLogger logger = LoggerManager.getLogger(LoggerType.LANTU_UTILS);

	private GzipUtil() {
	}

	/**
	 * 对字节流进行压缩
	 * @param bytes 要压缩的字节码数组
	 * @return
	 */
	public static byte[] zip(byte[] bytes) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
		GZIPOutputStream zos = null;
		try {
			zos = new GZIPOutputStream(bos);
			zos.write(bytes);
		} catch (IOException e) {
			logger.error("压缩错误：" + e);
		} finally {
			try {
				if (zos != null) zos.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}

	/**
	 * 解压缩
	 * @param bytes
	 * @return
	 */
	public static byte[] unzip(byte[] bytes) {
		byte [] rbytes = new byte[1024];
		int num = 0;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		GZIPInputStream zis = null;

		try {
			zis = new GZIPInputStream(bis);
			while ((num = zis.read(rbytes, 0, rbytes.length)) != -1) {
				bos.write(rbytes, 0, num);
			}
		} catch (IOException e) {
			logger.error("解压缩错误：" + e);
		} finally {
			try {
				bos.close();
				bis.close();
				if (zis != null) zis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}
}
