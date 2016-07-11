package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.sshd.SshClient;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 
 * @author user
 *
 */

public abstract class CLIConnection implements Connection {

	private String host = "";
	private int port;
	private java.io.InputStream in;
	private PrintStream out;
	private String prompt="#";
	private TelnetConnection connection;
	private StringBuffer cmdBack;
	abstract public String connectToDevice(String host, int port);

	public List<String> getInterfaces(Object o) throws IOException {
		// TODO push command show interfaces to device
		List<String> interfaces = new ArrayList<String>();
		if (o instanceof TelnetConnection) {
			connection = (TelnetConnection) o;
			in = connection.telnet.getInputStream();
			out = new PrintStream(connection.telnet.getOutputStream());

		} else if (o instanceof SshClient) { // session & channel implementation
												// to ssh

			JSch jsch = new JSch();

			try {
				Session session = jsch.getSession("reem", host, port);
				session.connect();

				com.jcraft.jsch.Channel channel = session.openChannel("shell");
				channel.setInputStream(System.in);
				channel.setOutputStream(System.out);

			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Scanner scan = new Scanner(System.in);
		readUntil("Password: ");
		String cmd = scan.nextLine();
		write(cmd);

		readUntil("ASR1002_Omar>");
		cmd = scan.nextLine();
		write(cmd);
		readUntil("Password: ");// you must test if the password is true of
								// false !!!
		cmd = scan.nextLine();
		write(cmd);
		readUntil("ASR1002_Omar#");
		write("sh ip int brief");
		readUntil("# ");
//		connection.sendCommand("sh ip int brief");
//		System.out.println("reem ... ");
//		connection.sendCommand("conf t");

		// here the list of interfaces !!
		// TODO get output and convert it to list
		

		/*
		 * String s = null; List<String> interfaces = new ArrayList<String>();
		 * while ((s = stdInput.readLine()) != null) {
		 * interfaces.add(s.substring(0, s.indexOf("	"))); }
		 * System.out.println(interfaces);
		 */
		// TODO return list<interface>
		// we don't know how the interfaces class it's look like !! :\
		// so we return a list of interfaces as string
		return interfaces;
	}

	private String readUntil(String sample) {
		// TODO Auto-generated method stub
		try {
			char lastChar = sample.charAt(sample.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(sample)) {
						cmdBack = sb;
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();// to be sure that our command is send to the server !!
			//System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   public String sendCommand(String command) {
	    	try {
	    		write(command);
	    		return readUntil(prompt + " ");
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
	   public void disconnect() {
	    	try {
	    		connection.telnet.disconnect();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }

}
