package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_Policy {
	private Direction direction;
	private int policyID;
	private int interfaceID;

	public Interface_Policy(Direction direction, int policyID, int interfaceID) {
		this.direction = direction;
		this.policyID = policyID;
		this.interfaceID = interfaceID;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getPolicy() {
		return policyID;
	}

	public void setPolicy(int policy) {
		this.policyID = policy;
	}

	public int getInterfaceID() {
		return interfaceID;
	}

	public void setInterfaceID(int interfaceID) {
		this.interfaceID = interfaceID;
	}

}
