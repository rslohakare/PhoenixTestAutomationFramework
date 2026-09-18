package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManagerOLD {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private volatile static Connection conn; // any update that happens to this conn varible all threads will be aware
												// of it

	private DatabaseManagerOLD() {

	}
     // Job of create Connection is ensure we get only DB Connection
	public static void createConnection() throws SQLException {
		if (conn == null) {// first check which all the parallel threads will enter
			synchronized (DriverManager.class) {
				if (conn == null) { // only and only for the first connection request
					conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(conn);
				}
			}
		}

	}
}
