package com.training.deviceoperation.deviceconnection;

import java.util.List;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.ClassMap;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.deviceconnection.model.Interface_ACL;
import com.training.deviceoperation.deviceconnection.model.Interface_Policy;
import com.training.deviceoperation.deviceconnection.model.PolicyMap;
import com.training.deviceoperation.deviceconnection.model.Transaction;

/**
 * 
 * @author user
 *
 */

public interface ConnectionRouter {

	/**
	 * disconnectConnection method to close the input and the output streams and
	 * to close the Telnet or SSH connection.
	 */
	public void disconnectConnection();

	/***
	 * getACL method to get all the access control lists from a device to
	 * control which packets move through a network and to where.
	 *
	 * @return - a list of all Access Control Lists.
	 ***/
	public List<ACL> getACL();

	/***
	 * getInterfaces method to get all the interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 ***/
	public List<Interface> getInterfaces();

	/***
	 * getPolicyMap method to get all policies from a device where each policy
	 * map defines a series of actions (functions) that you want applied to a
	 * set of classified in bound traffic.
	 *
	 * @return - a list of all policy maps.
	 ***/
	public List<PolicyMap> getPolicyMap();

	/***
	 * getClassMap method to get all Class Maps from a device, where each class
	 * map define a traffic classification.
	 *
	 * @return - a list of all Class Maps.
	 ***/
	public List<ClassMap> getClassMap();

	/**
	 * connectToDevice method to open Telnet or SSH connection to a server.
	 * 
	 */
	public String connectToDevice();

	/***
	 * getEthernetPE method to get all interfaces from a device.
	 *
	 * @return - a list of all interfaces.
	 ***/
	public List<EthernetProtocolEndpoint> parsEthernetPE();

	public List<Interface_ACL> getInterface_ACL();
	
	public List<Interface_Policy> getInterface_Policy();

	public List<Transaction> getTransaction();

	public void setHost(String host);

	public void setPort(int port);


}