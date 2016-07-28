package com.training.deviceoperation.deviceconnection;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.JDBC.connectionToMySQL;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

public class TelnetConnectionTest {

	private String TableName = "interface";
	ConnectionFactory connectionFactory = new ConnectionFactory();
	private static ConnectionRouter connection;
	String result = null;
	static connectionToMySQL con;
	ArrayList<EthernetProtocolEndpoint> epeList;

	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}

	@BeforeClass
	public static void createConnectionToSQL() {
		con = new connectionToMySQL();

	}

	@Test
	public void testConnectClass() {

		result = connection.connectToDevice();
		assertNotNull(result);
		assertEquals("Sucess", result);
		connection.disconnectConnection();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailureConnectionPort() {
		connection.setPort(98); // not the right port !!
		result = connection.connectToDevice();
		assertEquals("Fail to connect x_x", result);
		connection.disconnectConnection();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailureConnectionHost() {
		connection.setHost("9.9.9.9"); // not our device !!
		result = connection.connectToDevice();
		assertEquals("Fail to connect x_x", result);
		connection.disconnectConnection();
	}

/*	@Test
	public void testCLIParser() {
		result = connection.connectToDevice();
		epeList = (ArrayList<EthernetProtocolEndpoint>) connection.getEthernetPE();
		// send data to dataBase

		for (int j = 0; j < epeList.size(); j++) {

			System.out.println(epeList.get(j));
		}
		for (int j = 0; j < epeList.size(); j++) {

			try {
				con.insert(epeList.get(j));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		connection.disconnectConnection();

	}*/

	@Test
	public void testInsert() {
		con = new connectionToMySQL();

	}

/*	@Test
	public void testUpdate() {
		con.update("GigabitEthernet0/0/3", "reemEthernet123");
	}

	@Test
	public void testDelete() {
		con.delete("GigabitEthernet0", TableName);

	}*/

	@Test
	public void testGetInterfaces() {
		result = connection.connectToDevice();
		connection.getInterfaces();
		connection.disconnectConnection();

	}

/*	@Test
	public void testGetACL() {
		result = connection.connectToDevice();
		connection.getACL();
		connection.disconnectConnection();

	}*/
	@Test
	public void testGetClass_map() {
		result = connection.connectToDevice();
		connection.getClass_map();
		connection.disconnectConnection();

	}

	@Test
	public void testGetPolicy_map() {
		result = connection.connectToDevice();
		connection.getPolicy_map();
		connection.disconnectConnection();

	}
	// @Test(expected = IllegalArgumentException.class)
	// public void testHost() {
	// connection.setHost(null);
	// String re = connection.connectToDevice();
	// //String expResult = "Fail";
	// //assertEquals(expResult, re);
	// }
	//

	@Test(expected = IllegalArgumentException.class)
	public void testConnectClass_failureCase_hostIsBlank() {
		connection.setHost("");
		connection.connectToDevice();
		connection.disconnectConnection();
	}

	/*
	 * @AfterClass public static void teardown() throws IOException { if
	 * (connection != null) { connection.disconnectConnection(); connection =
	 * null; }
	 * 
	 * }
	 */

}
