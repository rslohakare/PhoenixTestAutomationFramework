package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.pojo.CreateJobPayLoad;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayLoad> createJobAPIDataProvider() {

		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayLoad> payloadList = new ArrayList<CreateJobPayLoad>();
		CreateJobBean tempBean;
		CreateJobPayLoad tempPayLoad;
		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayLoad = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayLoad);
		}
		return payloadList.iterator();
	}

}
