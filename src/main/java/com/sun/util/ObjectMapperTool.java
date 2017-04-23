package com.sun.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * json和对象转换工具类
 */
public class ObjectMapperTool {

	public final static ObjectMapper mapper = new ObjectMapper();
	public final static JsonNodeFactory jsonNodeFactory=JsonNodeFactory.instance;
	static { // if you need to change default configuration:
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// don't see any dynamic type usage as of now, set this to true
		// performance gain
		// mapper.configure(SerializationConfig.Feature.USE_STATIC_TYPING,
		// true);
		// will not print null property any more~~
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	public static Object fromJson(String json,
			Class<? extends Serializable> clazz) {

		Object result = null;
		try {
			result = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return result;
	}

	public static String toJson(Object createRequest) {
		String result = null;
		try {
			result = mapper.writeValueAsString(createRequest);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String toJsonNotDefault(Object createRequest) {
		String result = null;
		try {
			mapper.setSerializationInclusion(Include.NON_DEFAULT);
			result = mapper.writeValueAsString(createRequest);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
