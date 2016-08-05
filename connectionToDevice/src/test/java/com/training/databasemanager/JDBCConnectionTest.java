package com.training.databasemanager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import com.training.databacemanager.JDBC;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.ConnectionFactory;
import com.training.deviceoperation.deviceconnection.ConnectionRouter;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */
public class JDBCConnectionTest {
	ConnectionFactory connectionFactory = new ConnectionFactory();
	private static ConnectionRouter connection;
	JDBC JDBCConnection = new JDBC();
	Connection result = null;
	String res = null;

	@Before
	public void setup() {
		connection = ConnectionFactory.createConnection("TELNET");
		connection.setHost("192.168.50.200");
		connection.setPort(23);
	}

	@Test
	public void testConnectionToDataBase() throws CRUDException {
		res = connection.connectToDevice();
		result = JDBCConnection.connectToDatabase();

		ArrayList<EthernetProtocolEndpoint> epeList = (ArrayList<EthernetProtocolEndpoint>) connection.getEthernetPE(); 
		// send data to dataBase
		//for (int j = 0; j < epeList.size(); j++) {
			JDBCConnection.insert(epeList.get(0)); // con.select();
			//System.out.println(epeList.get(0));
		//} //
		//connection.getInterface_Policy();
	}

	@AfterClass
	public static void teardown() throws IOException {
		if (connection != null) {
			connection.disconnectConnection();
			connection = null;
		}
	}

}
