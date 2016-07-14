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
	private String host;
	private int port;
	/**
	 * @param host-host address to connect
	 * @param port-port number
	 * @throws Exception
	 */
	@Override
	public String connectToDevice(String host, int port) {
		jsch = new JSch();
		 this.host = host;
		 this.port=port;
		return"";
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}	
}
