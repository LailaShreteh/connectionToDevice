package com.training.deviceoperation.parser;

//@Root
public class EthernetProtocolEndpoint { // we think this class maybe abstract !!

	protected int minOccurs;
	protected int maxOccurs;
	protected  String description; //longStringType
	protected enumType2 duplexMode;
	protected enumType1 adminStatus;
	protected String comments; //shortStringType
	protected String entAliasMappingIdentifier;//longStringType
	protected enumType1 ethernetLoopback;
	protected  String ifSpeed;//LongQuantity "for bandwidth"
	protected  String lagEndName;//longStringType
	protected String  macAddress;//longStringType
	protected int mtu;
	protected enumType1 operStatus;
	protected enumType3 type;
	protected String name;
}
