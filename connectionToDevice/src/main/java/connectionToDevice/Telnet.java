package connectionToDevice;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Telnet implements INTERFACE {
	
	public boolean connect ()
	{
		Socket s = new Socket();
		PrintWriter out_s = null;
	    BufferedReader in_s = null;
	    return false;

	}
	
}
