package com.training.deviceoperation.deviceconnection;

public class CLIConnectionFactory {
	public Connection getConnection (String connectionType)
	{
		if (connectionType == null)
			return null;
		 if(connectionType.equalsIgnoreCase("TelnetConnection")){
			 return new TelnetConnection("192.168.50.200", 23);
	      } else if(connectionType.equalsIgnoreCase("SSHConnection")){
	         return new SSHConnection("192.168.50.200", 22);}
	         
	     /* } else if(connectionType.equalsIgnoreCase("SQUARE")){
	         return new Square();
	      }*/
		return null;
	}
	}

