package com.training.deviceoperation.deviceconnection.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.training.deviceoperation.parser.DuplexMode;
import com.training.deviceoperation.parser.Status;

/**
 * The EthernetProtocolEndpoint class represents each interface and its data.
 * 
 * @author user
 */

@Entity
public class EthernetProtocolEndpoint {

	@Id
	@GeneratedValue
	@Column(name = "id_interface")
	// (strategy = GenerationType.AUTO)
	private int id_interface;
	/** List of attributes which represent each interface. */
	@Column(name = "name")
	private String name;
	@Column(name = "adminStatu")
	private Status adminStatus;
	@Column(name = "operationalStatu")
	private Status operStatus;
	@Column(name = "MTU")
	private int mtu;
	@Column(name = "speed")
	private String ifSpeed;
	@Column(name = "duplex")
	private DuplexMode duplexMode;
	@Column(name = "macAddress")
	private String macAddress;

	public EthernetProtocolEndpoint() {
	}

	public EthernetProtocolEndpoint(String name, Status adminStatus, Status operStatus, int mtu, String ifSpeed,
			DuplexMode duplexMode, String macAddress) {

		this.name = name;
		this.mtu = mtu;
		this.adminStatus = adminStatus;
		this.operStatus = operStatus;
		this.duplexMode = duplexMode;
		this.ifSpeed = ifSpeed;
		this.macAddress = macAddress;
	}

	/**
	 * @return the duplexMode
	 */
	public DuplexMode getDuplexMode() {
		return duplexMode;
	}

	/**
	 * @return the adminStatus
	 */
	public Status getAdminStatus() {
		return adminStatus;
	}

	/**
	 * @return the ifSpeed
	 */
	public String getIfSpeed() {
		return ifSpeed;
	}

	/**
	 * @return the macAddress
	 */
	public String getMacAddress() {
		return macAddress;
	}

	/**
	 * @return the mtu
	 */
	public int getMtu() {
		return mtu;
	}

	/**
	 * @return the operStatus
	 */
	public Status getOperStatus() {
		return operStatus;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setDuplexMode(DuplexMode duplexMode) {
		this.duplexMode = duplexMode;
	}

	public void setAdminStatus(Status adminStatus) {
		this.adminStatus = adminStatus;
	}

	public void setIfSpeed(String ifSpeed) {
		this.ifSpeed = ifSpeed;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public void setMtu(int mtu) {
		this.mtu = mtu;
	}

	public void setOperStatus(Status operStatus) {
		this.operStatus = operStatus;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * toString method to print each interface and its data as object.
	 * 
	 * @return - each interface and its data.
	 */
	@Override
	public String toString() {
		return ("**" + name + " || \t" + adminStatus + " || \t" + operStatus + " || \t" + mtu + " || \t" + duplexMode
				+ " || \t" + ifSpeed + " || \t" + macAddress);
	}

	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; EthernetProtocolEndpoint other = (EthernetProtocolEndpoint)
	 * obj; if (adminStatus != other.adminStatus) return false; if (comments ==
	 * null) { if (other.comments != null) return false; } else if
	 * (!comments.equals(other.comments)) return false; if (description == null)
	 * { if (other.description != null) return false; } else if
	 * (!description.equals(other.description)) return false; if (duplexMode !=
	 * other.duplexMode) return false; if (entAliasMappingIdentifier == null) {
	 * if (other.entAliasMappingIdentifier != null) return false; } else if
	 * (!entAliasMappingIdentifier.equals(other.entAliasMappingIdentifier))
	 * return false; if (ethernetLoopback != other.ethernetLoopback) return
	 * false; if (ifSpeed == null) { if (other.ifSpeed != null) return false; }
	 * else if (!ifSpeed.equals(other.ifSpeed)) return false; if (lagEndName ==
	 * null) { if (other.lagEndName != null) return false; } else if
	 * (!lagEndName.equals(other.lagEndName)) return false; if (macAddress ==
	 * null) { if (other.macAddress != null) return false; } else if
	 * (!macAddress.equals(other.macAddress)) return false; if (maxOccurs !=
	 * other.maxOccurs) return false; if (minOccurs != other.minOccurs) return
	 * false; if (mtu != other.mtu) return false; if (name == null) { if
	 * (other.name != null) return false; } else if (!name.equals(other.name))
	 * return false; if (operStatus != other.operStatus) return false; return
	 * true; }
	 */
}
