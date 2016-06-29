package connectionToDevice;

import java.net.InetAddress;

public class SNMP implements INTERFACE {
	SNMP_par par;
	public boolean connect(String host, int port) {
		try
		 {
		 InetAddress hostAddress = InetAddress.getByName(par.strIPAddress);
		 SNMPv1CommunicationInterface comInterface = New
		 SNMPv1CommunicationInterface(iSNMPVersion,
		 hostAddress, community);

		 SNMPVarBindList newVars = new SNMPVarBindList();
		 System.out.println("Setting value corresponding to OID " + strOID);
		 newVars = comInterface.setMIBEntry(strOID, new SNMPInteger(Value)); 
		 String valueString = new String();
		 for (int i = 0; i < newVars.size(); ++i)
		 {
		 valueString += newVars.toString()+ "\n";
		 }
		 System.out.println("valueString: " + valueString);
		 }
		 catch(Exception e)
		 {
		 System.out.println("Exception during SNMP Set operation: " + e + "\n");
		 } 
		return false;
	}



	

	

}
