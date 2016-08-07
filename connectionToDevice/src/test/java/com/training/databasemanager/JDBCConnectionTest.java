package com.training.databasemanager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.training.databacemanager.JDBC;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

import com.training.deviceoperation.parser.DuplexMode;
import com.training.deviceoperation.parser.Status;

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	private static JDBC JDBCConnection;
	private Connection dataBaseConnection = null;
	private Exception exception;
	private EthernetProtocolEndpoint ePEObject;

	@Before
	public void setup() {
		JDBCConnection = new JDBC();
		exception = null;

		/*
		 * Static data for EthernetProtocolEndpoint object to insert it into its
		 * table in the database.
		 */
		ePEObject = new EthernetProtocolEndpoint("GigabitEthernet0/0/0", Status.up, Status.up,1500,
				"1000",DuplexMode.Full, "503d.e596.7400");
	}

	@Test
	public void testConnectionToDataBase() throws CRUDException {
		dataBaseConnection = JDBCConnection.connectToDatabase();
		assertNotNull(dataBaseConnection);
	}

	@Test
	public void testInsertToDatabase() {
		JDBCConnection = new JDBC();
		dataBaseConnection = JDBCConnection.connectToDatabase();
		exception = null;

		try {
			// send the data to the dataBase
			JDBCConnection.insert(ePEObject);
			
		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		}
		assertNull("Insert is not successfull", exception);
	}

	@AfterClass
	public static void teardown() throws IOException {
		if (JDBCConnection != null) {
			JDBCConnection.disconnectToDataBase();
			JDBCConnection = null;
		}
	}
}
