package com.focre.utlis.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @description [BigDecimal统一格式化]
 * @title BigDecimalZeroJsonSerializer
 * @author ye21st ye21st@gmail.com
 * @date 2020/3/26
 * @time 10:52 上午
 **/
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
