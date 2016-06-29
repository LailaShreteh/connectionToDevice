package connectionToDevice;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.InputStream;
import org.apache.commons.net.telnet.TelnetClient;

public class Telnet implements INTERFACE {
	
	public boolean connect (String host, int port)
	{
		TelnetClient telnet = new TelnetClient();
		
		try {
    		telnet.connect(host, 23);
    		InputStream in = telnet.getInputStream();
    		PrintStream out = new PrintStream(telnet.getOutputStream());
    		return true;
		}catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	    

	}
	
}
