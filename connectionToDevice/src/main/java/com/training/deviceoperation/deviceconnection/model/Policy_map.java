package com.training.deviceoperation.deviceconnection.model;

public class Policy_map {
	private String policy_name;
	private String traffic_class;
	private String bandwidth;
	private String priority;
	private String queue_limit;
	private String shape;
	private String police;
	private String service_policy;

	public String getTraffic_class() {
		return traffic_class;
	}

	public void setTraffic_class(String traffic_class) {
		this.traffic_class = traffic_class;
	}

	public String getQueue_limit() {
		return queue_limit;
	}

	public void setQueue_limit(String queue_limit) {
		this.queue_limit = queue_limit;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public String getService_policy() {
		return service_policy;
	}

	public void setService_policy(String service_policy) {
		this.service_policy = service_policy;
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
