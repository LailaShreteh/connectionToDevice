package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public interface Connection {
	public void disconnectConnection();
	public List<String> getInterfaces() throws IOException ;
	//public String getVersion();

}