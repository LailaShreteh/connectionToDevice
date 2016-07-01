package com.training.deviceoperation.deviceconnection.test;

import org.apache.mina.core.future.CloseFuture;
import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;
import org.apache.sshd.client.future.AuthFuture;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.Connection;
import com.training.deviceoperation.deviceconnection.SSHConnection;
import com.training.deviceoperation.deviceconnection.TelnetConnection;

import junit.framework.TestCase;

/**
 * 
 * @author Reem 
 *
 */
public class TelnetConnectionTest extends TestCase {
	private Exception exception = null;
	/**
	* @throws java.lang.Exception
	*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	System.out.println("this is excuted before start testing");
	}

	/**
	* @throws java.lang.Exception
	*/
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	System.out.println("this is excuted after done testing");
	}

	/**
	* @throws java.lang.Exception
	*/
	@Before
	public void setUp() throws Exception {
	// this logic is run before each test method
	this.exception = null;
	}

	/**
	* @throws java.lang.Exception
	*/
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTelnetConnection() throws Exception {
		// create instance
		Connection telnetConnection= new TelnetConnection();
		String host="";
		String result = telnetConnection.connectClass(host,23);
		assertEquals(result, "sucess");
	}
	   @Test
	    public void testCloseBeforeAuthSucceed() throws Exception {
	        SshClient client = SshClient.setUpDefaultClient();
	        client.start();
	        ClientSession session = client.connect("localhost", 22).await().getSession();
	        AuthFuture authFuture = session.authPassword("reembzu1121412", "reembzu1121412");
	        org.apache.sshd.common.future.CloseFuture closeFuture = session.close(false);
	        authFuture.await();
	        closeFuture.await();
	        assertNotNull(authFuture.getException());
	        assertTrue(closeFuture.isClosed());
	    }
}
