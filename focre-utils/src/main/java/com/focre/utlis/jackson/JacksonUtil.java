package com.focre.utlis.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JacksonUtil {

	private static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);

	private static ObjectMapper objectMapper = null;

	public static ObjectMapper getInstance() {
		// 不包含空值属性
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		return objectMapper;
	}
	
	public static ObjectMapper getInstance(Include include) {
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		return objectMapper;
	}
	
	static {
		objectMapper = new ObjectMapper();
		// 没有匹配的属性名称时不作失败处理
		objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
		// 反序列化
		// 禁止遇到空原始类型时抛出异常，用默认值代替。
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		// 忽略json字符串中不识别的属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
		// 允许未知的枚举解析成空值
		objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
		objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		// 忽略无法转换的对象
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
		objectMapper.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
		// 不包含空值属性
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		// 是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		// 时间格式处理
		// objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setDateFormat(new CustomDateFormat());
	}

	public String obj2json(ObjectMapper mapper, Object obj) {
		if (null == obj) {
			return null;
		}

		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}

	}

	public static String obj2json(Object obj) {
		if (null == obj) {
			return null;
		}

		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}

	}

	public static <T> T json2pojo(String jsonString, Class<T> clazz) {

		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return objectMapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2map(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString,
			        new TypeReference<Map<String, T>>() {});
			Map<String, T> result = new HashMap<String, T>();
			for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
				result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
			}
			return result;
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	public static <T> List<T> json2list(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			List<Map<String, Object>> list = objectMapper.readValue(jsonString, new TypeReference<List<T>>() {});
			List<T> result = new ArrayList<T>();
			for (Map<String, Object> map : list) {
				result.add(map2pojo(map, clazz));
			}
			return result;
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) objectMapper.readValue(jsonString, javaType);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T fromJson(String jsonString, TypeReference type) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) objectMapper.readValue(jsonString, type);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		try {
			return objectMapper.convertValue(map, clazz);
		} catch (Exception e) {
			log.error("Json解析失败：{}", e.getMessage());
			return null;
		}
	}

	/**
	 * 构造Collection类型.
	 */
	@SuppressWarnings("rawtypes")
	public static JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * 构造Map类型.
	 */
	@SuppressWarnings("rawtypes")
	public static JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

}
