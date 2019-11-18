package org.lantu.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.lantu.utils.string.StringUtil;

import java.lang.reflect.Type;

/**
 * Created by runshu.lin on 2018/12/12.
 */
public final class GsonUtil {

	private static Gson gson = null;

	static {
		if (gson == null) {
			//当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		}
	}

	private GsonUtil() {
	}

	/**
	 * 将对象转成json格式
	 *
	 * @param object
	 * @return String
	 */
	public static String gsonString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}

	/**
	 * 将json转成特定的cls的对象
	 *
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> T gsonToBean(String gsonString, Class<T> cls) {
		if (StringUtil.isEmpty(gsonString)) {
			return null;
		}
		T t = null;
		if (gson != null) {
			//传入json对象和对象类型,将json转成对象
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	public static <T> T gsonToBean(String gsonString, TypeToken<T> typeToken) {
		if (StringUtil.isEmpty(gsonString)) {
			return null;
		}
		T t = null;
		if (gson != null) {
			//根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
			t = gson.fromJson(gsonString, typeToken.getType());
		}
		return t;
	}

	public static <T> T gsonToBean(String gsonString, T t) {
		Type superclass = t.getClass();
		return gson.fromJson(gsonString, superclass);
	}


}
