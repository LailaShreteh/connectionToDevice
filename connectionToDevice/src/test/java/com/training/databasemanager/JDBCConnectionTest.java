package com.training.databasemanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	private static JDBC JDBCConnection;
	private Connection dataBaseConnection = null;
	private Exception exception;
	/* variables parsing data */
	private List<EthernetProtocolEndpoint> ePEList;
	private List<ACL> accessList;
	private List<ClassMap> classMapList;
	private List<PolicyMap> policyMapList;
	private List<Transaction> transactionList;
	private List<Interface_ACL> interface_ACLList;
	private List<Interface_Policy> interface_PolicyList;

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
			for (int j = 0; j < ePEList.size(); j++) {

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
