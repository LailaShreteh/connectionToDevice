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
	/**
	 * @return the minOccurs
	 */
	public int getMinOccurs() {
		
		return minOccurs;
	}
	/**
	 * @return the maxOccurs
	 */
	public int getMaxOccurs() {
		return maxOccurs;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the duplexMode
	 */
	public enumType2 getDuplexMode() {
		return duplexMode;
	}
	/**
	 * @return the adminStatus
	 */
	public enumType1 getAdminStatus() {
		return adminStatus;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @return the entAliasMappingIdentifier
	 */
	public String getEntAliasMappingIdentifier() {
		return entAliasMappingIdentifier;
	}
	/**
	 * @return the ethernetLoopback
	 */
	public enumType1 getEthernetLoopback() {
		return ethernetLoopback;
	}
	/**
	 * @return the ifSpeed
	 */
	public String getIfSpeed() {
		return ifSpeed;
	}
	/**
	 * @return the lagEndName
	 */
	public String getLagEndName() {
		return lagEndName;
	}
	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}
	/**
	 * @return the mtu
	 */
	public int getMtu() {
		return mtu;
	}
	/**
	 * @return the operStatus
	 */
	public enumType1 getOperStatus() {
		return operStatus;
	}
	/**
	 * @return the type
	 */
	public enumType3 getType() {
		return type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
