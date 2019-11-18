package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.enums.CharsetEnum;

import java.nio.charset.Charset;

/**
 * Created by runshu.lin on 2018/12/21.
 */
public class ClassUtilTest {
	@Test
	public void test() throws Exception {
//		List<?> classs = ClassUtil.getAllSubClass(HttpRequestHandler.class, null);
//		if (classs != null) {
//			classs.forEach(cla -> System.out.println(cla.toString()));
//		}
		System.out.println(Charset.forName("utf-8") == CharsetEnum.UTF_8.getCharset());
	}
}