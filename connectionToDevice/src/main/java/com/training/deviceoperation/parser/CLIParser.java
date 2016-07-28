package com.training.deviceoperation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */

public class CLIParser implements Parser {

	final static String INTERFACE = "[A-Z][A-Za-z]+[0-9/]*";
	final static String ADMIN_STATUS = "\\w+";
	final static String OPERATIONAL_STATUS = "\\w+";
	final static String ADDRESS = "[^\\s]+";
	final static String MTU = "\\d+";
	final static String DUPLEX = "\\w+";
	final static String DUPLEX_SPEED = "\\d+";

	final static String ACESS_LIST_TYPE = "^[?:Standard|Extended].*";
	final static String IP_ACCESS_LIST_NUM = "\\w+";
	final static String ACCESS_LIST_MODE_NUMBER = "\\d+";
	final static String SOURCE_IP = "[0-9.any]*";
	final static String WILDCARD_SOURCE_IP = "[0-9.]*";
	final static String DES_IP = "any|[0-9.]*";
	final static String WILDCARD_DES_IP = "[0-9.]*";
	final static String ACCESS_LIST_MODE = "[?:permit|deny|permit ip|deny ip]*";

	private String ifName;
	private Status ifStatus;
	private Status ifOperStatus;
	private int ifMTU;
	private DuplexMode duplexMode;
	private String ifSpeed;
	private String macAddress;
	private String IPAccessListType;
	private int IPAccessListNum;
	private int modeNum;
	private String sourceIP;
	private String wildCardSourceIP;
	private String desIP;
	private String wildCardDesIP;

	/**
	 * see @com.training.deviceoperation.parser.Parser
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

	public List<ACL> parsACL(String cmd) {
		ACL acl;
		List<ACL> accessList = new ArrayList<ACL>();
		cmd = cmd.trim();
		//System.out.println(cmd);
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
				desIP=matcher.group(4);
				wildCardDesIP = matcher.group(5);
				 acl = new ACL(IPAccessListType, IPAccessListNum, modeNum, sourceIP, wildCardSourceIP, desIP, wildCardDesIP);
				 accessList.add(acl);

			}
		}
		return accessList;
	}
}
