package com.training.deviceoperation.parser;

import java.util.List;

/**
 * 
 * @author user
 *
 */

public interface Parser {

	/**
	 * parsEthernetPE method to parse Interfaces data.
	 * 
	 * @param cmdBack
	 *            - the string value from "sh int" command.
	 * @return - list of all interfaces and their parsed data as an objects.
	 */
	List<EthernetProtocolEndpoint> parsEthernetPE(String cmdBack);

}
