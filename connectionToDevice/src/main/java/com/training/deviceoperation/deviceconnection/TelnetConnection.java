package com.training.deviceoperation.deviceconnection;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.PrintStream;
import java.io.InputStream;

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
		TelnetClient telnet = new TelnetClient();

		try {
			telnet.connect(host, 23);
			InputStream in = telnet.getInputStream();
			PrintStream out = new PrintStream(telnet.getOutputStream());
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
