package com.training.deviceoperation.deviceconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.training.deviceoperation.deviceconnection.model.Interface;

/**
 * 
 * @author user
 *
 */

public abstract class CLIConnection implements Connection {
	
	private String host="";
	private int port;
	
	abstract public String connectToDevice(String host, int port) ;

	public List<String> getInterfaces() throws IOException {
		//TODO push command show interfaces to device
		Process proc = Runtime.getRuntime().exec("sh ip int brief");
		
		BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(proc.getErrorStream()));

		//TODO get output and convert it to list
		
		String s = null;
		List<String> interfaces = new ArrayList<String>();
		while ((s = stdInput.readLine()) != null) {
			interfaces.add(s.substring(0, s.indexOf("	")));
				}
		//TODO return list<interface>
		// we don't know how the interfaces class it's look like !! :\
		// so we return a list of interfaces as string
		return interfaces;
	}
	
}
