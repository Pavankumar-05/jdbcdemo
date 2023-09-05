package com.dnb.jdbcdemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DBUtils {

	public DBUtils() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private Environment environment;

	public Optional<Connection> getConnection() {

		try {
			Connection connection = DriverManager.getConnection(environment.getProperty("jdbc.url"),
					environment.getProperty("jdbc.username"),
					environment.getProperty("jdbc.password"));
			return Optional.of(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
