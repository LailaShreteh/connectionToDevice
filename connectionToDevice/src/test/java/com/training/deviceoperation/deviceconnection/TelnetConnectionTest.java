package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.JDBC.connectionToMySQL;
import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

public class TelnetConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private ConnectionRouter connection;
	String result = null;

	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}

	@Test
	public void testConnectClass() {
		try {
			result = connection.connectToDevice();
			connection.getInterfaces();
			assertNotNull(result);
			assertEquals("Sucess", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			assertEquals("Fail to connect x_x", result);
		}
	}

	@Test
	public void testCLIParser() throws IOException {
		result = connection.connectToDevice();
		ArrayList<EthernetProtocolEndpoint> epeList = (ArrayList<EthernetProtocolEndpoint>) connection
				.createEthernetPE();
		//send data to dataBase
		connectionToMySQL con = new connectionToMySQL();
		for (int j = 0; j < epeList.size(); j++)
		{
			
			try {
				con.insert(epeList.get(j));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(epeList.get(j));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHost() {
		connection.setHost(null);
		String re = connection.connectToDevice();
		String expResult = "Fail";
		assertEquals(expResult, re);
	}

	/*
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testConnectClass_failureCase_hostIsBlank() {
	 * connection.connectToDevice("", 53); }
	 */

	@After
	public void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}

	}

}
