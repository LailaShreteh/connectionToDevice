package com.training.deviceoperation.parser;

public class ifName extends EthernetProtocolEndpoint {
	
	public ifName() {
		super.OID="	1.3.6.1.2.1.31.1.1.1.1";
		super.type=type.longStringType;
		super.description="the name of the entity.";
		super.permission=permission.read_only;
		super.status=status.current;
		
		// TODO Auto-generated constructor stub
	}

	public TypeEnum getType() {
		return this.type;
	}
	public int getminOccurs() {// here we don't know the default value to max and min occurrence 
		return this.minOccurs;
	}
	public int getmaxOccurs() {
		return this.minOccurs;
	}
	public String getDescription() {
		return this.description;
	}
	public String getOID() {
		return this.OID;
	}
	public permission getPermission() {
		return this.permission;
	}
	public status getStatus() {
		return this.status;
	}
}
