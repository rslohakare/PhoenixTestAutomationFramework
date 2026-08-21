package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserPOJO;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVReaderUtil {

	/*
	 * constructor is private
	 * 
	 * static- static method! job:help me read the csv file and Map it a bean
	 * 
	 * 
	 */
	private CSVReaderUtil() {

	}

	public static void loadCSV(String pathOFCSVFile) throws IOException, CsvException {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader).withType(UserPOJO.class)
				.withIgnoreEmptyLine(true).build();

		List<UserPOJO> userList = csvToBean.parse();

		System.out.println(userList.get(0).getUsername());

	}

}
