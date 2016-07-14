package com.training.deviceoperation.parser;

public class mtu extends EthernetProtocolEndpoint {
	private int type;

	public mtu() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	


}
