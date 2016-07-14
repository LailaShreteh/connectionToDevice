package com.training.deviceoperation.parser;

public class adminStatus extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.enumType;
	
	public adminStatus() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	public TypeEnum getType() {
		return type;
	}

}
