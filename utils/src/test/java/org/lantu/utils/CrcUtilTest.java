package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.encryptAndDecrypt.CrcUtil;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class CrcUtilTest {

	@Test
	public void getCrc32Value() throws Exception {
		System.out.println(CrcUtil.getCrc32Value("34434324".getBytes()));
	}

}