package com.training.deviceoperation.deviceconnection;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 */
public class TelnetConnection implements Connection {

	/**
	 * @param host-host address to connect
	 * @param port-port number
	 */
	public String connectClass(String host, int port) {
		if (host == null || host.length() == 0)
		{
			throw new IllegalArgumentException();
		}
		TelnetClient telnet = new TelnetClient();
		try {
			
			telnet.connect(host, port);
			//InputStream in = telnet.getInputStream();
			//PrintStream out = new PrintStream(telnet.getOutputStream());
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X sorry fails to connect x_x" ;
		}
	}
}
