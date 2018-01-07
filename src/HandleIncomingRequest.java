import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.StringTokenizer;

public class HandleIncomingRequest extends Thread implements IncomingRequestHandler
{
	private Socket socket;
	private String rootDirectory;
	
	/**
	 * constructor
	 * @param socket
	 */
	HandleIncomingRequest(Socket socket, String rootDirectory)
	{
		this.socket = socket;
		this.rootDirectory = rootDirectory;
	}
	
	/**
	 * thread run method
	 */
	public void run()
	{

		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String actualFileName = getFileName(bufferedReader);
		
			System.out.println("filename = " + actualFileName);	
			System.out.println("looking for filename = " + rootDirectory + actualFileName );
			File file = new File(rootDirectory + actualFileName);
			BufferedReader br = new BufferedReader(new FileReader(file));

			Date date = new Date();
			String res = "HTTP/1.0 200 OK\n"
	            + "Server: HTTP server/0.1\n"
	            + "Date: "+new java.util.Date()+"\n"
	            /*  "Content-Disposition : attachment \n"*/
	          //  + "Content-type: " + getMimeType(file) + "; charset=UTF-8\n"+
	           + "Content-Length: " +  file.length() +"\n\n";
			socket.getOutputStream().write(res.getBytes());
			FileInputStream fileInputStream = new FileInputStream(file);
			final int BUFFER_SIZE = 1024;
			byte[] buffer = new byte[BUFFER_SIZE];
			int read = 0;
			while((read = fileInputStream.read(buffer)) > 0)
			{
				socket.getOutputStream().write(buffer);
			}
			//byte[] data = Files.readAllBytes(file.toPath());
			
	       //     + "<html><body>OK</body></html>";

		//	socket.getOutputStream().write(data);
			socket.close();
		}
		catch(FileNotFoundException e)
		{
			String res = "HTTP/1.0 404 NOT FOUND\n"
		            + "Server: HTTP server/0.1\n"
		            + "Date: "+new java.util.Date()+"\n";
		            /*  "Content-Disposition : attachment \n"*/
		          //  + "Content-type: " + getMimeType(file) + "; charset=UTF-8\n"+
		    try
		    {
		    	socket.getOutputStream().write(res.getBytes());
		    	socket.close();
		    }
		    catch(Exception e2)
		    {
		    	
		    }
			
		}
		catch(Exception e)
		{
			
		}
		
			
	}

	/**
	 * return url file name  e.g. given "GET /index.html http/1.1" , this method returns /index.html
	 */
	public String getFileName(BufferedReader bufferedReader) throws Exception {
		// TODO Auto-generated method stub
		
		String line = bufferedReader.readLine();
		
		StringTokenizer str = new StringTokenizer(line);
		str.nextToken();
		String fileName = str.nextToken();
	    return fileName;
	//	String actualFileName = fileName.substring(1, fileName.length());
		//return actualFileName;
	}
}