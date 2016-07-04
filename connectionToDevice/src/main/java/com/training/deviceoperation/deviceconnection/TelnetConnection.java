package com.training.deviceoperation.deviceconnection;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 */
public class TelnetConnection extends CLIConnection {
	public TelnetClient telnet;
	
	/**
	 * @param host-host address to connect
	 * @param port-port number
	 */
	@Override
	public String connectToDevice(String host, int port) {
		if (host == null || host.length() == 0)
		{
			throw new IllegalArgumentException();
		}
		 TelnetClient telnet = new TelnetClient();
		try {
			telnet.connect(host, port);
			telnet.setSoTimeout(150000);

			//System.out.println(":) 563 :)");
		
			
			// here you must close the connection !! and send exit comnmand to router !!
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X sorry fails to connect x_x" ;
		}
	}
	
}


