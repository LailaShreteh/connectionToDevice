package com.training.deviceoperation.deviceconnection.model;

public class ACL {
	protected String desIP;
	protected String sourceIP;
	protected int IPAccessListNum;
	protected int AccessListModeNumber; // for permit or deny
	protected String IPAccessListType; //standard or extended.....

	public int getIPAccessListNum() {
		return IPAccessListNum;
	}

	public void setIPAccessListNum(int iPAccessListNum) {
		IPAccessListNum = iPAccessListNum;
	}

	public int getAccessListModeNumber() {
		return AccessListModeNumber;
	}

	public ACL(String iPAccessListType, int iPAccessListNum,  int accessListModeNumber, String sourceIP, String desIP) {
		this.desIP = desIP;
		this.sourceIP = sourceIP;
		IPAccessListNum = iPAccessListNum;
		AccessListModeNumber = accessListModeNumber;
		IPAccessListType = iPAccessListType;
	}

	public void setAccessListModeNumber(int accessListModeNumber) {
		AccessListModeNumber = accessListModeNumber;
	}

	public String getIPAccessListType() {
		return IPAccessListType;
	}

	public void setIPAccessListType(String iPAccessListType) {
		IPAccessListType = iPAccessListType;
	}

	public String getDesIP() {
		return desIP;
	}

	public void setDesIP(String desIP) {
		this.desIP = desIP;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public int getAccessNum() {
		return IPAccessListNum;
	}

	public void setAccessNum(int IPAccessListNum) {
		this.IPAccessListNum = IPAccessListNum;
	}
	@Override
	public String toString() {
		return ("**" + IPAccessListType + " || \t" + IPAccessListNum+ " || \t" + AccessListModeNumber + " || \t" + desIP );
	}
}
