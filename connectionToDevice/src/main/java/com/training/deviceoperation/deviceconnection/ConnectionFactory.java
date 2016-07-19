package com.training.deviceoperation.deviceconnection;

public class ConnectionFactory {
	public static Connection createConnection(String connectionType) {
		if (connectionType == null)
			return null;
		if (connectionType.equalsIgnoreCase("TELNET")) {
			return new TelnetConnection();
		} else if (connectionType.equalsIgnoreCase("SSH")) {
			return new SSHConnection();
		} else if (connectionType.equalsIgnoreCase("SNMP")) {
			return new SNMPConnection();
		}
		return null;
	}
}
