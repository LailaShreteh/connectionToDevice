package com.training.databasemanager;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.training.databacemanager.ORM;
import com.training.databacemanager.exception.CRUDException;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.parser.DuplexMode;
import com.training.deviceoperation.parser.Status;

/**
 * 
 * @author user
 *
 */
public class ORMConnectionTest {
	private Exception exception;
	private EthernetProtocolEndpoint ePEObject;
	private PolicyMap policymap;
	private ORM ORMConnection;

	@Before
	public void setup() {
		ORMConnection = new ORM();
		//ePEObject = new EthernetProtocolEndpoint("GigabitEthernet0/0/0", Status.up, Status.up,1500,
			//	"1000",DuplexMode.Full, "503d.e596.7400");
		policymap= new PolicyMap("policy1","calss1");
	}

	@Test
	public void testInsertToDatabase() {

		exception = null;

		try {
			// send the data to the dataBase
			ORMConnection.insert(policymap);

		} catch (Exception e) {
			exception = e;
			e.printStackTrace();
		}
		assertNull("Insert is not successfull", exception);
	}

}
