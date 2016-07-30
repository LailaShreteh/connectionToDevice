package com.training.deviceoperation.deviceconnection.model;

/**
 * The ACL class represents each Access Control List and its criteria which
 * can specified to control which packets move through a network and to where.
 * 
 */
public class ACL {
	protected String desIP;
	protected String sourceIP;
	protected int IPAccessListNum;
	protected int AccessListModeNumber;
	protected String IPAccessListType;
	protected String WildCardDesIP;
	protected String WildCardSourceIP;

	public int getIPAccessListNum() {
		return IPAccessListNum;
	}

	public void setIPAccessListNum(int iPAccessListNum) {
		IPAccessListNum = iPAccessListNum;
	}

	public int getAccessListModeNumber() {
		return AccessListModeNumber;
	}

	public ACL(String iPAccessListType, int iPAccessListNum, int accessListModeNumber, String sourceIP,
			String WildCardSourceIP, String desIP, String WildCardDesIP) {
		this.desIP = desIP;
		this.sourceIP = sourceIP;
		this.IPAccessListNum = iPAccessListNum;
		this.AccessListModeNumber = accessListModeNumber;
		this.IPAccessListType = iPAccessListType;
		this.WildCardDesIP = WildCardDesIP;
		this.WildCardSourceIP = WildCardSourceIP;

	}

	public String getWildCardDesIP() {
		return WildCardDesIP;
	}

	public void setWildCardDesIP(String wildCardDesIP) {
		WildCardDesIP = wildCardDesIP;
	}

	public String getWildCardSourceIP() {
		return WildCardSourceIP;
	}

	public void setWildCardSourceIP(String wildCardSourceIP) {
		WildCardSourceIP = wildCardSourceIP;
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
		return ("**" + IPAccessListType + " || \t" + IPAccessListNum + " || \t" + AccessListModeNumber + " || \t"
				+ sourceIP + " || \t" + WildCardSourceIP + " || \t" + desIP + " || \t" + WildCardDesIP);
	}
}
