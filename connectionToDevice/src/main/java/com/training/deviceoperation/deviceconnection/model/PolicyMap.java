package com.training.deviceoperation.deviceconnection.model;

/**
 * The PolicyMap class to get each policy map which defines a series of actions
 * (functions) that you want applied to a set of classified in bound traffic.
 * 
 * @author user
 */
public class PolicyMap {
	private String policyName;
	private String trafficClass;

	public PolicyMap(String policyName, String trafficClass) {
		this.policyName = policyName;
		this.trafficClass = trafficClass;
	}

	public String getTrafficClass() {
		return trafficClass;
	}

	public void setTrafficClass(String trafficClass) {
		this.trafficClass = trafficClass;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getTraficClass() {
		return trafficClass;
	}

	public void setTraficClass(String trafficClass) {
		this.trafficClass = trafficClass;
	}

}
