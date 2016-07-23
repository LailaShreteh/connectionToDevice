package com.training.deviceoperation.deviceconnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.JSchException;

/**
 * 
 * @author user
 *
 */

public class SSHConnection extends CLIConnection {

	/**
	 * A string to define the password to use for authentication.
	 */
	private String password = "lab";
	private JSch jsch;
	private Session session = null;
	private com.jcraft.jsch.Channel channel;

	public SSHConnection() {

	}

	/**
	 * createInOutStream method to create an InputStream and OutputStream for
	 * sending and receiving data over the SSH connection.
	 * 
	 */
	public void createInOutStream() {

		try {
			session = jsch.getSession("lab", super.getHost(), super.getPort());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "NO");
			session.setPassword(password); // sets the password to use for
											// authentication.
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("shell");
			channel.connect();
			setIn(new DataInputStream(channel.getInputStream()));
			setOut(new PrintStream(channel.getOutputStream(), true));
			readUntil("ASR1002_Omar>");
			write("en");
			readUntil("Password: ");
			write("lab");
			readUntil("ASR1002_Omar#");
			write("terminal length 0"); // to avoid More enter to get all output
										// in one time.
			readUntil("ASR1002_Omar#");

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * connectToDevice method to open SSH connection to a server.
	 * 
	 */

	@Override
	public String connectToDevice() {
		jsch = new JSch();
		createInOutStream();
		return "Sucess";
	}

	/**
	 * disconnectConnection method to close the input and the output stream, to
	 * close the channel and the opened session connection.
	 */
	public void disconnectConnection() {
		try {
			getIn().close();
			getOut().close();
			channel.disconnect();
			session.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
