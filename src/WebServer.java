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
			//System.out.println(args[0]);   // args[0] is root directory
			ServerImplementation serverImplementation = new ServerImplementation();
			serverImplementation.createServerSocket(8086); // port number
			serverImplementation.createThreadPool(5); // number of threads in thread pool
			serverImplementation.acceptClientConnections(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("error");  // not found error handled inside
		    // add code to handle server crashing	
		}
	}
}


