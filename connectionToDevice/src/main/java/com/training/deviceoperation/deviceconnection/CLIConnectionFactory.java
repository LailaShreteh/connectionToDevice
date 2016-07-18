package com.training.deviceoperation.deviceconnection;

public class CLIConnectionFactory extends AbstractFactory{
	public Connection getConnection (String connectionType)
	{
		if (connectionType == null)
			return null;
		 if(connectionType.equalsIgnoreCase("CLIConnection"))
			 return new CLIConnection();
	      /*} else if(connectionType.equalsIgnoreCase("RECTANGLE")){
	         return new Rectangle();
	         
	      } else if(connectionType.equalsIgnoreCase("SQUARE")){
	         return new Square();
	      }*/
	}
	}

