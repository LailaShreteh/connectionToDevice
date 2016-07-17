package com.training.deviceoperation.parser;

public class dot1dTpFdbAddress extends EthernetProtocolEndpoint {

	public dot1dTpFdbAddress() {
		super.minOccurs=0;
		super.OID="1.3.6.1.2.1.17.4.3.1.1";
		super.type=type.longStringType;
		super.description="Represents media- or physical-level addresses. This is the interface&apos;s address at its protocol sublayer. For example, for an 802.x interface, this object normally contains a MAC address. The interface&apos;s media-specific MIB must define the bit and byte ordering and the format of the value of this object.For interfaces that do not have such an address (such as a serial line), this object should be null.";		
		super.status=status.mandatory;
		super.permission=permission.read_only;
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
