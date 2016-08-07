package com.training.deviceoperation.deviceconnection.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The PolicyMap class to get each policy map which defines a series of actions
 * (functions) that you want applied to a set of classified in bound traffic.
 * 
 * @author user
 */
public class PolicyMap {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_policymap;
	@Column
	private String policyName;
	@Column
	private String trafficClass;

	public PolicyMap() {
	}

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

	@Override
	public String toString() {
		return ("**" + policyName + " || \t" + trafficClass);
	}
}
