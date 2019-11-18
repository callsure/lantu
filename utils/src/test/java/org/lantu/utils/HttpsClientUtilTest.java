package org.lantu.utils;

import org.lantu.utils.http.DefaultHttpUtil;

/**
 * Created by runshu.lin on 2018/4/1.
 */
public class HttpsClientUtilTest {

	public static void main(String[] args) {
		String url  = "https://note.youdao.com/share/?id=46cdefeb4218ee0b7ba95e754ef27321&type=note#/";
		String res = DefaultHttpUtil.getInstance().httpRequest(url, null);
		System.out.println(res);
	}

}
