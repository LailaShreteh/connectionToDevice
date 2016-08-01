package com.training.deviceoperation.deviceconnection.model;

/**
 * 
 * @author user
 *
 */
public class Interface_ACL {
	private Direction direction;
	private int id_acl;
	private int id_interface;

	public Interface_ACL(Direction direction, int id_acl, int id_interface) {
		this.direction = direction;
		this.id_acl = id_acl;
		this.id_interface = id_interface;
	}

	public Direction getDir() {
		return direction;
	}

	public void setDir(Direction direction) {
		this.direction = direction;
	}

	public int getAcl() {
		return id_acl;
	}

	public void setAcl(int id_acl) {
		this.id_acl = id_acl;
	}

	public int getInterface() {
		return id_interface;
	}

	public void setInterface(int id_interface) {
		this.id_interface = id_interface;
	}

}
