package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_ACL {
	private Direction direction;
	private String aclName;
	private String interfaceName;

	public Interface_ACL(Direction direction, String aclName, String interfaceName) {
		this.direction = direction;
		this.aclName = aclName;
		this.interfaceName = interfaceName;
	}

	public Direction getDir() {
		return direction;
	}

	public void setDir(Direction direction) {
		this.direction = direction;
	}

	public String getAclName() {
		return aclName;
	}

	public void setAcl(String aclName) {
		this.aclName = aclName;
	}

	public String getInterface() {
		return interfaceName;
	}

	public void setInterface(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	@Override
	public String toString() {
		return ("**" + direction + " || \t" + aclName + " || \t" + interfaceName);
	}

}
