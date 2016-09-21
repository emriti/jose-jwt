package com.ecomindo.common.util;

import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.ecomindo.common.exception.DAOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private static Logger logger = Logger.getLogger(JsonConverter.class);

	public static Object convertStrToPojo(String str, Class<?> pojoClass) {
		Object result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.readValue(str, pojoClass);
		} catch (Exception e) {
			throw new DAOException(logger, e.getMessage());
		}

		return result;
	}

	public static String convertPojoToStr(Object pojoObj) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, pojoObj);
			result = writer.toString();
		} catch (Exception e) {
			throw new DAOException(logger, e.getMessage());
		}
		return result;
	}
}
