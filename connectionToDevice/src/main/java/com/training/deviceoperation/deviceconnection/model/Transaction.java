package com.training.deviceoperation.deviceconnection.model;

public class Transaction {
	private Action command; // i think it's not private :/
	private String policy_map;
	private String class_map;

	public Transaction(Action command, String policyName, String trafficClass) {
		this.command = command;
		this.policy_map = policyName;
		this.class_map = trafficClass;
	}

	public Action getCommand() {
		return command;
	}

	public void setCommand(Action command) {
		this.command = command;
	}

	public String getPolicy_map() {
		return policy_map;
	}

	public void setPolicy_map(String policy_map) {
		this.policy_map = policy_map;
	}

	public String getClass_map() {
		return class_map;
	}

	public void setClass_map(String class_map) {
		this.class_map = class_map;
	}

}
