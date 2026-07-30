package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
	// WAP read the properties file from src/test/resourse/config/config.properties

	// create object of properties class
	private static Properties prop = new Properties();

	private ConfigManagerOld() {
		//private constructor
	}
	static {
		// use of static block to Load the Properties file in memory
		//static block it will executed once during class loading time
		File configFile = new File(
				System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+
		File.separator+"resources"+File.separator+"Config"+File.separator+"config.properties");
		FileReader fileReader = null;
		try {
			// read the file
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) throws IOException {

		return prop.getProperty("BASE_URI");

	}

}
