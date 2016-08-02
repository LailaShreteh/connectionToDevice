package com.training.databasemanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.training.databacemanager.JDBC;
import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */
public class DataBaseConnectionTest {
	ConnectionFactory connectionFactory = new ConnectionFactory();
	private ConnectionRouter connection;
	JDBC JDBCConnection=new JDBC();
	boolean result = false;
	String res=null;

	/*@Before
	public void setup() {

		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);

	}*/
	@Test
	public void testConnectionToDataBase() {
		result = JDBCConnection.connectToDatabase();
		res = connection.connectToDevice();
		ArrayList<EthernetProtocolEndpoint> epeList = (ArrayList<EthernetProtocolEndpoint>) connection
				.getEthernetPE();
		//send data to dataBase
		//reem
		
		for (int j = 0; j < epeList.size(); j++)
		{
			
			JDBCConnection.insert(epeList.get(j), "interfaces");
			//con.select();
			System.out.println(epeList.get(j));
		}
		//connection.getInterface_Policy();

	}
	/*@AfterClass
	public  void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}*/
}
