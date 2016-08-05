package com.training.deviceoperation.parser;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.databacemanager.DataTypes;
import com.training.databacemanager.JDBC;
import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.Action;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.Direction;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;

/**
 * CLIParser class to parse the data from Telnet and SSH CLI.
 * 
 * @author user
 *
 */

/**
 * see @com.training.deviceoperation.parser.Parser
 */
public class CLIParser implements Parser {

	/* regex constants to parse EthernetProtocolEndpoint. */
	final static String INTERFACE = "[A-Z][A-Za-z]+[0-9/]*";
	final static String ADMIN_STATUS = "\\w+";
	final static String OPERATIONAL_STATUS = "\\w+";
	final static String ADDRESS = "[^\\s]+";
	final static String MTU = "\\d+";
	final static String DUPLEX = "\\w+";
	final static String DUPLEX_SPEED = "\\d+";

	/* regex constants to parse ACL. */
	final static String ACESS_LIST_TYPE = "[?:Standard|Extended].*?";
	final static String IP_ACCESS_LIST_NUM = "\\w+";
	final static String ACCESS_LIST_MODE_NUMBER = "\\d+";
	final static String SOURCE_IP = "[0-9.any]*";
	final static String WILDCARD_SOURCE_IP = "[0-9.]*";
	final static String DES_IP = "any|[0-9.]*";
	final static String WILDCARD_DES_IP = "[0-9.]*";
	final static String ACCESS_LIST_MODE = "[?:permit|deny|permit ip|deny ip]*";

	/* regex constants to parse Class Map. */
	final static String CLASS_MAP_CONFIGERATION_MODE = "match-any|match-all";
	final static String CLASS_NAME = "[a-zA-Z](-|\\w+)*";
	final static String MATCH_TYPE = "Match.*?";
	final static String DESCRIPTION = "[^Match].*";
	final static String MATCH_ONE_GROUP = "[^\\s]*";
	final static String MATCH_TYPE_VALUE = "[^\\s]*";

	/* regex constants to parse Policy Map. */
	final static String POLICY_NAME = "\\w+";
	final static String TRAFFIC_CLASS = ".*";

	/*
	 * variables to define the matcher group for EthernetProtocolEndpoint parsed
	 * data.
	 */
	private String ifName = " ";
	private Status ifStatus = null;
	private Status ifOperStatus = null;
	private int ifMTU;
	private DuplexMode duplexMode = null;
	private String ifSpeed = " ";
	private String macAddress = " ";

	/* variables to define the matcher group for ACL parsed data. */
	private String ipAccessListType = " ";
	private int ipAccessListNum;
	private int modeNum;
	private String sourceIP = " ";
	private String wildCardSourceIP = " ";
	private String desIP = " ";
	private String wildCardDesIP = " ";

	/* variables to define the matcher group for Class Map parsed data. */
	private String className = " ";
	private String classMapConfigurationMode = " ";
	private String description = " ";
	private String matchType = " ";
	private String matchTypeValue = " ";

	/* variables to define the matcher group for Policy Map parsed data. */
	private String policyName = " ";
	private String trafficClass = " ";
	private Action classAction = null;

	/* variables parsing data */
	private List<EthernetProtocolEndpoint> ePEList;
	private List<ACL> accessList;
	private List<ClassMap> classMapList;
	private List<PolicyMap> policyMapList;
	private List<Transaction> transactionList;
	private List<Interface_ACL> interface_ACLList;
	private List<Interface_Policy> interface_PolicyList;

	EthernetProtocolEndpoint epObj;

	/**
	 * parsEthernetPE method to parse Interfaces data.
	 * 
	 * @param cmd
	 *            - the string value from "sh int" command.
	 * @return - an EthernetProtocolEndpoint object which represent each
	 *         Interface and its parsed data.
	 */
	public List<EthernetProtocolEndpoint> parsEthernetPE(String cmd) {
	//	%% GigabitEthernet0/0/0 is up, line protocol is up     Hardware is 4XGE-BUILT-IN, address is 503d.e596.7400 (bia 503d.e596.7400)    Internet address is 192.168.50.200/16    MTU 1500 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     Full Duplex, 1000Mbps, link type is auto, media type is T    output flow-control is on, input flow-control is on    ARP type: ARPA, ARP Timeout 04:00:00    Last input 00:00:00, output 00:01:10, output hang never    Last clearing of "show interface" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    5 minute input rate 60000 bits/sec, 49 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       3841824 packets input, 567118332 bytes, 0 no buffer       Received 1105481 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 2712211 multicast, 0 pause input       65231 packets output, 5471165 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0/0/1 is down, line protocol is down     Hardware is 4XGE-BUILT-IN, address is 0000.0000.0002 (bia 503d.e596.7401)    MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of "show interface" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    5 minute input rate 0 bits/sec, 0 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0/0/2 is down, line protocol is down     Hardware is 4XGE-BUILT-IN, address is 0000.0000.0003 (bia 503d.e596.7402)    Internet address is 20.0.0.1/24    MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of "show interface" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    30 second input rate 0 bits/sec, 0 packets/sec    30 second output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0/0/3 is down, line protocol is down     Hardware is 4XGE-BUILT-IN, address is 0000.0000.0004 (bia 503d.e596.7403)    Internet address is 30.0.0.1/24    MTU 4000 bytes, BW 1000000 Kbit/sec, DLY 10 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive not supported     Full Duplex, 1000Mbps, link type is auto, media type is SX    output flow-control is on, input flow-control is on    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of "show interface" counters never    Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    30 second input rate 0 bits/sec, 0 packets/sec    30 second output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 2 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out  %% GigabitEthernet0 is down, line protocol is down     Hardware is RP management port, address is 503d.e596.7440 (bia 503d.e596.7440)    MTU 1500 bytes, BW 10000 Kbit/sec, DLY 1000 usec,        reliability 255/255, txload 1/255, rxload 1/255    Encapsulation ARPA, loopback not set    Keepalive set (10 sec)    Half Duplex, 10Mbps, link type is auto, media type is RJ45    output flow-control is unsupported, input flow-control is unsupported    ARP type: ARPA, ARP Timeout 04:00:00    Last input never, output never, output hang never    Last clearing of "show interface" counters never    Input queue: 0/75/0/0 (size/max/drops/flushes); Total output drops: 0    Queueing strategy: fifo    Output queue: 0/40 (size/max)    5 minute input rate 0 bits/sec, 0 packets/sec    5 minute output rate 0 bits/sec, 0 packets/sec       0 packets input, 0 bytes, 0 no buffer       Received 0 broadcasts (0 IP multicasts)       0 runts, 0 giants, 0 throttles        0 input errors, 0 CRC, 0 frame, 0 overrun, 0 ignored       0 watchdog, 0 multicast, 0 pause input       0 packets output, 0 bytes, 0 underruns       0 output errors, 0 collisions, 0 interface resets       0 unknown protocol drops       0 babbles, 0 late collision, 0 deferred       0 lost carrier, 0 no carrier, 0 pause output       0 output buffer failures, 0 output buffers swapped out

		ePEList = new ArrayList<EthernetProtocolEndpoint>();

		String regex = "(?<=%%) (" + INTERFACE + ") is (" + ADMIN_STATUS + ").*?line protocol is (" + OPERATIONAL_STATUS
				+ ").*?address is (" + ADDRESS + ").*?MTU (" + MTU + ").*?(" + DUPLEX + ") Duplex, (" + DUPLEX_SPEED
				+ ")(.*?)(?=%%|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);

		while (matcher.find()) {
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

			ifSpeed = matcher.group(7);
			macAddress = matcher.group(5);
			epObj = new EthernetProtocolEndpoint(ifName, ifMTU, ifStatus, ifOperStatus, duplexMode, ifSpeed,
					macAddress);
//			Map<EthernetProtocolEndpoint , List<PolicyMap>> map = new HashMap<epObj,>;
			ePEList.add(epObj);
		}
		return ePEList;
	}

	/**
	 * parsACL method to parse AccessList data.
	 * 
	 * @param cmd
	 *            - the string value from "show access-list" command.
	 * @return - list of all access lists and their parsed data as an objects.
	 */
	public List<ACL> parsACL(String cmd) {
		//%% Standard IP access list 1     10 permit any  %% Standard IP access list 2     10 permit 127.0.0.1
		accessList = new ArrayList<ACL>();
		ACL aclObj;
		cmd = cmd.trim();
		String regex = "(?<=%%) (" + ACESS_LIST_TYPE + ") IP access list (" + IP_ACCESS_LIST_NUM
				+ ").*?(\\d.*?)(?=%%|$)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);

		while (matcher.find()) {
			ipAccessListType = matcher.group(1);
			ipAccessListNum = Integer.parseInt(matcher.group(2));
			String regex1 = "(" + ACCESS_LIST_MODE_NUMBER + ") " + ACCESS_LIST_MODE + "(" + SOURCE_IP + ") {0,1}("
					+ WILDCARD_SOURCE_IP + ") {0,1}(" + DES_IP + ") {0,1}(" + WILDCARD_DES_IP + ")";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(3));

			while (matcher1.find()) {
				modeNum = Integer.parseInt(matcher1.group(1));
				sourceIP = matcher1.group(2);
				wildCardSourceIP = matcher1.group(3);
				desIP = matcher1.group(4);
				wildCardDesIP = matcher1.group(5);
				aclObj = new ACL(ipAccessListType, ipAccessListNum, modeNum, sourceIP, wildCardSourceIP, desIP,
						wildCardDesIP);
				accessList.add(aclObj);
			}
		}
		return accessList;
	}

	public List<ClassMap> parsClassMap(String cmd) {
		//%% Class Map match-any class-default (id 0)    Match any     %% Class Map match-all class1 (id 1)   Description: first class    Match none
		classMapList = new ArrayList<ClassMap>();
		ClassMap classMap;
		cmd = cmd.trim();
		String regex = "(?<=%%) Class Map (" + CLASS_MAP_CONFIGERATION_MODE + ") (" + CLASS_NAME
				+ ") \\(id [0-9*]\\)    {0,1}(Description: ){0,1}(" + DESCRIPTION + "){0,1}(" + MATCH_TYPE
				+ ")(?=%%|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);

		while (matcher.find()) {
			classMapConfigurationMode = matcher.group(1);
			className = matcher.group(2);
			description = matcher.group(5);
			String regex2 = "Match (" + MATCH_ONE_GROUP + ") {0,2}(" + MATCH_TYPE_VALUE + "){0,1}";
			Pattern pattern1 = Pattern.compile(regex2);
			Matcher matcher1 = pattern1.matcher(matcher.group(6));
			while (matcher1.find()) {
				matchType = matcher1.group(1);
				matchTypeValue = matcher1.group(2);
				classMap = new ClassMap(className, classMapConfigurationMode, description, matchType, matchTypeValue);
				classMapList.add(classMap);
			}
		}
		return classMapList;
	}

	public List<PolicyMap> parsPolicyMap(String cmd) {
		//Policy Map policy1     Class class1    Policy Map policy2
		policyMapList = new ArrayList<PolicyMap>();
		PolicyMap policyMap = null;
		cmd = cmd.trim();
		String regex = "(?<=Policy Map) (\\w+)(.*?)(?=Policy Map|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			policyName = matcher.group(1);
			String regex1 = "(?<=Class) (\\w+)(.*?)(?=Class|$)";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(2));
			while (matcher1.find()) {
				trafficClass = matcher1.group(1);
				policyMap = new PolicyMap(policyName, trafficClass);
				policyMapList.add(policyMap);
			}

		}
		return policyMapList;
	}

	@Override
	public List<Transaction> parsTransaction(String cmd) {
		//Policy Map policy1     Class class1    Policy Map policy2

		transactionList = new ArrayList<Transaction>();
		cmd = cmd.trim();
		String regex = "(?<=Policy Map) (\\w+)(.*?)(?=Policy Map|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			String policyName = matcher.group(1);
			String regex1 = "(?<=Class) (\\w+)(.*?)(?=Class|$)";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(2));
			while (matcher1.find()) {
				String className = matcher1.group(1);
				String regex2 = "(?<=\\s\\s\\s\\s\\s)([a-zA-Z](-|\\w+)*)(.*?)(?=\\s\\s\\s\\s\\s|$)";
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher2 = pattern2.matcher(matcher1.group(2));
				while (matcher2.find()) {
					switch (matcher2.group(1)) {
					case "bandwidth":
						classAction = Action.BANDWIDTH;
						break;
					case "fair-queue":
						classAction = Action.FAIR_QUEUE;
						break;
					case "drop":
						classAction = Action.DROP;
						break;
					case "policy":
						classAction = Action.POLICY;
						break;
					case "police":
						classAction = Action.POLICE;
						break;
					case "police rate pdp":
						classAction = Action.police_rate_pdp;
						break;
					case "priority":
						classAction = Action.priority;
						break;
					case "queue-limit":
						classAction = Action.queue_limit;
						break;
					case "random-detect":
						classAction = Action.random_detect;
						break;
					case "random-detect discard-class":
						classAction = Action.random_detect_discard_class;
						break;
					case "random-detect discard-class-based":
						classAction = Action.random_detect_discard_class_based;
						break;
					case "random-detect ecn":
						classAction = Action.random_detect_ecn;
						break;
					case "random-detect precedence":
						classAction = Action.random_detect_precedence;
						break;
					case "service-policy":
						classAction = Action.service_policy;
						break;
					case "set atm-clp":
						classAction = Action.set_atm_clp;
						break;
					case "set cos":
						classAction = Action.set_cos;
						break;
					case "set discard-class":
						classAction = Action.set_discard_class;
						break;
					case "set [ip] dscp":
						classAction = Action.set_ip_dscp;
						break;
					case "set fr-de":
						classAction = Action.set_fr_de;
						break;
					case "set mpls experimental":
						classAction = Action.set_mpls_experimental;
						break;
					case "set precedence":
						classAction = Action.set_precedence;
						break;
					case "set qos-group":
						classAction = Action.set_qos_group;
						break;
					case "shape":
						classAction = Action.shape;
						break;
					case "shape adaptive":
						classAction = Action.shape_adaptive;
						break;
					case "shape fecn-adapt":
						classAction = Action.shape_fecn_adapt;
						break;
					default:
						break;
					}

					Transaction transaction = new Transaction(classAction, policyName, className);
					System.out.println(transaction);
					transactionList.add(transaction);
				}

			}
		}
		return transactionList;
	}

	@Override
	public List<Interface_ACL> parsInterface_ACL(String cmd) {
		//interface GigabitEthernet0/0/0  ip access-group 2 out interface GigabitEthernet0/0/1 interface GigabitEthernet0/0/2 interface GigabitEthernet0/0/3 interface GigabitEthernet0

		interface_ACLList = new ArrayList<Interface_ACL>();
		cmd = cmd.trim();
		Interface_ACL interface_acl;
		String regex = "(?<=interface) ([A-Z][A-Za-z]+[0-9/]*)(.*?)(?=interface|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			String interfaceName = matcher.group(1);
			String regex1 = "(?<=ip access-group) (\\w+)(.*?)(?=ip access-group|$)";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(2).trim());
			Direction direction = null;
			while (matcher1.find()) {

				String aclName = matcher1.group(1);

				switch (matcher1.group(2).trim()) {
				case "in":
					direction = Direction.in;
					break;
				case "out":
					direction = Direction.out;
					break;
				default:
					break;
				}
				interface_acl = new Interface_ACL(direction, aclName, interfaceName);
				;
				interface_ACLList.add(interface_acl);

			}
		}
		return interface_ACLList;
	}

	@Override
	public List<Interface_Policy> parsInterface_Policy(String cmd) {
//policy-map policy1 policy-map policy2 interface GigabitEthernet0/0/0  service-policy input policy1  service-policy output policy1 interface GigabitEthernet0/0/1  service-policy output policy1 interface GigabitEthernet0/0/2 interface GigabitEthernet0/0/3 interface GigabitEthernet0

		interface_PolicyList = new ArrayList<Interface_Policy>();
		cmd = cmd.trim();
		String regex = "(?<=interface) ([A-Z][A-Za-z]+[0-9/]*)(.*?)(?=interface|$)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cmd);
		while (matcher.find()) {
			
			String interfaceName = matcher.group(1);
			String regex1 = "(?<=service-policy) (\\w+) (\\w+).*?(?=service-policy|$)";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(matcher.group(2).trim());
			Direction direction = null;
			while (matcher1.find()) {
				String policyName = matcher1.group(2);
				switch (matcher1.group(1).trim()) {
				case "input":
					direction = Direction.in;
					break;
				case "output":
					direction = Direction.out;
					break;
				default:
					break;
				}

				Interface_Policy interface_policy = new Interface_Policy(direction, policyName, interfaceName);
				interface_PolicyList.add(interface_policy);

			}
		}
		return interface_PolicyList;
	}

	public List<EthernetProtocolEndpoint> getePEList() {
		return ePEList;
	}

	public void setePEList(List<EthernetProtocolEndpoint> ePEList) {
		this.ePEList = ePEList;
	}

	public List<ACL> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<ACL> accessList) {
		this.accessList = accessList;
	}

	public List<ClassMap> getClassMapList() {
		return classMapList;
	}

	public void setClassMapList(List<ClassMap> classMapList) {
		this.classMapList = classMapList;
	}

	public List<PolicyMap> getPolicyMapList() {
		return policyMapList;
	}

	public void setPolicyMapList(List<PolicyMap> policyMapList) {
		this.policyMapList = policyMapList;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public List<Interface_ACL> getInterface_ACLList() {
		return interface_ACLList;
	}

	public void setInterface_ACLList(List<Interface_ACL> interface_ACLList) {
		this.interface_ACLList = interface_ACLList;
	}

	public List<Interface_Policy> getInterface_PolicyList() {
		return interface_PolicyList;
	}

	public void setInterface_PolicyList(List<Interface_Policy> interface_PolicyList) {
		this.interface_PolicyList = interface_PolicyList;
	}

}
