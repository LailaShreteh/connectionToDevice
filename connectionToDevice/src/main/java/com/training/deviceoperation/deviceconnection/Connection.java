package com.training.deviceoperation.deviceconnection;

import java.io.IOException;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public interface Connection {

	public String connectToDevice(String host, int port) throws IOException;
	
//	public String getVersion();

}