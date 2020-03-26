package com.focre.base.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnWebApplication
public class DefaultJacksonConfig extends ObjectMapper {

    private static final long serialVersionUID = -2868622492593626236L;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(getMediaTypes());
        return converter;
    }

    public List<MediaType> getMediaTypes() {
        // 设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        return list;
    }

    public DefaultJacksonConfig() {
        super();
        // 没有匹配的属性名称时不作失败处理
        this.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
        // 设置JSON按照name排序 
        this.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        // 反序列化
        // 禁止遇到空原始类型时抛出异常，用默认值代替。
        this.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 忽略json字符串中不识别的属性
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        // 允许未知的枚举解析成空值
        this.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        this.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        //单引号处理
        this.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
        // 禁止序列化空值
//        this.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 忽略无法转换的对象
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        this.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        this.configure(SerializationFeature.FLUSH_AFTER_WRITE_VALUE, true);
        // 不包含空值属性
//         this.setSerializationInclusion(Include.NON_EMPTY);
        // 是否缩放排列输出，默认false，有些场合为了便于排版阅读则需要对输出做缩放排列
        this.configure(SerializationFeature.INDENT_OUTPUT, false);
        // 时间格式处理
        this.setDateFormat(new CustomDateFormat());
    }
}
