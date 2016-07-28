package com.training.deviceoperation.deviceconnection.model;

public class Policy_map {
	private String policy_name;
	private String trafic_class;
	private String bandwidth;
	private String priority;
	
	public String getPolicy_name() {
		return policy_name;
	}
	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}
	public String getTrafic_class() {
		return trafic_class;
	}
	public void setTrafic_class(String trafic_class) {
		this.trafic_class = trafic_class;
	}
	public String getBandwidth() {
		return bandwidth;
	}
	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}

