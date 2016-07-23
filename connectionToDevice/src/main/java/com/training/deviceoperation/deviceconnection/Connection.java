package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.List;

import com.training.deviceoperation.parser.EthernetProtocolEndpoint;

/**
 * 
 * @author user
 *
 */

public interface Connection {
	public void disconnectConnection();
	public List<String> getInterfaces() throws IOException ;
	public void setHost(String host);
	public void setPort(int port);
	public String connectToDevice();
	public List<EthernetProtocolEndpoint> createEthernetPE();

}