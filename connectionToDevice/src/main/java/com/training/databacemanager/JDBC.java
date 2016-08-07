package com.training.databacemanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.databacemanager.exception.CRUDException;

/**
 * 
 * @author user
 *
 */

/**
 * see @com.training.databacemanager
 */
public class JDBC implements DatabaseManager {
	Connection conn = null;

	@Override
	public Connection connectToDatabase() {
		/* JDBC driver name and database URL */
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost/interfaces";

		/* Database credentials */
		final String USER = "root";
		final String PASS = "laila";

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			/* using your username and password to get a Connection object */
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
	public boolean insert(Object obj) throws CRUDException {

		// className String variable to get the name of the model class from the
		// object which want to insert.
		String className = obj.getClass().getSimpleName();

		String databaseTableName = TableName.valueOf(className).getTableName();

		try {
			DataTypesJDBC.valueOf(databaseTableName).insert(obj);
		} catch (CRUDException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// Logic
		}
		return true;
	}


	@Override
	public boolean disconnectToDataBase() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				throw new CRUDException(e.getMessage());
			} catch (CRUDException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
