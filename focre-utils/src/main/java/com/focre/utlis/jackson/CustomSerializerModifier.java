package com.focre.utlis.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @description []
 * @title
 * @author ye21st ye21st@gmail.com
 * @date 2020/3/26
 * @time 10:50 上午
 **/
public class CustomSerializerModifier extends BeanSerializerModifier {

	private JsonSerializer<Object> _defaultBigDecimalJsonSerializer;

	private JsonSerializer<Object> _defaultStringJsonSerializer;

	private JsonSerializer<Object> _defaultArrayJsonSerializer;

	public CustomSerializerModifier() {
		this._defaultBigDecimalJsonSerializer = new BigDecimalJsonSerializer();
		this._defaultStringJsonSerializer = new StringJsonSerializer();
		this._defaultArrayJsonSerializer = new ArrayJsonSerializer();
	}

	public CustomSerializerModifier(JsonSerializer<Object> _defaultBigDecimalJsonSerializer) {
		this._defaultBigDecimalJsonSerializer = _defaultBigDecimalJsonSerializer;
		this._defaultStringJsonSerializer = new StringJsonSerializer();
		this._defaultArrayJsonSerializer = new ArrayJsonSerializer();
	}

	public CustomSerializerModifier(JsonSerializer<Object> _defaultBigDecimalJsonSerializer,
									JsonSerializer<Object> _defaultStringJsonSerializer, JsonSerializer<Object> _defaultArrayJsonSerializer) {
		this._defaultBigDecimalJsonSerializer = _defaultBigDecimalJsonSerializer;
		this._defaultStringJsonSerializer = _defaultStringJsonSerializer;
		this._defaultArrayJsonSerializer = _defaultArrayJsonSerializer;
	}

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
													 List<BeanPropertyWriter> beanProperties) {

		// 循环所有的beanPropertyWriter
		for (int i = 0; i < beanProperties.size(); i++) {
			BeanPropertyWriter writer = beanProperties.get(i);
			// 判断字段的类型，如果是则注册对应的Serializer
			if (isBigDecimalType(writer)) {
				writer.assignSerializer(this.defaultBigDecimalJsonSerializer());
			} else if (isStringType(writer)) {
				writer.assignNullSerializer(this.defaultStringJsonSerializer());
			} else if (isArrayType(writer)) {
				writer.assignNullSerializer(this.defaultArrayJsonSerializer());
			}
		}
		return beanProperties;
	}

	// 判断是BigDecimal类型
	protected boolean isBigDecimalType(BeanPropertyWriter writer) {
		JavaType _javaType = writer.getType();
		return _javaType.hasRawClass(BigDecimal.class);

	}

	// 判断是String类型
	protected boolean isStringType(BeanPropertyWriter writer) {
		JavaType _javaType = writer.getType();
		return _javaType.hasRawClass(String.class);

	}

	// 判断是Array类型
	protected boolean isArrayType(BeanPropertyWriter writer) {
		JavaType _javaType = writer.getType();
		return _javaType.isArrayType() || _javaType.hasRawClass(List.class) || _javaType.hasRawClass(Set.class);
	}

	protected JsonSerializer<Object> defaultBigDecimalJsonSerializer() {
		return _defaultBigDecimalJsonSerializer;
	}

	protected JsonSerializer<Object> defaultStringJsonSerializer() {
		return _defaultStringJsonSerializer;
	}

	protected JsonSerializer<Object> defaultArrayJsonSerializer() {
		return _defaultArrayJsonSerializer;
	}

}
