package com.training.databasemanager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.training.databacemanager.JDBC;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;
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
	/* static data */
	private EthernetProtocolEndpoint ePEList;

	@Before
	public void setup() {
		JDBCConnection = new JDBC();
		exception = null;
		ePEList = new EthernetProtocolEndpoint("Gig0/0/0", 123, Status.up, Status.down, DuplexMode.Full, "1090 kp/s", "123.123.123.123");

	}

	@Test
	public void testConnectionToDataBase() throws CRUDException {
		dataBaseConnection = JDBCConnection.connectToDatabase();
		assertNotNull(dataBaseConnection);

	}

	@Test
	public void testInsert() {
		JDBCConnection = new JDBC();
		dataBaseConnection = JDBCConnection.connectToDatabase();
		exception = null;
		// send data to dataBase
		try {

				JDBCConnection.insert(ePEList);

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
