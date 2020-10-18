package org.lantu.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

public class JacksonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		//忽略值为null的属性
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//在反序列化时忽略在json中存在但Java对象不存在的属性
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//运行空对象进行序列化
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//默认为true，当没有set方法的时候，会用get方法代替set方法
		mapper.configure(MapperFeature.USE_GETTERS_AS_SETTERS, false);
		mapper.configure(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL, true);
	}

	private JacksonUtil() {
	}

	public static <T> T toJava(String str, Class<T> clazz) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			return mapper.readValue(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T toJava(String str, TypeReference<T> typeRef) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			return (T) mapper.readValue(str, typeRef);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static <T> T toJava(String str, T typeRef) {
		return toJava(str, new TypeReference<T>() {
		});
	}

	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static ObjectMapper getObjectMapper() {
		return mapper;
	}

}
