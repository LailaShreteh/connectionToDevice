package com.training.deviceoperation.parser;

public class ifMtu extends EthernetProtocolEndpoint {

	public ifMtu () {
		super.minOccurs=0;
		super.OID="	1.3.6.1.2.1.2.2.1.4";
		super.type=type.integer;
		super.description="Represents the size in octets of the largest packet that can be sent/received on the interface. For interfaces that are used for transmitting network datagrams, this is the size of the largest network datagram that can be sent on the interface.";
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
