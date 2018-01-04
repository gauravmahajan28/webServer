import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class WebServer
{
	public static void main(String args[])
	{
		try
		{
			ServerImplementation serverImplementation = new ServerImplementation();
			serverImplementation.createServerSocket(8086);
			serverImplementation.createThreadPool(5);
			serverImplementation.acceptClientConnections();
		}
		catch(Exception e)
		{
		    // add code to handle server crashing	
		}
	}
}


