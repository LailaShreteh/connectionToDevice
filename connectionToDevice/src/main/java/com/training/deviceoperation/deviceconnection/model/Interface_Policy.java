package com.training.deviceoperation.deviceconnection.model;

public class Interface_Policy {
	private Direction dir;
	private PolicyMap policy;
	private Interface Interface;

	public Interface_Policy(Direction dir, PolicyMap policy, Interface interface1) {
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

	public PolicyMap getPolicy() {
		return policy;
	}

	public void setPolicy(PolicyMap policy) {
		this.policy = policy;
	}

	public Interface getInterface() {
		return Interface;
	}

	public void setInterface(Interface interface1) {
		Interface = interface1;
	}

}
