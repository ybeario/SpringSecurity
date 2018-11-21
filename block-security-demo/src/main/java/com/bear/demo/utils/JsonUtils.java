package com.bear.demo.utils;
/**
* @author Y.bear
* @version 创建时间：2018年11月20日 下午2:53:01
* 类说明
*/

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String ToJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	public static <T> T fromJson(String jsonString, Class<T> valueType)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(jsonString, valueType);
	}
}
