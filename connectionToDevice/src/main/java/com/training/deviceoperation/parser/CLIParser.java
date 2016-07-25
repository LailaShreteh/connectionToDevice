package com.training.deviceoperation.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author user
 *
 */

public class CLIParser implements Parser {
	public enum enumType {
		up, down, testing,

	}
	public enum enumType_duplex {
		unknown, halfDuplex, fullDuplex, NULL,
	}

	final static String INTERFACE = "[A-Z][A-Za-z]+[0-9/]*";
	final static String ADMIN_STATUS = "\\w+";
	final static String OPERATIONAL_STATUS = "\\w+";
	final static String ADDRESS = "[^\\s]+";
	final static String MTU = "\\d+";
	final static String DUPLEX = "\\w+";
	final static String DUPLEX_SPEED = "\\d+";
	String ifName;
	enumType ifStatus;
	enumType ifOperStatus;
	int ifMTU;
	enumType_duplex duplexMode;
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
			
			enumType adminState = enumType.valueOf(matcher.group(2)); 
			switch (adminState) {
			case up:
				ifStatus = enumType.up;
				break;
			case down:
				ifStatus = enumType.down;
				break;
			case testing:
				ifStatus = enumType.testing;
				break;
			default:
				break;
			};
			enumType operationalStatus = enumType.valueOf(matcher.group(3)); 

			switch (operationalStatus) {
			case up:
				ifOperStatus = enumType.up;
				break;
			case down:
				ifOperStatus = enumType.down;
				break;
			case testing:
				ifOperStatus = enumType.testing;
				break;
			default:
				break;
			}
			;

			ifMTU = Integer.parseInt(matcher.group(5));
			enumType_duplex duplex = enumType_duplex.valueOf(matcher.group(6)); 

			switch (duplex) {
			// case "unknown":duplexMode.add(enumType2.unknown); break;
			case halfDuplex:
				duplexMode = enumType_duplex.halfDuplex;
				break;
			case fullDuplex:
				duplexMode = enumType_duplex.fullDuplex;
				break;
			default:
				duplexMode = enumType_duplex.unknown;
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
}
