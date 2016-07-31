package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_Policy {
	private Direction direction;
	private PolicyMap policy;
	private Interface interfaceObj;

	public Interface_Policy(Direction direction, PolicyMap policy, Interface interfaceObj) {
		this.direction = direction;
		this.policy = policy;
		this.interfaceObj = interfaceObj;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public PolicyMap getPolicy() {
		return policy;
	}

	public void setPolicy(PolicyMap policy) {
		this.policy = policy;
	}

	public Interface getInterfaceObj() {
		return interfaceObj;
	}

	public void setInterfaceObj(Interface interfaceObj) {
		this.interfaceObj = interfaceObj;
	}

}
