package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
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

	private String host = "192.168.50.200";
	private int port;
	private java.io.InputStream in;
	private PrintStream out;
	private String prompt = "#";
	private TelnetConnection connection;
	private String cmdBack;
	private SSHConnection ssh_connection;

	abstract public String connectToDevice(String host, int port);

	public List<String> getInterfaces(Object o) throws IOException {
		// TODO push command show interfaces to device
		if (o instanceof TelnetConnection) {
			connection = (TelnetConnection) o;
			in = connection.telnet.getInputStream();
			out = new PrintStream(connection.telnet.getOutputStream());

		} else if (o instanceof SSHConnection) { // session & channel implementation
												// to ssh
			ssh_connection = (SSHConnection) o;
			Session session=null;
			try {
			
				    session = ssh_connection.jsch.getSession("lab",host,22);
					
				    Properties config = new Properties();
				   
				    config.put("StrictHostKeyChecking", "yes");
				   
				    session.setConfig(config);

				    session.connect(1000);
				//com.jcraft.jsch.Channel channel = session.openChannel("shell");
				//channel.setInputStream(System.in);
				//channel.setOutputStream(System.out);
//				readUntil(" ");


			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Scanner scan = null;
		try {
			scan = new Scanner(System.in);
			readUntil("Username: ");
			String cmd = scan.nextLine();
			write(cmd);

			readUntil("Password: ");
			 cmd = scan.nextLine();
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
			cmdBack = readUntil("#");
			write("ex");

		} finally {
			if (scan != null)
				scan.close();
		}

		try {
			if (o instanceof TelnetConnection) {
				connection.telnet.disconnect();
			} else if (o instanceof SshClient) {
				connection.telnet.disconnect();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO get output and convert it to list
		String[] lines = cmdBack.split(System.getProperty("line.separator"));
		// for(int i=0;i<lines.length;i++)
		// {
		// System.out.println(lines[i]+":P \n");
		// }

		// Pattern pat = Pattern.compile("(.*?) ");

		// String s = null;
		List<String> interfaces = new ArrayList<String>();
		for (int i = 2; i < lines.length - 1; i++) {
			String[] splited = lines[i].split("\\s+");
			// System.out.println(splited[0]+":P \n");
			interfaces.add(splited[0]);
		}
		System.out.println("\n"+interfaces);
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
			out.println(value);
			out.flush();// to be sure that our command is send to the server !!
			// System.out.println(value);
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
}
