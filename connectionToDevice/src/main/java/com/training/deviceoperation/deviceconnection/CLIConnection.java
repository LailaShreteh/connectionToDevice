package com.training.deviceoperation.deviceconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;

import org.apache.commons.net.telnet.TelnetClient;

import com.training.deviceoperation.deviceconnection.model.Interface;

/**
 * 
 * @author user
 *
 */

public abstract class CLIConnection implements Connection {

//	private String host="";
//	private int port;
	private java.io.InputStream in;
	private  Writer writer;
	private OutputStream out ;
	abstract public String connectToDevice(String host, int port) ;

	public List<String> getInterfaces(Object o ) throws IOException {
		//TODO push command show interfaces to device
		 if(o instanceof TelnetClient){
			 TelnetClient telnet = (TelnetClient)o;  
		 }
		 if(o instanceof TelnetClient){
			 TelnetClient telnet = (TelnetClient)o;  
		 }
		List<String> interfaces = new ArrayList<String>();
		
		out= o.getOutputStream();
		  writer = new OutputStreamWriter(out,"UTF-8");
		// writer = new BufferedWriter(writer);
		  in = o.getInputStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in,"UTF-8"));
		
		readUntil("Password: ");
		write("lab");
		//readUntil("   iyfhdkl");
		
		Scanner scan = new Scanner(System.in) ;
		String cmd = scan.nextLine();
		writer.write(cmd);
		writer.flush();// to be sure that our command is send to the server !!

		//writer.write("sh ip int brief");
		//TODO get output and convert it to list
		
		/*String s = null;
		List<String> interfaces = new ArrayList<String>();
		while ((s = stdInput.readLine()) != null) {
			interfaces.add(s.substring(0, s.indexOf("	")));
				}
		System.out.println(interfaces);*/
		//TODO return list<interface>
		// we don't know how the interfaces class it's look like !! :\
		// so we return a list of interfaces as string
		return interfaces;
	}
	private String readUntil(String sample) {
		// TODO Auto-generated method stub
		try {
    		char lastChar = sample.charAt(sample.length() - 1);
    		StringBuffer sb = new StringBuffer();
    		boolean found = false;
    		char ch = (char) in.read();
    		while (true) {
    			System.out.print(ch);
    			sb.append(ch);
    			if (ch == lastChar) {
    				if (sb.toString().endsWith(sample)) {
    					return sb.toString();
    				}
    			}
    			ch = (char) in.read();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
	public void write(String value) {
    	try {
			writer.write(value);
			writer.flush();// to be sure that our command is send to the server !!
    		System.out.println(value);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	
}
