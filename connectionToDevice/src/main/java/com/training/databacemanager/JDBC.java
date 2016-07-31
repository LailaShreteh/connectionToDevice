package com.training.databacemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBC implements DatabaseManager {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/interfaces";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "laila";
	static Connection conn = null;

	public static void insert(String table,String coloums ,String values) throws SQLException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = null;
		stmt = conn.createStatement();
		String sql = "INSERT INTO " + table +"("+ coloums +") VALUES (" + values+")";
		
		stmt.executeUpdate(sql);
		conn.close();
	}

	public static void select(String TableName) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + TableName);
			while (rs.next()) {
				String name = rs.getString("name");
				String adminStatus = rs.getString("adminStatus");
				String operationalStatus = rs.getString("operationalStatus");
				int MTU = rs.getInt("MTU");
				String duplexMode = rs.getString("duplex");
				String ifSpeed = rs.getString("speed");
				String macAddress = rs.getString("macAddress");

				System.out.println("**" + name + " || \t" + adminStatus + " || \t" + operationalStatus + " || \t" + MTU
						+ " || \t" + duplexMode + " || \t" + ifSpeed + " || \t" + macAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void delete(String name, String TableName) {
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + TableName + "where name = '" + name + "'");
			// select();
			System.out.println("\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void update(String name, String newName) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("Update interface set name = '" + newName + "'" + "where name = '" + name + "'");
			// select();
			System.out.println("\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}