package com.training.deviceoperation.parser;

import java.util.List;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;

/**
 * Parser interface to parse CLI and SNMP data from a device.
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

	/**
	 * parsACL method to parse AccessList data.
	 * 
	 * @param cmd
	 *            - the string value from "show access-list" command.
	 * @return - list of all access lists and their parsed data as an objects.
	 */
	List<ACL> parseACL(String cmd);

	List<ClassMap> parsClassMap(String cmd);

	List<PolicyMap> parsPolicyMap(String cmd);

	List<Transaction> parsTransaction(String cmd);

	List<Interface_ACL> parsInterface_ACL(String cmd);

	List<Interface_Policy> parsInterface_Policy(String cmd);

}
