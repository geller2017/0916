package org.smart4j.framework.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	public static String toJson(Object obj){
		String json = "";
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	} 
	
	
	public static <T> T fromJson(String json,Class<T> type){
		T t = null;
		try {
			t = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return t;
	}
}
