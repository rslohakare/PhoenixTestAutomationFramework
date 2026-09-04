package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.pojo.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {
		// demo.json ---> src/test/resources/testData/demo.json
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		// convert the JSON Object into java Object ---> Deserialization
		// Jackson DataBind -----> ObjectMapper

		ObjectMapper objectMapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		try {
			classArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {

			
			e.printStackTrace();
		}
		return list.iterator();

	}

}
