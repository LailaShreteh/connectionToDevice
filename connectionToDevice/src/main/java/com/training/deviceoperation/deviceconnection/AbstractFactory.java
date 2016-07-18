package com.training.deviceoperation.deviceconnection;

public abstract class AbstractFactory {
	   abstract CLIConnection getConnection(String connectionType);
	  // abstract SNMPConnection getSNMPConnection ()
}
