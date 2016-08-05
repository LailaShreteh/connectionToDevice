package com.training.databasemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import com.training.deviceoperation.parser.CLIParser;

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	private static JDBC JDBCConnection;
	private Connection dataBaseConnection = null;
	private Exception exception;

	@Before
	public void setup() {
		JDBCConnection = new JDBC();
		exception = null;

	}

	@Test
	public void testConnectionToDataBase() throws CRUDException {
		dataBaseConnection = JDBCConnection.connectToDatabase();
		assertEquals("Sucess", dataBaseConnection);

	}
	@Test
	public void testSendingToDatatoDB() {
		JDBCConnection = new JDBC();

		dataBaseConnection = JDBCConnection.connectToDatabase();
		exception = null;
		// send data to dataBase
		
		try {
			for (int j = 0; j < ((CLIParser) pars).getePEList().size(); j++) {

				JDBCConnection.insert(ePEList.get(j));
			}
			for (int j = 0; j < accessList.size(); j++) {

				JDBCConnection.insert(accessList.get(j));
			}
			for (int j = 0; j < classMapList.size(); j++) {
				
				JDBCConnection.insert(classMapList.get(j));
			}
			for (int j = 0; j < policyMapList.size(); j++) {

				JDBCConnection.insert(policyMapList.get(j));
			}
			for (int j = 0; j < transactionList.size(); j++) {

				JDBCConnection.insert(transactionList.get(j));
			}
			for (int j = 0; j < interface_PolicyList.size(); j++) {

				JDBCConnection.insert(interface_PolicyList.get(j));
			}
			for (int j = 0; j < interface_ACLList.size(); j++) {

				JDBCConnection.insert(interface_ACLList.get(j));
			}
		} catch (Exception e) {
			exception = e;
			System.out.println(e);
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
