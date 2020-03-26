package com.focre.utlis.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @description [BigDecimal统一格式化]
 * @title BigDecimalJsonSerializer
 * @author ye21st ye21st
 * @date 2020/3/26
 * @time 10:51 上午  
 **/
public class BigDecimalJsonSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
	        throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeObject(0.00000000);
		} else {
			gen.writeObject(String.format("%.8f", value));
		}
	}
}
