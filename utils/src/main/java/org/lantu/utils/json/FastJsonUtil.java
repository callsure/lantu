package org.lantu.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;
import java.util.List;

/**
 * json 工具类
 * Created by runshu.lin on 2018/4/1.
 */
public final class FastJsonUtil {
	private FastJsonUtil(){}
	/**
	 * 转换json
	 * @param o
	 * @return
	 */
	public static String toJsonString(Object o){
		return JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 得到通用的列表
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> List<T> getGeneralList(String json, Class<T> c){
		return JSON.parseArray(json, c);
	}
	/**
	 * 得到通用的对象
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T getGeneralObject(String json ,Class<T> c){
		return JSON.parseObject(json, c);
	}

	/**
	 * 泛型类型反序列化
	 * @param json
	 * @param <T>
	 * @return
	 */
	public static <T> T getGeneralTypeReferenceObject(String json, T t) {
		Type type = t.getClass();
		return JSON.parseObject(json, type);
	}
}
