package com.training.deviceoperation.parser;

public class ethernetLoopback extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.enumType;

	public ethernetLoopback() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
