package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */

public class SSHConnectionTest {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	private ConnectionRouter connection;
	String result = null;

	@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("SSH");
		connection.setHost("192.168.50.200");
		connection.setPort(22);
		result = connection.connectToDevice();

	}

	@Test
	public void testConnectClass_SucessCase() {
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

	@After
	public void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}

}
