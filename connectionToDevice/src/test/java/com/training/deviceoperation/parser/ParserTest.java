package com.training.deviceoperation.parser;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.training.databacemanager.JDBC;
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
public class ParserTest {

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	
	private static ConnectionRouter connection;
	private JDBC JDBCConnection = new JDBC();
	private Connection result = null;
	private String res = null;
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

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);
		exception = null;

	}

	@Test
	public void testParsingAndSendDatatoDB() {
		connection.connectToDevice();
		ePEList = connection.getEthernetPE();
		accessList = connection.getACL();
		classMapList = connection.getClassMap();
		policyMapList = connection.getPolicyMap();
		transactionList = connection.getTransaction();
		interface_PolicyList = connection.getInterface_Policy();
		interface_ACLList = connection.getInterface_ACL();
		res = connection.connectToDevice();
		result = JDBCConnection.connectToDatabase();
		// send data to dataBase
		try {
			for (int j = 0; j < ePEList.size(); j++) {
	
				JDBCConnection.insert(ePEList.get(j));		
				}
			for (int j = 0; j < accessList.size(); j++) {
				//System.out.println(accessList.get(j));
	
				JDBCConnection.insert(accessList.get(j));		
				}
			for (int j = 0; j < classMapList.size(); j++) {
				//System.out.println(classMapList.get(j));
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
		} catch(Exception e) {
			exception = e;
		}
		assertNull("Insert is not successfull",exception);

	}

	
	 
	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}

}
