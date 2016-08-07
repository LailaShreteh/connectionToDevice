package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_Policy {
	private Direction direction;
	private String policyName;
	private String interfaceName;

	public Interface_Policy() {
	}

	public Interface_Policy(Direction direction, String policyName, String interfaceName) {
		this.direction = direction;
		this.policyName = policyName;
		this.interfaceName = interfaceName;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policy) {
		this.policyName = policy;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceID(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	@Override
	public String toString() {
		return ("**" + direction + " || \t" + policyName + " || \t" + interfaceName);
	}

}
