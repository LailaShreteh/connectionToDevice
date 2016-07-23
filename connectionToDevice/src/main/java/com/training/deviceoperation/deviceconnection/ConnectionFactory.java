package com.training.deviceoperation.deviceconnection;

/**
 * 
 * @author user
 *
 */

public class ConnectionFactory {
	/**
	 * createConnection method to create a Telent, SSH or SNMP connection
	 * object.
	 * 
	 * @param connectionType
	 *            - to specify the type of the connection, either TELNET or SSH
	 *            or SNMP type connection.
	 * @return an object connection from the specified connection type.
	 * 
	 *
	 */
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
