package com.training.deviceoperation.parser;

public class entAliasMappingIdentifier extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.longStringType;

	public entAliasMappingIdentifier() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
