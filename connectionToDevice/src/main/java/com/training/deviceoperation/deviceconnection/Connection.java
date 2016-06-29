package com.training.deviceoperation.deviceconnection;

public interface Connection {
	
	String ip = "4.4.4.4";
	String id="";
	String version="";

	
	public String connectClass (String host,int port);
	

	
}