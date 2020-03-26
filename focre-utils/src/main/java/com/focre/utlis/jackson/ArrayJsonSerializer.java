package com.focre.utlis.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @description [Array统一格式化]
 * @title ArrayJsonSerializer
 * @author ye21st
 * @date 2020/3/26
 * @time 10:51 上午
 **/
public class ArrayJsonSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
	        throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeStartArray();
			gen.writeEndArray();
		} else {
			gen.writeObject(value);
		}
	}
}
