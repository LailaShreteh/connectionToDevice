package com.training.databacemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.databacemanager.exception.CRUDException;

public class JDBC implements DatabaseManager {
	Connection conn = null;

	@Override
	public Connection connectToDatabase() {
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
			String sql = "SET FOREIGN_KEY_CHECKS=0";
			try {
				Statement stmt = null;
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	@Override
	public boolean insert(Object obj) throws CRUDException{
		String t = obj.getClass().getSimpleName();
		String table=DataTypes.valueOf(t).toString();
		//System.out.println(tableName);
		 
		  try {
			DataTypes.valueOf(table).insert(obj);
		 } catch( CRUDException e) {
			 e.printStackTrace();
			 throw e;
		 }finally {
			//Logic 
		 }
		 
		 return true;
	}

	@Override
	public void delete(Object obj, String tableName) {

	}
}
