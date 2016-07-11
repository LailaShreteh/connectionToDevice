package com.training.deviceoperation.deviceconnection;

import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public class SSHConnection extends CLIConnection {
	public JSch jsch;

	/**
	 * @param host-host address to connect
	 * @param port-port number
	 * @throws Exception
	 */
	@Override
	public String connectToDevice(String host, int port) {
	
		
		 jsch = new JSch();
			//Session session=null;
//			try {
////				  session = jsch.getSession("lab", "192.168.50.200",22);
////				     //jsch.addIdentity(defaultPrivateKeyFile);
////				    Properties config = new Properties();
////				    config.put("StrictHostKeyChecking", "no");
////				    session.setConfig(config);
////				    session.connect(1000);
//				   // Channel channel = session.openChannel("shell");
//				    //channel.connect(1000);
//
//			} catch (JSchException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		return"";
	}
}
