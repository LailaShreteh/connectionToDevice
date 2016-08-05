package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.List;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;
import com.training.deviceoperation.parser.*;

/**
 * 
 * @author user
 *
 */
public class SNMPConnection implements ConnectionRouter {
	private String host;
	private int port;

	public SNMPConnection() {

	}

	public String connectToDevice() {

		TransportMapping transport = null;
		try {
			transport = new DefaultUdpTransportMapping();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Snmp snmp = new Snmp(transport);
		try {
			transport.listen();
			return "Sucess";

		} catch (Exception e) {
			return e.getMessage() + "  X_X sorry fails to connect x_x";
		}
	}


	public void disconnectConnection() {
		// TODO Auto-generated method stub

	}

	public void setHost(String host) {
		// TODO Auto-generated method stub

	}

	public void setPort(int port) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EthernetProtocolEndpoint> parsEthernetPE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ACL> getACL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interface> getInterfaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PolicyMap> getPolicyMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassMap> getClassMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interface_ACL> getInterface_ACL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interface_Policy> getInterface_Policy() {
		// TODO Auto-generated method stub
		return null;
	}

}
