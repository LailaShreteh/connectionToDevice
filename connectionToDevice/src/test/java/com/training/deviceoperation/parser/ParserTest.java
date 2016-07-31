package com.training.deviceoperation.parser;

import java.util.ArrayList;

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

	@Test
	public void testCLIParser() {
		result = connection.connectToDevice();
		epeList = (ArrayList<EthernetProtocolEndpoint>) connection.getEthernetPE();
		// send data to dataBase

		for (int j = 0; j < epeList.size(); j++) {

			System.out.println(epeList.get(j));
		}
		connection.disconnectConnection();

	}

	@Test
	public void testGetACL() {
		result = connection.connectToDevice();
		connection.getACL();
		connection.disconnectConnection();

	}

}
