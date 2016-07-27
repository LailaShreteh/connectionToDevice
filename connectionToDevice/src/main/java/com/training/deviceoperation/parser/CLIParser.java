package com.training.deviceoperation.parser;

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
	/*
	 * public enum enumType { up, down, testing,
	 * 
	 * }
	 */

	final static String INTERFACE = "[A-Z][A-Za-z]+[0-9/]*";
	final static String ADMIN_STATUS = "\\w+";
	final static String OPERATIONAL_STATUS = "\\w+";
	final static String ADDRESS = "[^\\s]+";
	final static String MTU = "\\d+";
	final static String DUPLEX = "\\w+";
	final static String DUPLEX_SPEED = "\\d+";

	final static String ACESS_LIST_TYPE = "^[?:Standard|Extended].*";
	final static String IP_ACCESS_LIST_NUM = "\\w+";
	final static String ACCESS_LIST_MODE_NUMBER = "\\w+";
	final static String SOURCE_IP = "[0-9.any]*";
	final static String WILDCARD_SOURCE_IP = "[0-9.]*";
	final static String DES_IP = "[0-9.any]*";
	final static String WILDCARD_DES_IP = "[0-9.]*";

	String ifName;
	Status ifStatus;
	Status ifOperStatus;
	int ifMTU;
	DuplexMode duplexMode;
	String ifSpeed;
	String macAddress;

	String IPAccessListType;
	int IPAccessListNum;
	int modeNum;
	String sourceIP;
	String desIP;

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

			// enumType operationalStatus = enumType.valueOf(matcher.group(3));

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
			// System.out.println(matcher.group(6));
			DuplexMode duplex = DuplexMode.valueOf(matcher.group(6));

			switch (duplex) {
			// case "unknown":duplexMode.add(enumType2.unknown); break;
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

	public ACL parsACL(String cmd) {
		//System.out.println("LLL" + cmd);
		cmd = cmd.trim();
		String[] splited = cmd.split("     ");
		String regex = "(" + ACESS_LIST_TYPE + ") IP access list (" + IP_ACCESS_LIST_NUM + ")";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(splited[0]);
		if (matcher.find()) {
			IPAccessListType = matcher.group(1);
			IPAccessListNum = Integer.parseInt(matcher.group(2));

			regex = "(" + ACCESS_LIST_MODE_NUMBER + ") [?:permit|deny|permit ip|deny ip]* " + "(" + SOURCE_IP + ")" ;//("	+ WILDCARD_SOURCE_IP + ")";
			pattern = Pattern.compile(regex);
			for (int i = 1; i < splited.length; i++) {
				//System.out.println(splited[i]);
				matcher = pattern.matcher(splited[i]);
				if (matcher.find()) {
					System.out.println(matcher.group(1));
					modeNum = Integer.parseInt(matcher.group(1));
					// sourceIP = matcher.group(2);

					/*
					 * switch (IPAccessListType) { case "Standard": desIP = "";
					 * //!! break; case "Extended": sourceIP = matcher.group(5);
					 * break; default: break; }
					 */
				}
			}

		}

		ACL acl = new ACL(IPAccessListType, IPAccessListNum, modeNum, sourceIP, desIP);
		return acl;
	}
}
