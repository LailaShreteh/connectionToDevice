package com.training.deviceoperation.parser;

import java.util.List;

public interface Parser {

	/**
	 * Parse Intefaces data
	 * @param cmdBack
	 * @return
	 */
	List<EthernetProtocolEndpoint> parsEthernetPE(String cmdBack); //List of objects !!
	// SNMP OBJECTS
	// CLI STRING BACK !! LIST 

	
	
}
