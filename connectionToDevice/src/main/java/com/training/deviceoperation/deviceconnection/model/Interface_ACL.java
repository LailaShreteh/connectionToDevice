package com.training.deviceoperation.deviceconnection.model;

public class Interface_ACL {
	private Direction dir;
	private ACL acl;
	private Interface Interface;

	public Interface_ACL(Direction dir, ACL acl, Interface interface1) {
		this.dir = dir;
		this.acl = acl;
		Interface = interface1;
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public ACL getAcl() {
		return acl;
	}

	public void setAcl(ACL acl) {
		this.acl = acl;
	}

	public Interface getInterface() {
		return Interface;
	}

	public void setInterface(Interface interface1) {
		Interface = interface1;
	}

}
