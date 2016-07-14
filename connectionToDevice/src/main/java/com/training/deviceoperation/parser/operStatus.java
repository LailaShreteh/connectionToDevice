package com.training.deviceoperation.parser;

public class operStatus extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.enumType;

	public operStatus() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
