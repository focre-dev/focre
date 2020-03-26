/**
 * @Title: BigDecimalSerializerModifier.java
 * @Description: TODO
 * @author ye21st 2020年03月07日16:26:50
 * @date 2018年4月9日 下午9:49:17
 * @version V3.0
 */
package com.focre.base.jackson;

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
 * @ClassName: BigDecimalSerializerModifier
 * @Description: TODO
 * @author ye21st ye21st@gmail.com
 * @date 2020年03月07日16:27:05
 */
public class CustomSerializerModifier extends BeanSerializerModifier {

	private JsonSerializer<Object> defaultBigDecimalJsonSerializer;

	private JsonSerializer<Object> defaultStringJsonSerializer;

	private JsonSerializer<Object> defaultArrayJsonSerializer;

	public CustomSerializerModifier() {
		this.defaultBigDecimalJsonSerializer = new BigDecimalJsonSerializer();
		this.defaultStringJsonSerializer = new StringJsonSerializer();
		this.defaultArrayJsonSerializer = new ArrayJsonSerializer();
	}

	public CustomSerializerModifier(JsonSerializer<Object> defaultBigDecimalJsonSerializer) {
		this.defaultBigDecimalJsonSerializer = defaultBigDecimalJsonSerializer;
		this.defaultStringJsonSerializer = new StringJsonSerializer();
		this.defaultArrayJsonSerializer = new ArrayJsonSerializer();
	}

	public CustomSerializerModifier(JsonSerializer<Object> defaultBigDecimalJsonSerializer,
									JsonSerializer<Object> defaultStringJsonSerializer, JsonSerializer<Object> defaultArrayJsonSerializer) {
		this.defaultBigDecimalJsonSerializer = defaultBigDecimalJsonSerializer;
		this.defaultStringJsonSerializer = defaultStringJsonSerializer;
		this.defaultArrayJsonSerializer = defaultArrayJsonSerializer;
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

	/**判断是BigDecimal类型 */
	protected boolean isBigDecimalType(BeanPropertyWriter writer) {
		JavaType javaType = writer.getType();
		return javaType.hasRawClass(BigDecimal.class);

	}

	/**判断是String类型*/
	protected boolean isStringType(BeanPropertyWriter writer) {
		JavaType javaType = writer.getType();
		return javaType.hasRawClass(String.class);

	}

	/**
	 * 判断是Array类型
	 */
	protected boolean isArrayType(BeanPropertyWriter writer) {
		JavaType javaType = writer.getType();
		return javaType.isArrayType() || javaType.hasRawClass(List.class) || javaType.hasRawClass(Set.class);
	}

	protected JsonSerializer<Object> defaultBigDecimalJsonSerializer() {
		return defaultBigDecimalJsonSerializer;
	}

	protected JsonSerializer<Object> defaultStringJsonSerializer() {
		return defaultStringJsonSerializer;
	}

	protected JsonSerializer<Object> defaultArrayJsonSerializer() {
		return defaultArrayJsonSerializer;
	}

}
