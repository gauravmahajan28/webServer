import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerImplementation implements Server {
	
	ServerSocket serverSocket;
	ExecutorService pool;

	/**
	 * create server socket given port number
	 */
	public void createServerSocket(int portNumber)  throws Exception
	{
		// TODO Auto-generated method stub
		this.serverSocket = new ServerSocket(portNumber);
		
	}

	/**
	 * return current working directory
	 */
	public String getCurrentWorkingDirectory() throws Exception {
		// TODO Auto-generated method stub
		return System.getProperty("user.dir");
	}

	/**
	 * create thread pool based on number of threads
	 */
	public void createThreadPool(int numberOfThreads) throws Exception {
		// TODO Auto-generated method stub
		pool =  Executors.newFixedThreadPool(numberOfThreads);
	}

	/**
	 * accept client connections
	 */
	public void acceptClientConnections() throws Exception {
		// TODO Auto-generated method stub
		while (true)
		{
		      // spin forever
			final Socket client = serverSocket.accept();
			Thread thread = new HandleIncomingRequest(client);
			pool.execute(thread);
		//	thread.start();
		 }
		
	}
	
	
}
