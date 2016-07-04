package com.training.deviceoperation.deviceconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.sshd.ClientSession;
import org.apache.sshd.SshClient;

/**
 * 
 * @author Reem Jazi
 * @author Laila Shreteh
 *
 */
public class SSHConnection extends CLIConnection {
	
	/**
	 * @param host-host address to connect
	 * @param port-port number
	 * @throws Exception
	 */
	@Override
	public String connectToDevice(String host, int port) {
	
		String login = System.getProperty("laila");
		SshClient client = SshClient.setUpDefaultClient();
		client.start();

		try {
			boolean hasKeys = false;
			ClientSession session;
			try {
				session = client.connect(host, port).await().getSession();
				int ret = ClientSession.WAIT_AUTH;

				while ((ret & ClientSession.WAIT_AUTH) != 0) {
					if (hasKeys) {
						session.authAgent(login);
						ret = session.waitFor(ClientSession.WAIT_AUTH | ClientSession.CLOSED | ClientSession.AUTHED, 0);
					} else {    // here just connect authentication without exchanging
								// any information ;)
						System.out.print("Password:");
						BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
						String password = r.readLine();
						System.out.println("true !!! pass ");
						session.authPassword(login, password);
						ret = session.waitFor(ClientSession.WAIT_AUTH | ClientSession.CLOSED | ClientSession.AUTHED, 0);
						return "Sucess";
					}

				}
				if ((ret & ClientSession.CLOSED) != 0) {
					System.err.println("error");
					System.exit(-1);
					return "fails to Connect x_x";
				}
				session.close(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(":P :P" + e.getMessage());//here
			}
			
		} finally { // The finally block is executed always after the try-catch
					// block, if an exception is thrown or not.
			client.stop();
		}
		return"fail";
	}
}
