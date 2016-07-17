package com.training.deviceoperation.parser;

public class ifSpeed extends EthernetProtocolEndpoint {

	public ifSpeed() {
		super.minOccurs=0;
		super.OID="	1.3.6.1.2.1.2.2.1.5";
		super.type=type.LongQuantity;
		super.description="Specifies an estimate of the interface&apos;s current bandwidth in bits per second. For interfaces that do not vary in bandwidth or for those where no accurate estimation can be made, this should contain the nominal bandwidth. For a sublayer that has no concept of bandwidth, this object should be zero.";
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
