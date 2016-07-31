package com.training.deviceoperation.deviceconnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.parser.CLIParser;
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
	private List<Interface> interfaces;

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
	public List<Interface> getInterfaces() {

		write("sh ip int br");
		cmdBack = readUntil("#");
		String pattern = "[^\\s]+";
		Pattern r = Pattern.compile(pattern);
		// TODO get output and convert it to list
		interfaces = new ArrayList<Interface>();
		Interface inter;
		String[] lines = cmdBack.split(System.getProperty("line.separator"));
		for (int i = 2; i < lines.length - 1; i++) {
			Matcher m = r.matcher(lines[i]);
			if (m.find()) {
				String s = m.group(0);
				s = s + " "; // to avoid conflict in interfaces names
				inter = new Interface();
				inter.setInterfaceName(s);
				interfaces.add(inter);
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

	/***
	 * getPolicyMap method to get all policies from a device where each policy
	 * map defines a series of actions (functions) that you want applied to a
	 * set of classified in bound traffic.
	 *
	 * @return - a list of all policy maps.
	 ***/
	public List<PolicyMap> getPolicyMap() {

		List<PolicyMap> policy_mapList = new ArrayList<PolicyMap>();
		Parser pars = new CLIParser();
		write("sh policy-map");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh policy-map", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		/*
		 * cmdBack = "policy-map policy1
		 * 
		 * class class1 bandwidth 2000 queue-limit 40
		 * 
		 * class class-default fair-queue 16 queue-limit 20 policy-map policy9
		 * 
		 * class acl136 bandwidth 2000 queue-limit 40
		 * 
		 * class ethernet101 bandwidth 3000 random-detect
		 * exponential-weighting-constant 10
		 * 
		 * class class-default fair-queue 10 queue-limit 20Related Commands"
		 */ System.out.println(cmdBack);

		return policy_mapList;

	}

	/***
	 * getClassMap method to get all Class Maps from a device, where each class
	 * map define a traffic classification.
	 *
	 * @return - a list of all Class Maps.
	 ***/
	public List<ClassMap> getClassMap() {
		write("sh class-map");
		cmdBack = readUntil("ASR1002_Omar#");
		cmdBack = cmdBack.replace("sh class-map", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("Class Map", " % Class Map");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		//System.out.println(cmdBack); 
		String[] splited = cmdBack.split("%");
		
		//System.out.println(cmdBack); 
		List<ClassMap> class_mapList = new ArrayList<ClassMap>();
		Parser pars = new CLIParser();
		for (int i = 1; i < splited.length; i++) {
			List<ClassMap> classMap = pars.parsClassMap(splited[i]);
			// System.out.println(splited[i]);
			class_mapList.addAll(classMap);
		
		}

 
		return class_mapList;

	}

	/***
	 * getEthernetPE method to get all interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 ***/
	public List<EthernetProtocolEndpoint> getEthernetPE() {
		List<EthernetProtocolEndpoint> epList = new ArrayList<EthernetProtocolEndpoint>();
		String interfaceInform;
		Parser pars = new CLIParser();
		this.getInterfaces();
		write("sh int");
		cmdBack = readUntil("#");

		for (int i = 0; i < interfaces.size(); i++) {
			if (i == interfaces.size() - 1) {

				interfaceInform = cmdBack.substring(cmdBack.indexOf(interfaces.get(i).getInterfaceName()),
						cmdBack.length());
			} else {
				interfaceInform = cmdBack.substring(cmdBack.indexOf(interfaces.get(i).getInterfaceName()),
						cmdBack.indexOf(interfaces.get(i + 1).getInterfaceName()));
			}
			EthernetProtocolEndpoint ep = pars.parsEthernetPE(interfaceInform);
			epList.add(ep);
		}

		return epList;

	}

	/***
	 * getACL method to get all the access control lists from a device to
	 * control which packets move through a network and to where.
	 *
	 * @return - a list of all Access Control Lists.
	 ***/
	public List<ACL> getACL() {

		write("show access-list");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("show access-list", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("Standard", " % Standard");
		cmdBack = cmdBack.replace("Extended", " % Extended");
		cmdBack = cmdBack.replace("     ", "$$");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();

		String[] splited = cmdBack.split("%");
		List<ACL> ACLList = new ArrayList<ACL>();
		Parser pars = new CLIParser();

		for (int i = 1; i < splited.length; i++) {
			List<ACL> acl = pars.parsACL(splited[i]);
			ACLList.addAll(acl);
		}

		return ACLList;
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
