package com.training.deviceoperation.parser;

public class dot3StatsDuplexStatus extends EthernetProtocolEndpoint {
	public int value; // we get confused about if it's integer or enum because it take just three values !!
//	1 : unknown
//	2 : halfDuplex
//	3 : fullDuplex
	public dot3StatsDuplexStatus() {
		super.minOccurs=0;
		super.OID="1.3.6.1.2.1.10.7.2.1.19";
		super.type=type.enumType;// INTEGER !!
		super.description="Indicates the duplex mode for the interface.";
		super.permission=permission.read_only;
		super.status=status.current;
		
		// TODO Auto-generated constructor stub
	}
	public int getmaxOccurs() {
		return this.minOccurs;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
