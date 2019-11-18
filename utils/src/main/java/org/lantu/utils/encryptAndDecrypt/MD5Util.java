package org.lantu.utils.encryptAndDecrypt;

import java.security.MessageDigest;

/**
 * md5 加密
 * Created by runshu.lin on 2018/12/21.
 */
public class MD5Util {

	private MD5Util() {
	}

	private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	public static String MD5(String source) {
		return MD5(source, "utf8");
	}

	public static String MD5(String source, String charset) {
		String rt = "";
		try {
			MessageDigest mg = MessageDigest.getInstance("MD5");
			mg.update(source.getBytes(charset));
			byte[] md = mg.digest();
			char str[] = new char[md.length * 2];
			for (int i = 0, k = 0; i < md.length; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >>> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			rt = new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}

	public static String md5Encode(byte[] bytes) {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(bytes));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return String.valueOf(hexDigits[d1]) + String.valueOf(hexDigits[d2]);
	}


}
