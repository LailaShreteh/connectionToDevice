package com.training.deviceoperation.deviceconnection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The ACL class represents each Access Control List and its criteria which can
 * specified to control which packets move through a network and to where.
 * 
 * @author user
 */
@Entity
public class ACL {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_ACL;
	@Column
	private String ipAccessListType;
	@Column
	private int ipAccessListNum;
	@Column
	private int accessListModeNumber;
	@Column
	private String sourceIP;
	@Column
	private String wildCardSourceIP;
	@Column
	private String desIP;
	@Column
	private String wildCardDesIP;

	public ACL() {
	}

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
