package com.training.deviceoperation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;

/**
 * 
 * @author user
 *
 */

/**
 * see @com.training.deviceoperation.parser.Parser
 */
public class CLIParser implements Parser {

	/**
	 * regex constants to parse EthernetProtocolEndpoint.
	 */
	final static String INTERFACE = "[A-Z][A-Za-z]+[0-9/]*";
	final static String ADMIN_STATUS = "\\w+";
	final static String OPERATIONAL_STATUS = "\\w+";
	final static String ADDRESS = "[^\\s]+";
	final static String MTU = "\\d+";
	final static String DUPLEX = "\\w+";
	final static String DUPLEX_SPEED = "\\d+";

	/**
	 * regex constants to parse ACL.
	 */
	final static String ACESS_LIST_TYPE = "^[?:Standard|Extended].*";
	final static String IP_ACCESS_LIST_NUM = "\\w+";
	final static String ACCESS_LIST_MODE_NUMBER = "\\d+";
	final static String SOURCE_IP = "[0-9.any]*";
	final static String WILDCARD_SOURCE_IP = "[0-9.]*";
	final static String DES_IP = "any|[0-9.]*";
	final static String WILDCARD_DES_IP = "[0-9.]*";
	final static String ACCESS_LIST_MODE = "[?:permit|deny|permit ip|deny ip]*";

	final static String CLASS_MAP_CONFIGERATION_MODE = "match-any|match-all";
	final static String CLASS_NAME = "[a-zA-Z](-|\\w+)*";
	final static String MATCH_TYPE = "Match.*";
	final static String DESCRIPTION = "[^Match].*";
	final static String MATCH_ONE_GROUP = "[^\\s]*";
	final static String MATCH_TYPE_VALUE = "[^\\s]*";

	final static String POLICY_NAME = "\\w+";
	final static String TRAFFIC_CLASS = ".*";

	/**
	 * variables to define the matcher group for EthernetProtocolEndpoint parsed
	 * data .
	 */
	private String ifName;
	private Status ifStatus;
	private Status ifOperStatus;
	private int ifMTU;
	private DuplexMode duplexMode;
	private String ifSpeed;
	private String macAddress;

	/**
	 * variables to define the matcher group for ACL parsed data .
	 */
	private String IPAccessListType;
	private int IPAccessListNum;
	private int modeNum;
	private String sourceIP;
	private String wildCardSourceIP;
	private String desIP;
	private String wildCardDesIP;

	private String className;
	private String classMapConfigurationMode;
	private String description;
	private String matchType;
	private String matchTypeValue;

	private String policyName;
	private String trafficClass;

	/**
	 * parsEthernetPE method to parse Interfaces data.
	 * 
	 * @param cmd
	 *            - the string value from "sh int" command.
	 * @return - an EthernetProtocolEndpoint object which represent each
	 *         Interface and its parsed data.
	 */
	public EthernetProtocolEndpoint parsEthernetPE(String cmd) {
		cmd = cmd.replace("\n", "");
		cmd = cmd.replace("\r", " ");
		String regex = "^(" + INTERFACE + ") is (" + ADMIN_STATUS + ").*line protocol is (" + OPERATIONAL_STATUS
				+ ").*address is (" + ADDRESS + ").*MTU (" + MTU + ").*?(" + DUPLEX + ") Duplex, (" + DUPLEX_SPEED
				+ ")";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		if (matcher.find()) {
			ifName = matcher.group(1);

			Status adminState = Status.valueOf(matcher.group(2));
			switch (adminState) {
			case up:
				ifStatus = Status.up;
				break;
			case down:
				ifStatus = Status.down;
				break;
			case testing:
				ifStatus = Status.testing;
				break;
			default:
				break;
			}

			Status status = Status.valueOf(matcher.group(3));
			switch (status) {
			case up:
				ifOperStatus = Status.up;
				break;
			case down:
				ifOperStatus = Status.down;
				break;
			case testing:
				ifOperStatus = Status.testing;
				break;
			default:
				break;
			}

			ifMTU = Integer.parseInt(matcher.group(5));

			DuplexMode duplex = DuplexMode.valueOf(matcher.group(6));

			switch (duplex) {
			case Half:
				duplexMode = DuplexMode.Half;
				break;
			case Full:
				duplexMode = DuplexMode.Full;
				break;
			default:
				duplexMode = DuplexMode.unknown;
				break;
			}
			;
			ifSpeed = matcher.group(7);
			macAddress = matcher.group(5);
		}

		EthernetProtocolEndpoint ep = new EthernetProtocolEndpoint(ifName, ifMTU, ifStatus, ifOperStatus, duplexMode,
				ifSpeed, macAddress);

		return ep;
	}

	/**
	 * parsACL method to parse AccessList data.
	 * 
	 * @param cmd
	 *            - the string value from "show access-list" command.
	 * @return - list of all access lists and their parsed data as an objects.
	 */
	public List<ACL> parsACL(String cmd) {
		List<ACL> accessList = new ArrayList<ACL>();
		ACL acl;
		cmd = cmd.trim();
		// System.out.println(cmd);
		String regex = "(" + ACESS_LIST_TYPE + ") IP access list (" + IP_ACCESS_LIST_NUM + ").*?(\\d.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		if (matcher.find()) {
			IPAccessListType = matcher.group(1);
			IPAccessListNum = Integer.parseInt(matcher.group(2));

			regex = "(" + ACCESS_LIST_MODE_NUMBER + ") " + ACCESS_LIST_MODE + "(" + SOURCE_IP + ") {0,1}("
					+ WILDCARD_SOURCE_IP + ") {0,1}(" + DES_IP + ") {0,1}(" + WILDCARD_DES_IP + ")";
			pattern = Pattern.compile(regex);

			matcher = pattern.matcher(matcher.group(3));
			while (matcher.find()) {

				modeNum = Integer.parseInt(matcher.group(1));
				sourceIP = matcher.group(2);
				wildCardSourceIP = matcher.group(3);
				desIP = matcher.group(4);
				wildCardDesIP = matcher.group(5);
				acl = new ACL(IPAccessListType, IPAccessListNum, modeNum, sourceIP, wildCardSourceIP, desIP,
						wildCardDesIP);
				accessList.add(acl);

			}
		}
		return accessList;
	}

	public List<ClassMap> parsClassMap(String cmd) {
		List<ClassMap> classMapList = new ArrayList<ClassMap>();
		ClassMap classMap;
		cmd = cmd.trim();
		String regex = "Class Map (" + CLASS_MAP_CONFIGERATION_MODE + ") (" + CLASS_NAME
				+ ") \\(id [0-9*]\\)    {0,1}(Description: ){0,1}(" + DESCRIPTION + "){0,1}(" + MATCH_TYPE + ")";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		if (matcher.find()) {
			classMapConfigurationMode = matcher.group(1);
			className = matcher.group(2);
			description = matcher.group(5);
			regex = "Match (" + MATCH_ONE_GROUP + ") {0,2}(" + MATCH_TYPE_VALUE + "){0,1}";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(matcher.group(6));
			while (matcher.find()) {

				matchType = matcher.group(1);
				matchTypeValue = matcher.group(2);
				classMap = new ClassMap(className, classMapConfigurationMode, description, matchType, matchTypeValue);
				classMapList.add(classMap);
			}
		}
		return classMapList;

	}

	public List<PolicyMap> parsPolicyMap(String cmd) {
		List<PolicyMap> policyMapList = new ArrayList<PolicyMap>();
		PolicyMap policyMap = null;
		cmd = cmd.trim();
		// System.out.println(">>>>>>"+cmd);
		cmd = "Policy Map Child     Class reem       priority 10 (%)    Policy Map policy1     Class class1    Policy Map policy2     Class laila       Average Rate Traffic Shaping       cir 384000 (bps)       bandwidth 256 (kbps)     Class class1       bandwidth 384 (kbps)       service-policy policy1";
		String regex = "Policy Map (" + POLICY_NAME + ")     (.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			policyName = matcher.group(1);
			System.out.println(">>group (0):" + matcher.group(0));
			System.out.println(">>group (1):" + matcher.group(1));
			System.out.println(">>group (2):" + matcher.group(2));

			String regex1 = "Class (" + "\\w+" + ") (.*)";
			pattern = Pattern.compile(regex1);
			matcher = pattern.matcher(matcher.group(2));
			while (matcher.find()) {
				System.out.println("$$group (0):" + matcher.group(0));
				System.out.println("$$group (1):" + matcher.group(1));
				System.out.println("$$group (2):" + matcher.group(2));

				String regex2 = "      (.*)    (Policy Map.*)";
				pattern = Pattern.compile(regex2);
				matcher = pattern.matcher(matcher.group(2));
				while (matcher.find()) {
					System.out.println("%%group (0):" + matcher.group(0));
					System.out.println("%%group (1):" + matcher.group(1));
					policyMap = new PolicyMap(policyName, trafficClass);
					policyMapList.add(policyMap);
				}

			}

		}
		return policyMapList;

	}
}
