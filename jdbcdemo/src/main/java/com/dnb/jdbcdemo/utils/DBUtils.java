package com.dnb.jdbcdemo.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class DBUtils {

	private static Properties properties;
	
	private static Properties getProperties() {
		InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			if(inputStream!=null) {
				properties = new Properties();// used for creating the properties onbjcet
				properties.load(inputStream);//used for loading the data inside the inputstream data presenties the inputStream
				return properties;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	public static Optional<Connection> getConnection() {
		Properties properties = getProperties(); //or getProperties()
		
		try {
			Connection connection = DriverManager.getConnection(properties.getProperty("jdbc.url"), 
					properties.getProperty("jdbc.username"), 
					properties.getProperty("jdbc.password"));
			return Optional.of(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DBUtils.getProperties());
	}
	
}
