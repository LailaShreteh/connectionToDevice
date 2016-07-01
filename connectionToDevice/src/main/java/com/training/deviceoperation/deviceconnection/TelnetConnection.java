package com.training.deviceoperation.deviceconnection;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public String connectClass(String host, int port) throws UnknownHostException, IOException {
		TelnetClient telnet = new TelnetClient();
		try {
			
			telnet.connect(host, 23);
			System.out.println(":P");
			InputStream in = telnet.getInputStream();
			PrintStream out = new PrintStream(telnet.getOutputStream());
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X fail to connect x_x" ;
		}
	}
}
