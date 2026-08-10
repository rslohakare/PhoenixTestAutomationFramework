package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// WAP read the properties file from src/test/resourse/config/config.properties

	// create object of properties class
	private static Properties prop = new Properties();

	private static String path = "config/config.properties";

	private static String env;

	private ConfigManager() {
		// private constructor
	}

	static {

		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		System.out.println("Running tests in Env=" + env);
		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			throw new RuntimeException("Can not read the file at path " + path);
		}

		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Loading resource: " + path);

		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		System.out.println("ClassLoader = " + cl);

		InputStream input1 = cl.getResourceAsStream(path);

		System.out.println("InputStream = " + input1);
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}

}
