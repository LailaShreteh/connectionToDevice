package com.training.deviceoperation.deviceconnection.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.training.deviceoperation.deviceconnection.Connection;
import com.training.deviceoperation.deviceconnection.TelnetConnection;

import junit.framework.TestCase;

/**
 * 
 * @author hamada1
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
	public void testConnect() throws Exception {
	   // fail();
		// create instance
		Connection telnetConnection= new TelnetConnection();
		String host="";
		String result = telnetConnection.connectClass(host,23);
		
	}
}
