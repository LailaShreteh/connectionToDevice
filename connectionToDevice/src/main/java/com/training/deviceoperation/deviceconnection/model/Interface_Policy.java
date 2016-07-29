package com.training.deviceoperation.deviceconnection.model;

public class Interface_Policy {
	private Direction dir;
	private Policy_map policy;
	private Interface Interface;

	public Interface_Policy(Direction dir, Policy_map policy, Interface interface1) {
		super();
		this.dir = dir;
		this.policy = policy;
		Interface = interface1;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public Policy_map getPolicy() {
		return policy;
	}

	public void setPolicy(Policy_map policy) {
		this.policy = policy;
	}

	public Interface getInterface() {
		return Interface;
	}

	public void setInterface(Interface interface1) {
		Interface = interface1;
	}

}
