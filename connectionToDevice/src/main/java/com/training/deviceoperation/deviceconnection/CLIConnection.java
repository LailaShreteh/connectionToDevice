package com.training.deviceoperation.deviceconnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.parser.CLIParser;
import com.training.deviceoperation.parser.EthernetProtocolEndpoint;
import com.training.deviceoperation.parser.Parser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author user
 *
 */
public abstract class CLIConnection implements ConnectionRouter {

	/**
	 * see @com.training.deviceoperation.deviceconnection.Connection
	 */

	private InputStream in;
	private PrintStream out;
	private String cmdBack;
	private String host;
	private int port;
	private List<String> interfaces;

	/**
	 * Constructor to initialize default construct for children
	 */
	public CLIConnection() {

	}

	/**
	 * @param host
	 *            - host address to connect
	 * @param port
	 *            - port number
	 */
	public CLIConnection(String host, int port) {
		this.port = port;
		this.host = host;
	}

	public String getHost() {
		return this.host;
	}

	/***
	 * getInterfaces method to get all the interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 ***/
	@Override
	public List<String> getInterfaces() {

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
				String s = m.group(0);
				s = s + " "; // to avoid conflict in interfaces names :3
				interfaces.add(s);
			}
		}

		// TODO return list<interface>
		return interfaces;
	}

	/***
	 * readUntil method to read the output from Telnet or SSH commands.
	 *
	 * @param sample
	 *            - an output from Telnet or SSH commands.
	 * @return - the output which was read.
	 ***/
	protected String readUntil(String sample) {
		// TODO Auto-generated method stub
		try {
			char lastChar = sample.charAt(sample.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();

			while (true) {
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

	/***
	 * write method to print the response from the device.
	 *
	 * @param value
	 *            - commands which send by the user .
	 ***/
	protected void write(String value) {
		try {
			out.print(value + "\n");
			out.flush(); // to flush any data in the buffer which is not written
							// to the underlying stream.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EthernetProtocolEndpoint> createEthernetPE() {
		List<EthernetProtocolEndpoint> epList = new ArrayList<EthernetProtocolEndpoint>();
		String interfaceInform;
		Parser pars = new CLIParser();
		this.getInterfaces();
		write("sh int");
		cmdBack = readUntil("#");

		for (int i = 0; i < interfaces.size(); i++) {
			if (i == interfaces.size() - 1){
				 
				interfaceInform = cmdBack.substring(cmdBack.indexOf(interfaces.get(i)), cmdBack.length());
			}
			else{
				interfaceInform = cmdBack.substring(cmdBack.indexOf(interfaces.get(i)),
						cmdBack.indexOf(interfaces.get(i + 1)));
			}
				// System.out.println(interfaceInform);
			EthernetProtocolEndpoint ep = pars.parsEthernetPE(interfaceInform);
			epList.add(ep);
		}

		return epList;

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
