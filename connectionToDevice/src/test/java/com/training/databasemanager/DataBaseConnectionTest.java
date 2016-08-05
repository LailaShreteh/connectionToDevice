package com.training.databasemanager;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import com.training.databacemanager.JDBC;

/**
 * 
 * @author user
 *
 */
public class DataBaseConnectionTest {

	static JDBC JDBCConnection = new JDBC();
	Connection dataBaseConnection = null;
	private Exception exception;

	@Before
	public void setup() {
		JDBCConnection = new JDBC();
		exception = null;
	}
	
	@Test
	public void testConnectToDatabase ()
	{
		dataBaseConnection = JDBCConnection.connectToDatabase();
		assertNotNull(dataBaseConnection);
	}

	@AfterClass
	public static void teardown() throws IOException {
		if (JDBCConnection != null) {
			JDBCConnection.disconnectToDataBase();
			JDBCConnection = null;
		}
	}
}
