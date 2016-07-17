package com.training.deviceoperation.parser;

public class entAliasMappingIdentifier extends EthernetProtocolEndpoint {

	public entAliasMappingIdentifier() {
		super.minOccurs=0;
		super.OID="1.3.6.1.2.1.47.1.3.2.1.2";
		super.type=type.longStringType;// INTEGER !!
		super.description="## Internal use only ##";
		super.permission=permission.read_only;
		super.status=status.current;
		
		// TODO Auto-generated constructor stub
	}
	public int getmaxOccurs() {
		return this.minOccurs;
	}
	public TypeEnum getType() {
		return this.type;
	}
	public int getminOccurs() {
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
