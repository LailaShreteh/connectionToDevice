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
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */

public class SSHConnection extends CLIConnection {
	private JSch jsch;
	private Session session = null;
	private com.jcraft.jsch.Channel channel;
	private String password = "lab";
	public SSHConnection()
	{
		
	}
	public void createInOutStream() {
		
		try {
			session = jsch.getSession("lab", super.getHost(), super.getPort());
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "NO");
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("shell");
			channel.connect();
			setIn(new DataInputStream(channel.getInputStream()));
			setOut(new PrintStream(channel.getOutputStream(), true));
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public String connectToDevice() {
		jsch = new JSch();
		createInOutStream();
		return"Sucess";
	}	
	public void disconnectConnection(){
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
