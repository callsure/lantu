package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.encryptAndDecrypt.MD5Util;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class MD5UtilTest {

	@Test
	public void MD5() throws Exception {
		String ss = "12345";
		System.out.println(MD5Util.MD5(ss));
		System.out.println(MD5Util.md5Encode(ss.getBytes()));
	}

}