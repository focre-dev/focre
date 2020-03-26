package com.focre.base.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @ClassName: BigDecimalJsonSerializer
 * @Description: BigDecimal统一格式化
 * @author ye21st ye21st@gmail.com
 * @date 2020年03月07日16:26:27
 */
public class BigDecimalZeroJsonSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
	        throws IOException, JsonProcessingException {

		if (value == null) {
			gen.writeObject(0);
		} else {
			gen.writeObject((((BigDecimal) value).stripTrailingZeros()).toPlainString());
		}

	}

}
