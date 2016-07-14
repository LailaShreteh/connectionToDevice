package com.training.deviceoperation.parser;

public class macAddress extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.longStringType;

	public macAddress() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
