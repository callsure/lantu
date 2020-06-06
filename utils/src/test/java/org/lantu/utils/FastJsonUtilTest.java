package org.lantu.utils;

import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.lantu.utils.json.FastJsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		Map<String, User> par = FastJsonUtil.getGeneralObject(s, new TypeReference<Map<String, User>>(){});
		User user = par.get("1");
		System.out.println(user.toString());
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> map2 = new HashMap<>();
		map2.put("jj", "rrr");
		list.add(map2);
		String s2 = FastJsonUtil.toJsonString(list);
		List<Map<String, String>> list1 = FastJsonUtil.getGeneralList(s2, new TypeReference<List<Map<String, String>>>(){});
	}

}