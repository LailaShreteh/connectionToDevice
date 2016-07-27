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
	String ifName;
	Status ifStatus;
	Status ifOperStatus;
	int ifMTU;
	DuplexMode duplexMode;
	String ifSpeed;
	String macAddress;

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
		cmd = cmd.replace("\n", "");
		cmd = cmd.replace("\r", " ");
		System.out.println(cmd);
		
		
		
		return null;
	}
}
