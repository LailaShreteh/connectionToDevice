package com.training.databacemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC implements DatabaseManager {
	Connection conn = null;

	@Override
	public boolean connectToDatabase() {
		// JDBC driver name and database URL
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/interfaces";

		// Database credentials
		final String USER = "root";
		final String PASS = "laila";

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean insert(Object obj,String tableName) {
		boolean flag=false;
		if (DataTypes.valueOf(tableName).insert(obj)){
			flag = true;
			}
		return flag;
	
		}

	@Override
	public void delete(Object obj, String tableName) {
		
		
	}}
		
