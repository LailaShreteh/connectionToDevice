package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.apache.commons.net.telnet.TelnetClient;

import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.Transaction;

/**
 * 
 * @author user
 */
public class TelnetConnection extends CLIConnection {
	private TelnetClient telnet;

	/**
	 * connectToDevice method to open Telnet connection to a server.
	 * 
	 * @return - Sucess or Fail to connect .
	 */
	public String connectToDevice() {
		if (getHost() == null || getHost().length() == 0) {
			throw new IllegalArgumentException("Host: " + getHost());
		}

		telnet = new TelnetClient();
		try {
			telnet.connect(getHost(), getPort());
			telnet.setSoTimeout(15000);
			createInOutStream();
			return "Sucess";

		} catch (Exception e) {
			throw new IllegalArgumentException("  X_X sorry fails to connect x_x");
		}

	}

	/**
	 * createInOutStream method to create an InputStream and OutputStream for
	 * sending and receiving data over the Telnet connection.
	 * 
	 */

	private void createInOutStream() {
		setIn(telnet.getInputStream());
		setOut(new PrintStream(telnet.getOutputStream()));
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

	/**
	 * disconnectConnection method to close the input and the output streams and
	 * to close the telnet connection.
	 */
	public void disconnectConnection() {
		try {
			getIn().close();
			getOut().close();
			telnet.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Exception while closing telnet:" + e.getMessage());
		}

	}



}
