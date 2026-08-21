package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		/*
		 * // code to read the CSV file in java[imp interview question] File csvFile =
		 * new File(
		 * "C:\\Users\\Rahul\\eclipse-workspace\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv"
		 * ); FileReader fr = new FileReader(csvFile);
		 */

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader isr = new InputStreamReader(is);

		CSVReader csvReader = new CSVReader(isr); // CSVReader Constructor requires a reader

		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {

			System.out.print(dataArray[0]);
			System.out.println(dataArray[1]);

		}
	}

}
