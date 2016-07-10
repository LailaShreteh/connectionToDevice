package com.training.deviceoperation.deviceconnection;


import org.apache.sshd.SshClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public class SSHConnection extends CLIConnection {
	
	/**
	 * @param host-host address to connect
	 * @param port-port number
	 * @throws Exception
	 */
	@Override
	
	public String connectToDevice(String host, int port) {
	
		
		SshClient client = SshClient.setUpDefaultClient();
		client.start();
		
				
			
	
		return"";
	}
}
