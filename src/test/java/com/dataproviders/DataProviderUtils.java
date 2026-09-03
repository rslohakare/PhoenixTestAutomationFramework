package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.pojo.CreateJobPayLoad;
import com.api.utils.CSVReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayLoad> createJobFakeDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);

		Iterator<CreateJobPayLoad> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}

}
