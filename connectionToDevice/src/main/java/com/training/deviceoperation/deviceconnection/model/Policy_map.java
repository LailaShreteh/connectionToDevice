package com.training.deviceoperation.deviceconnection.model;

public class Policy_map {
	private String policy_name;
	private String traffic_class;

	public Policy_map(String policy_name, String traffic_class) {
		this.policy_name = policy_name;
		this.traffic_class = traffic_class;
	}

	public String getTraffic_class() {
		return traffic_class;
	}

	public void setTraffic_class(String traffic_class) {
		this.traffic_class = traffic_class;
	}

	public String getPolicy_name() {
		return policy_name;
	}

	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}

	public String getTrafic_class() {
		return traffic_class;
	}

	public void setTrafic_class(String traffic_class) {
		this.traffic_class = traffic_class;
	}

}
