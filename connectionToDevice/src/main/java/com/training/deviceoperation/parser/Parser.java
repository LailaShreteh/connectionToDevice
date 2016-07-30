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

	/**
	 * parsACL method to parse AccessList data.
	 * 
	 * @param cmd
	 *            - the string value from "show access-list" command.
	 * @return - list of all access lists and their parsed data as an objects.
	 */
	List<ACL> parsACL(String cmd);
}
