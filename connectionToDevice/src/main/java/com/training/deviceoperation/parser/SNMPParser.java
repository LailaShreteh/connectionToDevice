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
 * 
 * @author user
 *
 */
public class SNMPParser implements Parser {

	@Override
	public List<EthernetProtocolEndpoint> parsEthernetPE(String cmdBack) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACL> parsACL(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassMap> parsClassMap(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PolicyMap> parsPolicyMap(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> parsTransaction(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interface_ACL> parsInterface_ACL(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interface_Policy> parsInterface_Policy(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

}
