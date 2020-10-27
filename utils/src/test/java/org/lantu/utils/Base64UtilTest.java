package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.encryptAndDecrypt.Base64Util;

/**
 * Created by runshu.lin on 2020/10/24.
 */
public class Base64UtilTest {

	@Test
	public void encrypt() throws Exception {
		System.out.println(Base64Util.encryptBASE64("40d98191-ac86-4e0a-a8b7-d7ec687fb41a"));
	}

	@Test
	public void decrypt() throws Exception {
		System.out.println(Base64Util.decryptBASE64("NDBkOTgxOTEtYWM4Ni00ZTBhLWE4YjctZDdlYzY4N2ZiNDFh"));
	}

}
