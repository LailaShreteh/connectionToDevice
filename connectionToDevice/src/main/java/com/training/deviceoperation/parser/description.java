package com.training.deviceoperation.parser;

public class description extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.longStringType;
	
	public description() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	public TypeEnum getType() {
		return type;
	}

}
