package com.focre.base.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @ClassName: ArrayJsonSerializer
 * @Description: Array统一格式化
 * @author ye21st ye21st@gmail.com
 * @date 2020年03月07日16:26:37
 */
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
