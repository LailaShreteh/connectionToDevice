package com.training.deviceoperation.deviceconnection;

import java.io.IOException;
import java.util.List;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.training.deviceoperation.parser.*;

public class SNMPConnection implements ConnectionRouter{
	private String host;
	private int port;
	public SNMPConnection ()
	{

	}
	public String connectToDevice()  {
	
		TransportMapping transport = null;
		try {
			transport = new DefaultUdpTransportMapping();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 Snmp snmp = new Snmp(transport);
		 try{
			 	transport.listen();
			 	return "Sucess";
			 	
		 	}catch (Exception e) {
		 		return e.getMessage() + "  X_X sorry fails to connect x_x" ;
			}
	}

	public List<String> getInterfaces()  {
		// TODO Auto-generated method stub
		return null;
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
	public List<EthernetProtocolEndpoint> createEthernetPE() {
		// TODO Auto-generated method stub
		return null;
	}

}

