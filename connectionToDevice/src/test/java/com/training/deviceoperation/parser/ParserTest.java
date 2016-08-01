package com.training.deviceoperation.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */
public class ParserTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private static ConnectionRouter connection;
	String result = null;
	ArrayList<EthernetProtocolEndpoint> epeList;

	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}

//	@Test
//	public void testCLIParser() {
//		result = connection.connectToDevice();
//		epeList = (ArrayList<EthernetProtocolEndpoint>) connection.getEthernetPE();
//		// send data to dataBase
//
//		for (int j = 0; j < epeList.size(); j++) {
//
//			System.out.println(epeList.get(j));
//		}
//
//	}
//
//	@Test
//	public void testGetACL() {
//		result = connection.connectToDevice();
//		connection.getACL();
//
//	}
//	@Test
//	public void testParsPolicyMap() {
//		result = connection.connectToDevice();
//		connection.getPolicyMap();
//
//	}
//	@Test
//	public void testParsTransaction() {
//		result = connection.connectToDevice();
//		connection.getTransaction();
//
//	}
/*	@Test
	public void testParsInterface_ACL() {
		result = connection.connectToDevice();
		connection.getInterface_ACL();

	}*/
	@Test
	public void testParsInterface_Policy() {
		result = connection.connectToDevice();
		connection.getInterface_Policy();

	}
	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}

}
