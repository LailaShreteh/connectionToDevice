package com.training.deviceoperation.parser;

import java.util.List;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;

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
	EthernetProtocolEndpoint parsEthernetPE(String cmdBack);

	public ACL parsACL(String cmd);

}
