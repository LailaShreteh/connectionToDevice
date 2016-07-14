package com.training.deviceoperation.parser;

public class type extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.enumType;

	public type() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
