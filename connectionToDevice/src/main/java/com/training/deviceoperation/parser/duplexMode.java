package com.training.deviceoperation.parser;

public class duplexMode extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.enumType;

	public duplexMode() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
