package com.training.deviceoperation.deviceconnection.model;

public class Transaction {
	private Action command; // i think it's not private :/
	private PolicyMap policy_map;
	private ClassMap class_map;

	public Transaction(Action command, PolicyMap policy_map, ClassMap class_map) {
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

	public PolicyMap getPolicy_map() {
		return policy_map;
	}

	public void setPolicy_map(PolicyMap policy_map) {
		this.policy_map = policy_map;
	}

	public ClassMap getClass_map() {
		return class_map;
	}

	public void setClass_map(ClassMap class_map) {
		this.class_map = class_map;
	}

}
