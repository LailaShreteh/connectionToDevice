package com.training.deviceoperation.deviceconnection.model;

/**
 * The ACL class represents each Access Control List and its criteria which can
 * specified to control which packets move through a network and to where.
 * 
 * @author user
 */
public class ACL {
	protected String desIP;
	protected String sourceIP;
	protected int ipAccessListNum;
	protected int accessListModeNumber;
	protected String ipAccessListType;
	protected String wildCardDesIP;
	protected String wildCardSourceIP;

	public int getIPAccessListNum() {
		return ipAccessListNum;
	}

	public void setIPAccessListNum(int ipAccessListNum) {
		this.ipAccessListNum = ipAccessListNum;
	}

	public int getAccessListModeNumber() {
		return accessListModeNumber;
	}

	public ACL(String ipAccessListType, int ipAccessListNum, int accessListModeNumber, String sourceIP,
			String wildCardSourceIP, String desIP, String wildCardDesIP) {
		this.desIP = desIP;
		this.sourceIP = sourceIP;
		this.ipAccessListNum = ipAccessListNum;
		this.accessListModeNumber = accessListModeNumber;
		this.ipAccessListType = ipAccessListType;
		this.wildCardDesIP = wildCardDesIP;
		this.wildCardSourceIP = wildCardSourceIP;

	}

	public String getWildCardDesIP() {
		return wildCardDesIP;
	}

	public void setWildCardDesIP(String wildCardDesIP) {
		this.wildCardDesIP = wildCardDesIP;
	}

	public String getWildCardSourceIP() {
		return wildCardSourceIP;
	}

	public void setWildCardSourceIP(String wildCardSourceIP) {
		this.wildCardSourceIP = wildCardSourceIP;
	}

	public void setAccessListModeNumber(int accessListModeNumber) {
		this.accessListModeNumber = accessListModeNumber;
	}

	public String getIPAccessListType() {
		return ipAccessListType;
	}

	public void setIPAccessListType(String ipAccessListType) {
		this.ipAccessListType = ipAccessListType;
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
		return ipAccessListNum;
	}

	public void setAccessNum(int ipAccessListNum) {
		this.ipAccessListNum = ipAccessListNum;
	}

	@Override
	public String toString() {
		return ("**" + ipAccessListType + " || \t" + ipAccessListNum + " || \t" + accessListModeNumber + " || \t"
				+ sourceIP + " || \t" + wildCardSourceIP + " || \t" + desIP + " || \t" + wildCardDesIP);
	}
}
