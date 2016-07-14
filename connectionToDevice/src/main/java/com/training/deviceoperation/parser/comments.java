package com.training.deviceoperation.parser;

public class comments extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.shortStringType;

	public comments() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	public TypeEnum getType() {
		return type;
	}

}
