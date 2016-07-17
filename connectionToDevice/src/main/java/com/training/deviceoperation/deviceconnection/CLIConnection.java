package com.training.deviceoperation.deviceconnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.apache.sshd.SshClient;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 
 * @author user
 *
 */

public abstract class CLIConnection implements Connection {

	private InputStream in;
	private PrintStream out;
	private TelnetConnection connection;
	private String cmdBack;
	private SSHConnection ssh_connection;
	private String password = "lab";
	private DataOutputStream dataOut;
	private Session session = null;
	private com.jcraft.jsch.Channel channel;

	abstract public String connectToDevice(String host, int port);

	public List<String> getInterfaces(Object o) throws IOException {

		// TODO push command show interfaces to device
		if (o instanceof TelnetConnection) {

			connection = (TelnetConnection) o;
			in = connection.telnet.getInputStream();
			out = new PrintStream(connection.telnet.getOutputStream());
			// addition to cmd if the connection is telnet
			readUntil("Username: ");
			write("lab");
			readUntil("Password: ");
			write("lab");

		} else if (o instanceof SSHConnection) { // session & channel
													// implementation to ssh
			ssh_connection = (SSHConnection) o;

			try {
				session = ssh_connection.jsch.getSession("lab", ssh_connection.getHost(), ssh_connection.getPort());
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "NO");
				session.setPassword(password);
				session.setConfig(config);
				session.connect();
				channel = session.openChannel("shell");
				channel.connect();
				in = new DataInputStream(channel.getInputStream());
				out = new PrintStream(channel.getOutputStream(), true);

			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

		try {
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
		}
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

	private String readUntil(String sample) {
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

	public void write(String value) {
		try {
			out.print(value+"\n");
			out.flush();// to be sure that our command is send to the server !!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
