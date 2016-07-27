package com.training.deviceoperation.deviceconnection;

import java.util.List;

import com.training.deviceoperation.deviceconnection.model.ACL;
import com.training.deviceoperation.deviceconnection.model.Interface;
import com.training.deviceoperation.parser.*;

/**
 * 
 * @author user
 *
 */

public interface ConnectionRouter {
	public void disconnectConnection();

	public List<ACL> getACL();

	public List<Interface> getInterfaces();

	public void setHost(String host);

	public void setPort(int port);

	public String connectToDevice();

	public List<EthernetProtocolEndpoint> createEthernetPE();

}