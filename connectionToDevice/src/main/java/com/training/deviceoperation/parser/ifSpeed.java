package com.training.deviceoperation.parser;

public class ifSpeed extends EthernetProtocolEndpoint {
	private final TypeEnum type = TypeEnum.LongQuantity;

	public ifSpeed() {
		super.minOccurs=0;
		// TODO Auto-generated constructor stub
	}
	
	public TypeEnum getType() {
		return type;
	}

}
