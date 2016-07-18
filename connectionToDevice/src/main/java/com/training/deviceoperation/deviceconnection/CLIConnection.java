package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * @author user
 *
 */

public abstract class CLIConnection implements Connection {

	private InputStream in;
	private PrintStream out;
	private String cmdBack;
	private String host;
	private int port;

	public CLIConnection(String host, int port) {
		this.port=port;
		this.host = host;
	}
	
	public String getHost() {
		return this.host;
	}
	//abstract public String connectToDevice(String host, int port);

	public List<String> getInterfaces() throws IOException {
		
	
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			readUntil("ASR1002_Omar>");
			write("en");
			readUntil("Password: ");
			write("lab");
			readUntil("ASR1002_Omar#");
			write("sh ip int brief");
			cmdBack = readUntil("#");
			write("ex");

		} finally {
			if (scan != null)
				scan.close();
		}

	/*	try {
			if (o instanceof TelnetConnection) {
				in.close();
				out.close();
				connection.telnet.disconnect();
			} else if (o instanceof SshClient) {
				in.close();
				dataOut.close();
				channel.disconnect();
				session.disconnect();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// TODO get output and convert it to list
		String[] lines = cmdBack.split(System.getProperty("line.separator"));

		List<String> interfaces = new ArrayList<String>();
		for (int i = 2; i < lines.length - 1; i++) {
			String[] splited = lines[i].split("\\s+");
			interfaces.add(splited[0]);
		}
		System.out.println("\n" + interfaces);
		// TODO return list<interface>
		// we don't know how the interfaces class it's look like !! :\
		// so we return a list of interfaces as string
		return interfaces;
	}

	protected String readUntil(String sample) {
		// TODO Auto-generated method stub
		try {
			char lastChar = sample.charAt(sample.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();

			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(sample)) {
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

	protected void write(String value) {
		try {
			out.print(value+"\n");
			out.flush();// to be sure that our command is send to the server !!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the in
	 */
	public InputStream getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(InputStream in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	public PrintStream getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(PrintStream out) {
		this.out = out;
	}

}
