package com.training.deviceoperation.deviceconnection;

import static org.junit.Assert.*;

import org.junit.Test;

public class SSHConnectionTest {

	@Test
	public void test() {
		SSHConnection connection=  new SSHConnection();
		String result = null;
		try {
			result = connection.connectClass("8.8.8.8", 53);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(":P :P LLLLLL");
			e.getMessage();
		}
		assertEquals("Sucess" , result);
		}

}
