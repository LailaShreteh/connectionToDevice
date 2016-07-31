package com.training.deviceoperation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.chainsaw.Main;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.Action;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;

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
	private Action classAction;

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
		cmd = "Policy Map Child     Class reem       priority 10 (%)    Policy Map policy1     Class class1    Policy Map policy2     Class laila       Average Rate Traffic Shaping       cir 384000 (bps)       bandwidth 256 (kbps)     Class class1       bandwidth 384 (kbps)       service-policy policy1";
		System.out.println(cmd);
		String regex = "(?<=Policy Map)(.*?)(?=Policy Map|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			String regex1 = "(\\w+)     (Class.*)";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(1));
			while (matcher1.find()) {
				policyName = matcher1.group(1);
				String regex2 = "(?<=Class)(.*?)(?=Class|$)";
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher2 = pattern2.matcher(matcher1.group(2));
				while (matcher2.find()) {
					String regex3 = "(\\w+)(.*)";
					Pattern pattern3 = Pattern.compile(regex3);
					Matcher matcher3 = pattern3.matcher(matcher2.group(1));
					while (matcher3.find()) {
						trafficClass = matcher3.group(1);
						// System.out.println(trafficClass);
						String regex4 = "(?<=\\s\\s\\s\\s\\s)(.*?)(?=\\s\\s\\s\\s\\s|$)";
						Pattern pattern4 = Pattern.compile(regex4);
						Matcher matcher4 = pattern4.matcher(matcher3.group(2));
						while (matcher4.find()) {
							 System.out.println(">>>>>>>group (1):" +matcher4.group(1));
							policyMap = new PolicyMap(policyName, trafficClass);
							policyMapList.add(policyMap);
							String regex5 = "(\\w+) (.*)";
							Pattern pattern5 = Pattern.compile(regex5);
							Matcher matcher5 = pattern5.matcher(matcher4.group(1).trim());
							while (matcher5.find()) {
								System.out.println("loool");
								System.out.println("group laila (1):" + matcher5.group(1));
								System.out.println("group laila (2):" + matcher5.group(2));

								Action action = Action.valueOf(matcher5.group(1));
								switch (action) {
								case BANDWIDTH:
									classAction = Action.BANDWIDTH;
									break;
								case FAIR_QUEUE:
									classAction = Action.FAIR_QUEUE;
									break;
								case DROP:
									classAction = Action.DROP;
									break;
								case POLICY:
									classAction = Action.POLICY;
									break;
								case POLICE:
									classAction = Action.POLICE;
									break;
								case police_rate_pdp:
									classAction = Action.police_rate_pdp;
									break;
								case priority:
									classAction = Action.priority;
									break;
								case queue_limit:
									classAction = Action.queue_limit;
									break;
								case random_detect:
									classAction = Action.random_detect;
									break;
								case random_detect_discard_class:
									classAction = Action.random_detect_discard_class;
									break;
								case random_detect_discard_class_based:
									classAction = Action.random_detect_discard_class_based;
									break;
								case random_detect_ecn:
									classAction = Action.random_detect_ecn;
									break;
								case random_detect_precedence:
									classAction = Action.random_detect_precedence;
									break;
								case service_policy:
									classAction = Action.service_policy;
									break;
								case set_atm_clp:
									classAction = Action.set_atm_clp;
									break;
								case set_cos:
									classAction = Action.set_cos;
									break;
								case set_discard_class:
									classAction = Action.set_discard_class;
									break;
								case set_ip_dscp:
									classAction = Action.set_ip_dscp;
									break;
								case set_fr_de:
									classAction = Action.set_fr_de;
									break;
								case set_mpls_experimental:
									classAction = Action.set_mpls_experimental;
									break;
								case set_precedence:
									classAction = Action.set_precedence;
									break;
								case set_qos_group:
									classAction = Action.set_qos_group;
									break;
								case shape:
									classAction = Action.shape;
									break;
								case shape_adaptive:
									classAction = Action.shape_adaptive;
									break;
								case shape_fecn_adapt:
									classAction = Action.shape_fecn_adapt;
									break;
								default:
									break;
								}
								Transaction transaction = new Transaction(classAction, policyName, trafficClass); // what
																													// about
																													// transaction
																													// object
																													// :/
																													// !!
							}
						}
					}

				}

			}

		}
		return policyMapList;

	}

	public static void main(String[] args) {
		CLIParser pr = new CLIParser();
		pr.parsPolicyMap("sn");
	}
}
