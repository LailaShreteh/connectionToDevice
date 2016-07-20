package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 */
public class TelnetConnection extends CLIConnection {
	private TelnetClient telnet;

	public TelnetConnection(){
		
	}

	/**
	 * @param host-host
	 *            address to connect
	 * @param port-port
	 *            number
	 */
	public String connectToDevice() {
		if (getHost() == null || getHost().length() == 0) {
			throw new IllegalArgumentException();
		}

		telnet = new TelnetClient();
		try {
			telnet.connect(getHost(), getPort());
			telnet.setSoTimeout(150000);
			// System.out.println(":) 563 :)");

			// here you must close the connection !! and send exit comnmand to
			// router !!
			createInOutStream();
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X sorry fails to connect x_x";
		}
		
	}

	private void createInOutStream () {
		// TODO Auto-generated method stub
		setIn(telnet.getInputStream());
		setOut(new PrintStream(telnet.getOutputStream()));
		readUntil("Username: ");
		write("lab");
		readUntil("Password: ");
		write("lab");
		readUntil("ASR1002_Omar>");
		write("en");
		readUntil("Password: ");
		write("lab");
		readUntil("ASR1002_Omar#");
		write("terminal length 0");
		readUntil("ASR1002_Omar#");

		
	}

	public void disconnectConnection() {
		try {
			getIn().close();
			getOut().close();
			telnet.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
