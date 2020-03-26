package com.focre.base.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final static DefaultJacksonConfig objectMapper = new DefaultJacksonConfig();

    private final static DefaultJacksonConfig soketObjectMapper = new DefaultJacksonConfig();

    public static DefaultJacksonConfig getInstance() {
        return objectMapper;
    }

    public static DefaultJacksonConfig getNonEmptyInstance() {
        // 不包含空值属性
        soketObjectMapper.setSerializationInclusion(Include.NON_EMPTY);
        return soketObjectMapper;
    }

    public static String obj2json(ObjectMapper mapper, Object obj) {
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
            Map<String, Map<String, Object>> map =
                objectMapper.readValue(jsonString, new TypeReference<Map<String, T>>() {});
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
            return (T)objectMapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            log.error("Json解析失败：{}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, TypeReference<?> type) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T)objectMapper.readValue(jsonString, type);
        } catch (Exception e) {
            log.error("Json解析失败：{}", e.getMessage());
            return null;
        }
    }

    public static <T> T map2pojo(Map<?, ?> map, Class<T> clazz) {
        try {
            return objectMapper.convertValue(map, clazz);
        } catch (Exception e) {
            log.error("Map转对象失败：{}", e.getMessage());
            return null;
        }
    }

    public static Map<?, ?> pojo2map(Object pojo) {
        try {
            return objectMapper.convertValue(pojo, Map.class);
        } catch (Exception e) {
            log.error("对象转Map失败：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 构造Collection类型.
     */
    public static JavaType contructCollectionType(
        @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     */
    public static JavaType contructMapType(@SuppressWarnings("rawtypes") Class<? extends Map> mapClass,
										   Class<?> keyClass, Class<?> valueClass) {
        return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }
}
