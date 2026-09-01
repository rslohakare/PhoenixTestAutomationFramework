package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
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

//	public static Iterator<UserBean> loadCSV(String pathOFCSVFile) {
//
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
//		InputStreamReader isr = new InputStreamReader(is);
//
//		CSVReader csvReader = new CSVReader(isr);
//
//		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader).withType(UserBean.class)
//				.withIgnoreEmptyLine(true).build();
//
//		List<UserBean> userList = csvToBean.parse();
//
//		return userList.iterator();
//
//	}

	public static <T> Iterator<T> loadCSV(String pathOFCSVFile, Class<T> bean) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOFCSVFile);
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader).withType(bean).withIgnoreEmptyLine(true)
				.build();

		List<T> list = csvToBean.parse();

		return list.iterator();

	}

}
