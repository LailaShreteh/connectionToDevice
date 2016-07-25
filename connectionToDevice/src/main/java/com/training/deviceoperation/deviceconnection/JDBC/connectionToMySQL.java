package com.training.deviceoperation.deviceconnection.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

public class connectionToMySQL {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost//interfaces";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "laila";

	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("reem");
			e.printStackTrace();
		}

	}
}
