package com.training.deviceoperation.deviceconnection.model;

public class ACL {
	protected String desIP;
	protected String sourceIP;
	protected int accessNum;

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
		return accessNum;
	}

	public void setAccessNum(int accessNum) {
		this.accessNum = accessNum;
	}
}
