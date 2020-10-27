package org.lantu.utils.encryptAndDecrypt;

import org.lantu.utils.logger.LoggerManager;
import org.lantu.utils.logger.LoggerType;
import org.lantu.utils.logger.log.MyLogger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by runshu.lin on 2020/10/24.
 */
public class Base64Util {

	private static final MyLogger logger = LoggerManager.getLogger(LoggerType.LANTU_UTILS);

	private Base64Util() {
	}

	/**
	 * BASE64加密
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(String key) {
		return (new BASE64Encoder()).encodeBuffer(key.getBytes());
	}

	/**
	 * BASE64解密
	 * @param key
	 * @return
	 */
	public static String decryptBASE64(String key) {
		try {
			byte[] decoded = (new BASE64Decoder()).decodeBuffer(key);
			return new String(decoded);
		} catch (IOException e) {
			logger.error("BASE64 解密:" + e);
		}
		return null;
	}
}
