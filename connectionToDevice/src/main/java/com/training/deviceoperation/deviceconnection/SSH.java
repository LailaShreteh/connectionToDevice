package com.training.deviceoperation.deviceconnection;

import java.io.File;
import java.net.InetAddress;
import java.io.*;
import java.util.*;

public class SSH implements Connection {
	
	public String connectClass (String host, int port)
	{
		String hostname = "ssh.myhost.com";
		String username = "reem";
		 String password="";
		
		// create new SshParameters instance
		SshParameters params = new SshParameters(hostname, port, username, password);
		 
		// create new SshHostKeys instance
		SshHostKeys keys = new SshHostKeys();
		 
		// specify valid remote server address
		InetAddress address = InetAddress.getByName(hostname);
		 
		// add valid fingerprint to SshHostKeys instance
		keys.addKey(address, "18:bc:ec:a5:0f:9a:fc:1a:60:96:7a:17:c8:ed:73:ac");
		 
		// update SshParameters instance to validate against fingerprint in SshHostKeys instance
		params.setHostKeys(keys, false);
		 
		// create new Ssh instance
		SsH ssh = new SsH(params);
		ssh.connect();
		
		// gets updated host keys (if updated)
		keys = ssh.getHostKeys();
	 return "";
	}
}
