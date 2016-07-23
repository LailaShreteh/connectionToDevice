package com.training.deviceoperation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author user
 *
 */

public class CLIParser implements Parser {

	/**
	 * see @com.training.deviceoperation.parser.Parser
	 */
	@Override
	public List<EthernetProtocolEndpoint> parsEthernetPE(String cmd) {
		// // ifName

		String pattern = "^[A-Z][A-Za-z]+[0-9/]*[^\\s]";
		Pattern patternObj = Pattern.compile(pattern);
		List<String> ifName = new ArrayList<String>();
		String[] lines = cmd.split(System.getProperty("line.separator"));
		Matcher matcher;
		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				ifName.add(matcher.group(0));
			}
		}

		List<EthernetProtocolEndpoint> epeList = new ArrayList<EthernetProtocolEndpoint>();

		// ifStatus >> admin Status "we are not sure :/"!!
		// ^[A-Z]*[a-zA-Z0-9\\/].*
		pattern = "^[A-Z][A-Za-z]+[0-9/]* is (\\w+)* ";
		patternObj = Pattern.compile(pattern);
		List<enumType1> ifStatus = new ArrayList<enumType1>();
		lines = cmd.split(System.getProperty("line.separator"));

		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				switch (matcher.group(1)) {
				case "up":
					ifStatus.add(enumType1.up);
					break;
				case "down":
					ifStatus.add(enumType1.down);
					break;
				case "testing":
					ifStatus.add(enumType1.testing);
					break;
				default:
					break;
				}
			}
		}
		// operStatus
		pattern = "^[A-Z]*[a-zA-Z0-9\\/].*";
		patternObj = Pattern.compile(pattern);
		List<enumType1> ifOperStatus = new ArrayList<enumType1>();
		lines = cmd.split(System.getProperty("line.separator"));

		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("protocol
				// is"), m.group(0).length()-1));
				switch (matcher.group(0).substring(matcher.group(0).indexOf("is ") + 3,
						matcher.group(0).indexOf(","))) {
				case "up":
					ifOperStatus.add(enumType1.up);
					break;
				case "down":
					ifOperStatus.add(enumType1.down);
					break;
				case "testing":
					ifOperStatus.add(enumType1.testing);
					break;
				default:
					break;
				}
			}
		}

		// System.out.println(ifStatus);

		// ifMTU
		pattern = "\\bMTU\\b ([0-9])[^\\s]+";
		patternObj = Pattern.compile(pattern);
		List<Integer> ifMTU = new ArrayList<Integer>();
		lines = cmd.split(System.getProperty("line.separator"));

		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				// System.out.println(m.group(0));
				ifMTU.add(Integer.parseInt(matcher.group(0).substring(4, matcher.group(0).length())));
			}
		}
		// duplexMode
		pattern = "[^\\s].*\\bDuplex\\b[^\\s]+";
		patternObj = Pattern.compile(pattern);
		List<enumType2> duplexMode = new ArrayList<enumType2>();
		lines = cmd.split(System.getProperty("line.separator"));

		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				// System.out.println(m.group(0));
				switch (matcher.group(0)) {
				// case "unknown":duplexMode.add(enumType2.unknown); break;
				case "Half Duplex,":
					duplexMode.add(enumType2.halfDuplex);
					break;
				case "Full Duplex,":
					duplexMode.add(enumType2.fullDuplex);
					break;
				default:
					duplexMode.add(enumType2.unknown);
					break;
				}
			}
		}

		// ifSpeed
		// [^\\s].*\\bDuplex,\\b].*
		pattern = "[^\\s].*\\bDuplex\\b[^\\s].*";
		patternObj = Pattern.compile(pattern);
		List<String> ifSpeed = new ArrayList<String>();
		lines = cmd.split(System.getProperty("line.separator"));
		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("Duplex,
				// ")+8, m.group(0).indexOf("ps,")+2));
				ifSpeed.add(matcher.group(0).substring(matcher.group(0).indexOf("Duplex, ") + 8,
						matcher.group(0).indexOf("ps,") + 2));
			}
		}
		// macAddress
		int sos = 0;
		pattern = ".*\\bHardware\\b.*[^\\s]";
		patternObj = Pattern.compile(pattern);
		List<String> macAddress = new ArrayList<String>();
		lines = cmd.split(System.getProperty("line.separator"));
		for (int i = 1; i < lines.length - 1; i++) {
			matcher = patternObj.matcher(lines[i]);
			if (matcher.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("
				// address is ")+12, m.group(0).indexOf("(bia")));
				macAddress.add(matcher.group(0).substring(matcher.group(0).indexOf("address is ") + 11,
						matcher.group(0).indexOf("(bia")));
			}
		}

		EthernetProtocolEndpoint ep;
		// System.out.println(duplexMode.size());
		for (int i = 0; i < ifName.size(); i++) {
			ep = new EthernetProtocolEndpoint(ifName.get(i), ifMTU.get(i), ifStatus.get(i), ifOperStatus.get(i),
					duplexMode.get(i), ifSpeed.get(i), macAddress.get(i));
			epeList.add(ep);
		}
		for (int i = 0; i < ifName.size(); i++)
			System.out.println(epeList.get(i).toString());

		return epeList;

	}
}
