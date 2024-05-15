package com.nitya.assesment.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nitya.assesment.client.GreetingService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	// JDBC URL, username, and password for connecting to the database
	static final String JDBC_URL = "jdbc:mysql://localhost:3306/user_db";
	static final String JDBC_USER = "root";
	static final String JDBC_PASSWORD = "vaseemR@111";

	@Override
	public String signUp(String username, String email, String password) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
			String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, email);
				preparedStatement.setString(3, password);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					return "success";
				} else {
					return "failure";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "failure";
		}
	}

//	@Override
//	public String login(String username, String password) {
//		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//				preparedStatement.setString(1, username);
//				preparedStatement.setString(2, password);
//				try (ResultSet resultSet = preparedStatement.executeQuery()) {
//					if (resultSet.next()) {
//						return "success";
//					} else {
//						return "failure";
//					}
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return "failure";
//		}
//	}
	
	@Override
	public String login(String username, String password) {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
	    	
	    	System.out.println("Validated User");
	        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return "success"; // User found, login successful
	                } else {
	                    return "failure"; // User not found or invalid credentials
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "failure";
	    }
	}

}
