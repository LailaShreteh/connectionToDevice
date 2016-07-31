package com.training.deviceoperation.deviceconnection.model;

/**
 * The PolicyMap class to get each policy map which defines a series of actions
 * (functions) that you want applied to a set of classified in bound traffic.
 * 
 */
public class PolicyMap {
	private String policyName;
	private String trafficClass;

	public PolicyMap(String policyName, String trafficClass) {
		this.policyName = policyName;
		this.trafficClass = trafficClass;
	}

	public String getTraffic_class() {
		return trafficClass;
	}

	public void setTraffic_class(String trafficClass) {
		this.trafficClass = trafficClass;
	}

	public String getPolicy_name() {
		return policyName;
	}

	public void setPolicy_name(String policyName) {
		this.policyName = policyName;
	}

	public String getTrafic_class() {
		return trafficClass;
	}

	public void setTrafic_class(String trafficClass) {
		this.trafficClass = trafficClass;
	}

}
