package com.training.deviceoperation.deviceconnection;

import java.util.List;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.EthernetProtocolEndpoint;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.deviceconnection.model.Policy_map;
/**
 * 
 * @author user
 *
 */

public interface ConnectionRouter {
	public void disconnectConnection();

	public List<ACL> getACL();

	public List<Interface> getInterfaces();

	public List<Policy_map> getPolicy_map();

	public void setHost(String host);

	public void setPort(int port);

	public String connectToDevice();

	public List<EthernetProtocolEndpoint> getEthernetPE();

}