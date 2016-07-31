package com.training.deviceoperation.deviceconnection.model;

import com.training.deviceoperation.parser.DuplexMode;
import com.training.deviceoperation.parser.Status;

/**
 * The EthernetProtocolEndpoint class represents each interface and its data.
 * 
 * @author user
 */

public class EthernetProtocolEndpoint {

	/** List of attributes which represent each interface. */
	protected int minOccurs;
	protected int maxOccurs;
	protected String description;
	protected DuplexMode duplexMode;
	protected Status adminStatus;
	protected String comments;
	protected String entAliasMappingIdentifier;
	protected Status ethernetLoopback;
	protected String ifSpeed;
	protected String lagEndName;
	protected String macAddress;
	protected int mtu;
	protected Status operStatus;
	protected String name;

	public EthernetProtocolEndpoint(String name, int mtu, Status adminStatus, Status operStatus, DuplexMode duplexMode,
			String ifSpeed, String macAddress) {

		this.name = name;
		this.mtu = mtu;
		this.adminStatus = adminStatus;
		this.operStatus = operStatus;
		this.duplexMode = duplexMode;
		this.ifSpeed = ifSpeed;
		this.macAddress = macAddress;
	}

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
	public DuplexMode getDuplexMode() {
		return duplexMode;
	}

	/**
	 * @return the adminStatus
	 */
	public Status getAdminStatus() {
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
	public Status getEthernetLoopback() {
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
	public Status getOperStatus() {
		return operStatus;
	}

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

	public void setDuplexMode(DuplexMode duplexMode) {
		this.duplexMode = duplexMode;
	}

	public void setAdminStatus(Status adminStatus) {
		this.adminStatus = adminStatus;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setEntAliasMappingIdentifier(String entAliasMappingIdentifier) {
		this.entAliasMappingIdentifier = entAliasMappingIdentifier;
	}

	public void setEthernetLoopback(Status ethernetLoopback) {
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

	public void setOperStatus(Status operStatus) {
		this.operStatus = operStatus;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * toString method to print each interface and its data as object.
	 * 
	 * @return - each interface and its data.
	 */
	@Override
	public String toString() {
		return ("**" + name + " || \t" + adminStatus + " || \t" + operStatus + " || \t" + mtu + " || \t" + duplexMode
				+ " || \t" + ifSpeed + " || \t" + macAddress);
	}
}
