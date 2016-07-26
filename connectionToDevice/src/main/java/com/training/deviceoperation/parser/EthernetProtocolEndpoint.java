package com.training.deviceoperation.parser;
/**
 * 
 * @author user
 *
 */

import com.training.deviceoperation.parser.CLIParser.enumType;
import com.training.deviceoperation.parser.CLIParser.enumType_Duplex;

/**
 * The EthernetProtocolEndpoint class to get each interface and it's data as an
 * object.
 */

public class EthernetProtocolEndpoint {
	/**
	 * List of attributes which represent each interface.
	 * 
	 */

	protected int minOccurs;
	protected int maxOccurs;
	protected String description; // longStringType
	protected enumType_Duplex duplexMode;
	protected enumType adminStatus;
	protected String comments; // shortStringType
	protected String entAliasMappingIdentifier;// longStringType
	protected enumType ethernetLoopback;
	protected String ifSpeed;// LongQuantity "for bandwidth"
	protected String lagEndName;// longStringType
	protected String macAddress;// longStringType
	protected int mtu;
	protected enumType operStatus;
	//protected enumType_type type;

	/*
	 * public EthernetProtocolEndpoint(int minOccurs, int maxOccurs, String
	 * description,String comments, String entAliasMappingIdentifier, String
	 * lagEndName, enumType1 ethernetLoopback,enumType3 type){
	 */
	public EthernetProtocolEndpoint(String name, int mtu, enumType adminStatus, enumType operStatus,
			enumType_Duplex duplexMode, String ifSpeed, String macAddress) {

		this.name = name;
		this.mtu = mtu;
		this.adminStatus = adminStatus;
		this.operStatus = operStatus;
		this.duplexMode = duplexMode;
		this.ifSpeed = ifSpeed;
		this.macAddress = macAddress;
		// System.out.println(macAddress);
	}

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
	public enumType_Duplex getDuplexMode() {
		return duplexMode;
	}

	/**
	 * @return the adminStatus
	 */
	public enumType getAdminStatus() {
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
	public enumType getEthernetLoopback() {
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
	public enumType getOperStatus() {
		return operStatus;
	}

	/**
	 * @return the type
	 */
	/*public enumType_type getType() {
		return type;
	}*/

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	public void setMinOccurs(int minOccurs) {
		this.minOccurs = minOccurs;
	}

	public void setMaxOccurs(int maxOccurs) {
		this.maxOccurs = maxOccurs;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDuplexMode(enumType_Duplex duplexMode) {
		this.duplexMode = duplexMode;
	}

	public void setAdminStatus(enumType adminStatus) {
		this.adminStatus = adminStatus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setEntAliasMappingIdentifier(String entAliasMappingIdentifier) {
		this.entAliasMappingIdentifier = entAliasMappingIdentifier;
	}

	public void setEthernetLoopback(enumType ethernetLoopback) {
		this.ethernetLoopback = ethernetLoopback;
	}

	public void setIfSpeed(String ifSpeed) {
		this.ifSpeed = ifSpeed;
	}

	public void setLagEndName(String lagEndName) {
		this.lagEndName = lagEndName;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setMtu(int mtu) {
		this.mtu = mtu;
	}

	public void setOperStatus(enumType operStatus) {
		this.operStatus = operStatus;
	}

//	public void setType(enumType_type type) {
//		this.type = type;
//	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * toString method to print each interface and it's data as object.
	 * 
	 * @return - each interface and it's data.
	 */
	@Override
	public String toString() {
		return ("**" + name + " || \t" + adminStatus + " || \t" + operStatus + " || \t" + mtu + " || \t" + duplexMode
				+ " || \t" + ifSpeed + " || \t" + macAddress);
	}
}
