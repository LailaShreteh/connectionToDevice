package com.training.deviceoperation.deviceconnection;

import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 */
public class TelnetConnection extends CLIConnection {
	private TelnetClient telnet;

	public TelnetConnection(String host, int port) {
		super(host,port);
		connectToDevice();
		setIn(telnet.getInputStream());
		setOut( new PrintStream(telnet.getOutputStream()));
		readUntil("Username: ");
		write("lab");
		readUntil("Password: ");
		write("lab");
	}
	/**
	 * @param host-host address to connect
	 * @param port-port number
	 */
	private String connectToDevice() {
		if (getHost() == null || getHost().length() == 0)
		{
			throw new IllegalArgumentException();
		}
	
		 telnet = new TelnetClient();
		try {
			telnet.connect(getHost(), getPort());
			telnet.setSoTimeout(150000);
			//System.out.println(":) 563 :)");
			
			// here you must close the connection !! and send exit comnmand to router !!
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X sorry fails to connect x_x" ;
		}
	}

	
}


