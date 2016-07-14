package com.training.deviceoperation.parser;

public class lagEndName extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.longStringType;

	public lagEndName() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
