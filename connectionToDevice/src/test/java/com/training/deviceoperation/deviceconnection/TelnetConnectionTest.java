package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

public class TelnetConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection connection;
	String result = null;
	
	@Before
	public void setup() throws IOException {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);
		result = connection.connectToDevice();

	}

	@Test
	public void testConnectClass_SucessCase()  {
		try {
			
			connection.getInterfaces();
			connection.createEthernetPE();
			assertNotNull(result);
			assertEquals("Sucess", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			assertEquals("Fail", result);
		}
	}

	@Test
	public void testCLIParser() throws IOException {
		ArrayList<EthernetProtocolEndpoint> epeList = (ArrayList<EthernetProtocolEndpoint>) connection
				.createEthernetPE();
		for (int j = 0; j < epeList.size(); j++)
			System.out.println(epeList.get(j));
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testHost() {
	    connection.setHost("null");
		String re = connection.connectToDevice();
		String expResult = "Fail";
		assertEquals(expResult, re);
		}
		

		/*try {
			connection.setHost("null");
	        fail("Should throw an exception if host set as NULL");
	    } catch (Exception e) {
	        assertThat(e) .isInstanceOf(IllegalArgumentException.class)
	                .hasMessage("Null host are not allowed");
	    }
	}*/
	 
	 /* @Test(expected = IllegalArgumentException.class) public void
	 * testConnectClass_failureCase_hostIsBlank() {
	 * connection.connectToDevice("", 53); }
	 */

	@After
	public void teardown() throws IOException{
		if(connection!=null){
		connection.disconnectConnection();
		connection = null;}

	}

}
