package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.encryptAndDecrypt.AESUtil;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class AESUtilTest {
	@Test
	public void encrypt() throws Exception {
		System.out.println(AESUtil.encrypt("123", "454"));
	}

	@Test
	public void decrypt() throws Exception {
		System.out.println(AESUtil.decrypt("HVax2ofCxgQ+Qzrpw/xdFw==", "454"));
	}

}