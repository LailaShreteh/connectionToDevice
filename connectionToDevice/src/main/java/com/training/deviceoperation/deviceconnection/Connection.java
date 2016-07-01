package com.training.deviceoperation.deviceconnection;

import java.io.IOException;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public interface Connection {

	String ip = "4.4.4.4";
	String id = "";
	String version = "";

	public String connectClass(String host, int port) throws IOException, InterruptedException, Exception;

}