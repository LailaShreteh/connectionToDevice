package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_ACL {
	private Direction direction;
	private ACL acl;
	private Interface interfaceObj;

	public Interface_ACL(Direction direction, ACL acl, Interface interfaceObj) {
		this.direction = direction;
		this.acl = acl;
		this.interfaceObj = interfaceObj;
	}

	public Direction getDir() {
		return direction;
	}

	public void setDir(Direction direction) {
		this.direction = direction;
	}

	public ACL getAcl() {
		return acl;
	}

	public void setAcl(ACL acl) {
		this.acl = acl;
	}

	public Interface getInterface() {
		return interfaceObj;
	}

	public void setInterface(Interface interfaceObj) {
		this.interfaceObj = interfaceObj;
	}

}
