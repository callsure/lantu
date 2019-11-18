package org.lantu.utils.encryptAndDecrypt;

import java.util.zip.CRC32;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class CrcUtil {

	private CrcUtil() {
	}

	/**
	 * 得到crc32计算的crc值
	 * @param bytes
	 * @return
	 */
	public static long getCrc32Value(byte [] bytes) {
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		return crc32.getValue();
	}
}
