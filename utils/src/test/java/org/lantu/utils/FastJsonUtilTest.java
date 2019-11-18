package org.lantu.utils;

import org.junit.Test;
import org.lantu.utils.json.FastJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by runshu.lin on 2018/4/8.
 */
public class FastJsonUtilTest {

	@Test
	public void jsonTest() throws Exception {
		User user1 = new User(1, "lin");
		User user2 = new User(2, "sun");
		Map<String, User> map = new HashMap<>();
		map.put("1", user1);
		map.put("2", user2);
		String s = FastJsonUtil.toJsonString(map);
		System.out.println(s);
//		Map<String, User> par = JSON.parseObject(s, new TypeReference<Map<String, User>>() {});
		Map<String, User> par = FastJsonUtil.getGeneralTypeReferenceObject(s, new HashMap<String, User>());
		System.out.println(par.get("1").toString());
	}

}