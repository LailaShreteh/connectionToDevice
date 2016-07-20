package com.training.deviceoperation.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLIParser implements Parser{
	
	public CLIParser ()// here :3 
	{
		
	}
	public EthernetProtocolEndpoint parsEthernetPE(String cmd)
	{
//		// ifName
		
		String pattern = "^[A-Z]*[a-zA-Z]*[0-9\\/]*[^\\s]+";
		Pattern r = Pattern.compile(pattern);
		List<String> ifName = new ArrayList<String>();
		String[] lines = cmd.split(System.getProperty("line.separator"));
		Matcher m;
		for (int i = 1; i < lines.length - 1; i++) {
			 m = r.matcher(lines[i]);
			if (m.find()) {
				ifName.add(m.group(0));
			}
		}
		
		List<EthernetProtocolEndpoint> ePE= new ArrayList<EthernetProtocolEndpoint>();
		
		//ifStatus >> admin Status "we are not sure :/"!!
		//^[A-Z]*[a-zA-Z0-9\\/].*
		pattern = "^[A-Z]*[a-zA-Z0-9\\/].*";
		r = Pattern.compile(pattern);
		List<enumType1> ifStatus = new ArrayList<enumType1>();
		lines = cmd.split(System.getProperty("line.separator"));
		
		for (int i = 1; i < lines.length - 1; i++) {
			 m = r.matcher(lines[i]);
			if (m.find()) {
				//System.out.println(m.group(0).substring(m.group(0).indexOf(" "), m.group(0).indexOf(",")-1));
				switch (m.group(0).substring(m.group(0).indexOf("is ")+3, m.group(0).indexOf(",")))
				{
				case "up": ifStatus.add(enumType1.up); break;
				case "down": ifStatus.add(enumType1.down); break;
				case "testing": ifStatus.add(enumType1.testing); break;
				default: break;
				}
			}
		}
	//	System.out.println(ifStatus);
		
		//ifMTU
		pattern = "\\bMTU\\b ([0-9])[^\\s]+";
		r = Pattern.compile(pattern);
		List<Integer> ifMTU = new ArrayList<Integer>();
		lines = cmd.split(System.getProperty("line.separator"));
		
		for (int i = 1; i < lines.length - 1; i++) {
			 m = r.matcher(lines[i]);
			if (m.find()) {
				System.out.println(m.group(0));
				ifMTU.add(Integer.parseInt(m.group(0).substring(4, m.group(0).length())));
			}
		}
		//System.out.println(ifMTU);
		/*EthernetProtocolEndpoint ep = new EthernetProtocolEndpoint(int minOccurs, int maxOccurs, String description, enumType2 duplexMode,
				enumType1 adminStatus, String comments, String entAliasMappingIdentifier, enumType1 ethernetLoopback,
				String ifSpeed, String lagEndName, String macAddress, int mtu, enumType1 operStatus, enumType3 type,
				format);
		*/
		EthernetProtocolEndpoint ep;
		for(int i=0;i<ifName.size();i++)
		{
			ep = new EthernetProtocolEndpoint(ifName.get(i),ifMTU.get(i),ifStatus.get(i));
			ePE.add(ep);
		}


		
		return null;
		
	}
}
