public class Telnet implements INTERFACE {
	@Override
	boolean connect ()
	{
		ServerSocket s = new ServerSocket(2000);
		while(true)
		{
			Socket soc = s.accept();
			run(soc);
		}dsadf
	}
	
}
