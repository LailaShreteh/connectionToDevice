package com.training.deviceoperation.deviceconnection;

import com.jcraft.jsch.JSch;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public class SSHConnection extends CLIConnection {
	public JSch jsch;

	/**
	 * @param host-host address to connect
	 * @param port-port number
	 * @throws Exception
	 */
	@Override
	public String connectToDevice(String host, int port) {
	
		
		 jsch = new JSch();
		return"";
	}
}
