package com.training.deviceoperation.deviceconnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.parser.EthernetProtocolEndpoint;
import com.training.deviceoperation.parser.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */

public abstract class CLIConnection implements Connection {

	private InputStream in;
	private PrintStream out;
	private String cmdBack;
	private String host;
	private int port;
	private int intNum = 0;
	private List<String> interfaces;

	/**
	 * Constructor to initialize default construct for children
	 */
	public CLIConnection() {

	}

	public CLIConnection(String host, int port) {
		this.port = port;
		this.host = host;
	}

	public String getHost() {
		return this.host;
	}

	public List<String> getInterfaces() throws IOException {

		write("sh ip int br");
		cmdBack = readUntil("#");
		String pattern = "[^\\s]+";
		Pattern r = Pattern.compile(pattern);
		// TODO get output and convert it to list
		interfaces = new ArrayList<String>();
		String[] lines = cmdBack.split(System.getProperty("line.separator"));
		for (int i = 2; i < lines.length - 1; i++) {
			Matcher m = r.matcher(lines[i]);
			if (m.find()) {
				intNum++;
				interfaces.add(m.group(0));
			}
		}
		// System.out.println("\n" + interfaces);
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
				// System.out.print(ch);
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
			out.print(value + "\n");
			out.flush();// to be sure that our command is send to the server !!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EthernetProtocolEndpoint> createEthernetPE() {
		try {
			this.getInterfaces();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		write("sh int");
		cmdBack = readUntil("#");

		Parser pars = new CLIParser();
		cmdBack = cmdBack.replaceAll("\n", "%%");
		// System.out.println(cmdBack);
		List<String> cmd = new ArrayList<String>();
		Matcher m;
		for (int i = 0; i < intNum; i++) {
			// hoon wasalna !!
			String pattern = "[a-z]"+interfaces.get(i) +"(.*?)"+ interfaces.get(i + 1);
			System.out.println(pattern);
			Pattern r = Pattern.compile(pattern);
			m = r.matcher(cmdBack);
			System.out.println(m.find());
			if (m.find()) {
				System.out.println("group:" + m.group(1));
				cmd.add(m.group(0));
			}
		}

		System.out.println(cmd);
		// System.out.println(cmdBack);

		List<EthernetProtocolEndpoint> ep = pars.parsEthernetPE(cmdBack);

		return ep;

	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param host
	 *            the host to set
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
	 * @param in
	 *            the in to set
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
	 * @param out
	 *            the out to set
	 */
	public void setOut(PrintStream out) {
		this.out = out;
	}

}
