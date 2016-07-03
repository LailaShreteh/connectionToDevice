package com.training.deviceoperation.deviceconnection;

import java.io.IOException;

import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPConnection implements Connection{
	
	public String connectToDevice(String IP, int port) throws IOException  {
	
		TransportMapping transport = new DefaultUdpTransportMapping();
		 Snmp snmp = new Snmp(transport);
		 try{
			 	transport.listen();
			 	return "Sucess";
			 	
		 	}catch (Exception e) {
		 		return e.getMessage() + "  X_X sorry fails to connect x_x" ;
			}
	}
}

