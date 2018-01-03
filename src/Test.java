import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(8081);
		System.out.println("Listening for connection on port 8080 ....");
		while (true)
		{
		      // spin forever
			final Socket client = serverSocket.accept();
			System.out.println("connection accepted !");
		/*	InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line = bufferedReader.readLine();
			while(!line.isEmpty())
			{
				 System.out.println(line);
	                line = bufferedReader.readLine();
			}*/
		    Date date = new Date();
		    String res = "HTTP/1.0 200 OK\n"
		            + "Server: HTTP server/0.1\n"
		            + "Date: "+new java.util.Date()+"\n"
		      + "Content-type: text/html; charset=UTF-8\n"
		            + "Content-Length: 38\n\n"
		            + "<html><body>OK</body></html>";

				client.getOutputStream().write(res.getBytes());
				
				
		 }
		
		// TODO Auto-generated method stub
		

	}

}
