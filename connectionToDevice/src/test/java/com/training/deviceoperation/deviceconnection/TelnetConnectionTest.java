package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.JDBC.connectionToMySQL;
import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

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
		//result = connection.connectToDevice();
		 epeList = (ArrayList<EthernetProtocolEndpoint>) connection
				.createEthernetPE();
		//send data to dataBase
		
		for (int j = 0; j < epeList.size(); j++)
		{
			
			System.out.println(epeList.get(j));
		}
		
	}
	@Test
	public void testInsert() {
		for (int j = 0; j < epeList.size(); j++)
		{
			
			try {
				con.insert(epeList.get(j));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	@Test
	public void testUpdate() {
		con.update("GigabitEthernet0/0/3", "reemEthernet123");
	}
	@Test
	public void testDelete() {
		con.delete("GigabitEthernet0",TableName);
	}


//	@Test(expected = IllegalArgumentException.class)
//	public void testHost() {
//		connection.setHost(null);
//		String re = connection.connectToDevice();
//		//String expResult = "Fail";
//		//assertEquals(expResult, re);
//	}
//
//	/*
//	 * @Test(expected = IllegalArgumentException.class) public void
//	 * testConnectClass_failureCase_hostIsBlank() {
//	 * connection.connectToDevice("", 53); }
//	 */

	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}

	}

}
