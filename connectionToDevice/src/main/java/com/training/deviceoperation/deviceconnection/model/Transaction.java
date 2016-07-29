package com.training.deviceoperation.deviceconnection.model;

public class Transaction {
	private Action command; // i think it's not private :/
	private Policy_map policy_map;
	private Class_map class_map;

	public Transaction(Action command, Policy_map policy_map, Class_map class_map) {
		super();
		this.command = command;
		this.policy_map = policy_map;
		this.class_map = class_map;
	}

	public Action getCommand() {
		return command;
	}

	public void setCommand(Action command) {
		this.command = command;
	}

	public Policy_map getPolicy_map() {
		return policy_map;
	}

	public void setPolicy_map(Policy_map policy_map) {
		this.policy_map = policy_map;
	}

	public Class_map getClass_map() {
		return class_map;
	}

	public void setClass_map(Class_map class_map) {
		this.class_map = class_map;
	}

}
