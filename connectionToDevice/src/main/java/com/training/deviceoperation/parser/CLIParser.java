package com.training.deviceoperation.parser;


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
	public EthernetProtocolEndpoint parsEthernetPE(String cmd) {
		// // ifName
		String pattern1 = "^[A-Z][A-Za-z]+[0-9/]*[^\\s]";
		Pattern patternObj1 = Pattern.compile(pattern1);
		String ifName = new String();
		// ifStatus >> admin Status "we are not sure :/"!!
		String pattern2 = "^[A-Z]*[a-zA-Z0-9\\/].* ";
		Pattern patternObj2 = Pattern.compile(pattern2);
		enumType1 ifStatus = null;
		// operStatus
		String pattern3 = "^[A-Z]*[a-zA-Z0-9\\/].*";
		Pattern patternObj3 = Pattern.compile(pattern3);
		enumType1 ifOperStatus = null;
		// ifMTU
		String pattern4 = "\\bMTU\\b ([0-9])[^\\s]+";
		Pattern patternObj4 = Pattern.compile(pattern4);
		Integer ifMTU = 0;
		// duplexMode
		String pattern5 = "[^\\s].*\\bDuplex\\b[^\\s]+";
		Pattern patternObj5 = Pattern.compile(pattern5);
		enumType2 duplexMode = null;
		// ifSpeed
		// [^\\s].*\\bDuplex,\\b].*
		String pattern6 = "[^\\s].*\\bDuplex\\b[^\\s].*";
		Pattern patternObj6 = Pattern.compile(pattern6);
		String ifSpeed = new String();
		// macAddress
		String pattern7 = ".*\\bHardware\\b.*[^\\s]";
		Pattern patternObj7 = Pattern.compile(pattern7);
		String macAddress = new String();

		String[] lines = cmd.split(System.getProperty("line.separator"));
		Matcher matcher1;
		Matcher matcher2;
		Matcher matcher3;
		Matcher matcher4;
		Matcher matcher5;
		Matcher matcher6;
		Matcher matcher7;
		for (int i = 0; i < lines.length - 1; i++) {
			matcher1 = patternObj1.matcher(lines[i]);
			matcher2 = patternObj2.matcher(lines[i]);
			matcher3 = patternObj3.matcher(lines[i]);
			matcher4 = patternObj4.matcher(lines[i]);
			matcher5 = patternObj5.matcher(lines[i]);
			matcher6 = patternObj6.matcher(lines[i]);
			matcher7 = patternObj7.matcher(lines[i]);

			if (matcher1.find()) {
				ifName = matcher1.group(0);
			}
//			System.out.println(matcher2.find());
//			System.out.println(lines[i]);
			if (matcher2.find()) {
				switch (matcher2.group(0).substring(matcher2.group(0).indexOf("is ")+3, matcher2.group(0).indexOf(","))) {
				case "up":
					ifStatus = enumType1.up;
					break;
				case "down":
					ifStatus = enumType1.down ;
					break;
				case "testing":
					ifStatus = enumType1.testing;
					break;
				default:
					break;
				}
			}
			if (matcher3.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("protocol
				// is"), m.group(0).length()-1));
				switch (matcher3.group(0).substring(matcher3.group(0).indexOf("is ") + 3,
						matcher3.group(0).indexOf(","))) {
				case "up":
					ifOperStatus = enumType1.up;
					break;
				case "down":
					ifOperStatus = enumType1.down;
					break;
				case "testing":
					ifOperStatus = enumType1.testing;
					break;
				default:
					break;
				}
			}
			if (matcher4.find()) {
				// System.out.println(m.group(0));
				ifMTU = Integer.parseInt(matcher4.group(0).substring(4, matcher4.group(0).length()));
			}

			if (matcher5.find()) {
				// System.out.println(m.group(0));
				switch (matcher5.group(0)) {
				// case "unknown":duplexMode.add(enumType2.unknown); break;
				case "Half Duplex,":
					duplexMode = enumType2.halfDuplex;
					break;
				case "Full Duplex,":
					duplexMode = enumType2.fullDuplex;
					break;
				default:
					duplexMode = enumType2.unknown;
					break;
				}
			}
			if (matcher6.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("Duplex,
				// ")+8, m.group(0).indexOf("ps,")+2));
				ifSpeed = matcher6.group(0).substring(matcher6.group(0).indexOf("Duplex, ") + 8,
						matcher6.group(0).indexOf("ps,") + 2);
			}

			if (matcher7.find()) {
				// System.out.println(m.group(0).substring(m.group(0).indexOf("
				// address is ")+12, m.group(0).indexOf("(bia")));
				macAddress = matcher7.group(0).substring(matcher7.group(0).indexOf("address is ") + 11,
						matcher7.group(0).indexOf("(bia"));
			}
		}

		EthernetProtocolEndpoint ep = new EthernetProtocolEndpoint(ifName, ifMTU, ifStatus, ifOperStatus,
					duplexMode, ifSpeed, macAddress);

		return ep;

	}
}
