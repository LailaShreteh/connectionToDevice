package com.training.databasemanager;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.training.databacemanager.JDBC;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

import junit.framework.Assert;

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	private static ConnectionRouter connection;
	JDBC JDBCConnection = new JDBC();
	Connection result = null;
	String res = null;

	@Before
	public void setup() {
		
	}

	@Test
	public void testConnectionToDataBase() throws CRUDException {
		result = JDBCConnection.connectToDatabase();
		assertEquals("Sucess", result);

	}

	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}

}
