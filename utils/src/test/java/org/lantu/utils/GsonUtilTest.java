package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.json.GsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by runshu.lin on 2018/12/12.
 */
public class GsonUtilTest {
	@Test
	public void gsonString() throws Exception {
		User user1 = new User(1, "lin");
		System.out.println(GsonUtil.gsonString(user1));
	}

	@Test
	public void gsonToBean() throws Exception {
		String s = "{\"id\":1,\"name\":\"lin\"}";
		System.out.println(GsonUtil.gsonToBean(s, User.class).toString());
	}

	@Test
	public void gsonToBean1() throws Exception {
		User user1 = new User(1, "lin");
		User user2 = new User(2, "sun");
		Map<String, User> map = new HashMap<>();
		map.put("1", user1);
		map.put("2", user2);
		String s = GsonUtil.gsonString(map);
		System.out.println(s);
		System.out.println(GsonUtil.gsonToBean(s, new HashMap<String, User>()).toString());
	}

	@Test
	public void gsonToBean2() throws Exception {

	}

}