package com.training.deviceoperation.deviceconnection;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;
import com.training.deviceoperation.parser.CLIParser;
import com.training.deviceoperation.parser.Parser;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author user
 */

/**
 * see @com.training.deviceoperation.deviceconnection.Connection
 */
public abstract class CLIConnection implements ConnectionRouter {
	private InputStream in;
	private PrintStream out;
	private String cmdBack;
	private String host;
	private int port;
	private List<Interface> interfaces;

	/**
	 * Constructor to initialize default construct for children.
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

	/**
	 * readUntil method to read the output from Telnet or SSH commands.
	 *
	 * @param sample
	 *            - an output from Telnet or SSH commands.
	 * @return - the output which was read.
	 **/
	protected String readUntil(String sample) {
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

	/**
	 * write method to print the response from the device.
	 *
	 * @param value
	 *            - commands which send by the user .
	 **/
	protected void write(String value) {
		try {
			out.print(value + "\n");
			out.flush(); // to flush any data in the buffer which is not written
							// to the underlying stream.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getInterfaces method to get all the interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 **/
	public List<Interface> getInterfaces() {
		write("sh ip int br");
		cmdBack = readUntil("#");
		String regex = "[^\\s]+";
		Pattern pattern = Pattern.compile(regex);
		interfaces = new ArrayList<Interface>();
		Interface interfaceObj;
		String[] lines = cmdBack.split(System.getProperty("line.separator"));

		for (int i = 2; i < lines.length - 1; i++) {
			Matcher matcher = pattern.matcher(lines[i]);

			if (matcher.find()) {
				String interfaceName = matcher.group(0);

				interfaceName = interfaceName + " "; // to avoid conflict in
														// interfaces names
				interfaceObj = new Interface();
				interfaceObj.setInterfaceName(interfaceName);
				interfaces.add(interfaceObj);
			}
		}

		/* return list<interface> */
		return interfaces;
	}

	/**
	 * getEthernetPE method to get all interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 **/
	public List<EthernetProtocolEndpoint> parsEthernetPE() {
		List<EthernetProtocolEndpoint> epList = new ArrayList<EthernetProtocolEndpoint>();
		Parser pars = new CLIParser();
		this.getInterfaces();
		write("sh int");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh int", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", " ");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		for (int i = 0; i < interfaces.size(); i++) {
			cmdBack = cmdBack.replace(interfaces.get(i).getInterfaceName(),
					"%% " + interfaces.get(i).getInterfaceName());
		}
		// System.out.println(cmdBack);
		epList = pars.parsEthernetPE(cmdBack);

		return epList;

	}

	/**
	 * getPolicyMap method to get all policies from a device where each policy
	 * map defines a series of actions (functions) that you want applied to a
	 * set of classified in bound traffic.
	 *
	 * @return - a list of all policy maps.
	 **/
	public List<PolicyMap> getPolicyMap() {
		List<PolicyMap> policyMapList = new ArrayList<PolicyMap>();
		Parser pars = new CLIParser();
		write("sh policy-map");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh policy-map", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		List<PolicyMap> policyMap = pars.parsPolicyMap(cmdBack);
		policyMapList.addAll(policyMap);

		return policyMapList;

	}

	@Override
	public List<Transaction> getTransaction() {
		List<Transaction> transactionsList = new ArrayList<Transaction>();
		Parser pars = new CLIParser();
		write("sh policy-map");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh policy-map", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		transactionsList = pars.parsTransaction(cmdBack);

		return transactionsList;
	}

	/**
	 * getClassMap method to get all Class Maps from a device, where each class
	 * map define a traffic classification.
	 *
	 * @return - a list of all Class Maps.
	 **/
	public List<ClassMap> getClassMap() {
		write("sh class-map");
		cmdBack = readUntil("ASR1002_Omar#");
		cmdBack = cmdBack.replace("sh class-map", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("Class Map", " %% Class Map");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		//System.out.println(cmdBack);
		List<ClassMap> classMapList = new ArrayList<ClassMap>();
		Parser pars = new CLIParser();

		classMapList = pars.parsClassMap(cmdBack);

		return classMapList;
	}

	/**
	 * getACL method to get all the access control lists from a device to
	 * control which packets move through a network and to where.
	 *
	 * @return - a list of all Access Control Lists.
	 **/
	public List<ACL> getACL() {
		write("show access-list");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("show access-list", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("Standard", " %% Standard");
		cmdBack = cmdBack.replace("Extended", " %% Extended");
		cmdBack = cmdBack.replace("     ", "$$");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		List<ACL> ACLList = new ArrayList<ACL>();
		Parser pars = new CLIParser();
		ACLList = pars.parsACL(cmdBack);
		return ACLList;
	}

	@Override
	public List<Interface_ACL> getInterface_ACL() {
		write("sh run | in ^inter|access-gr");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh run | in ^inter|access-gr", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		// System.out.println(cmdBack);
		List<Interface_ACL> interface_ACL_List = new ArrayList<Interface_ACL>();
		Parser pars = new CLIParser();
		interface_ACL_List = pars.parsInterface_ACL(cmdBack);

		return interface_ACL_List;
	}

	@Override
	public List<Interface_Policy> getInterface_Policy() {
		write("sh run | in ^inter|policy");
		cmdBack = readUntil("#");
		cmdBack = cmdBack.replace("sh run | in ^inter|policy", "");
		cmdBack = cmdBack.replace("ASR1002_Omar#", "");
		cmdBack = cmdBack.replace("\n", "");
		cmdBack = cmdBack.replace("\r", " ");
		cmdBack = cmdBack.trim();
		// System.out.println(cmdBack);
		List<Interface_Policy> interface_Policy_List = new ArrayList<Interface_Policy>();
		Parser pars = new CLIParser();
		interface_Policy_List = pars.parsInterface_Policy(cmdBack);

		return interface_Policy_List;
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
