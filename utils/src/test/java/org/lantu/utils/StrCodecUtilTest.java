package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.encryptAndDecrypt.StrCodecUtil;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class StrCodecUtilTest {
	@Test
	public void encrypt() throws Exception {
		String ss = "12345";
		System.out.println(StrCodecUtil.encrypt(ss));
	}

	@Test
	public void decrypt() throws Exception {
		String ss = "3132333435";
		System.out.println(StrCodecUtil.decrypt(ss));
	}
}