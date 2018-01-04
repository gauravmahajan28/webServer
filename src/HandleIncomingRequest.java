import java.io.BufferedReader;
import java.io.File;
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
	
	/**
	 * constructor
	 * @param socket
	 */
	HandleIncomingRequest(Socket socket)
	{
		this.socket = socket;
	}
	
	/**
	 * return mime type based on file extension
	 */
	 public String getMimeType(File f) {
	        String file = f.toString();
	        String type = "";
	        if (file.endsWith(".txt")) {
	            type = "text/txt";
	        } else if (file.endsWith(".html") || file.endsWith("htm")) {
	            type = "text/html; Charset=UTF-8";
	        } else if (file.endsWith(".jpg")) {
	            type = "image/jpg";
	        } else if (file.endsWith(".png")) {
	            type = "image/png";
	        } else if (file.endsWith(".jpeg")) {
	            type = "image/jpeg";
	        } else if (file.endsWith(".gif")) {
	            type = "image/gif";
	        } else if (file.endsWith(".pdf")) {
	            type = "application/pdf";
	        } else if (file.endsWith(".mp3")) {
	            type = "audio/mpeg";
	        } else if (file.endsWith(".class")) {
	            type = "application/octet-stream";
	        } else if (file.endsWith(".mp4")) {
	            type = "video/mp4";
	        }
	        return type;
	    }

	

	public void run() 
	{

		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String actualFileName = getFileName(bufferedReader);
		
			System.out.println("filename = " + actualFileName);		
			File file = new File(actualFileName);
			BufferedReader br = new BufferedReader(new FileReader(file));

			Date date = new Date();
			String res = "HTTP/1.0 200 OK\n"
	            + "Server: HTTP server/0.1\n"
	            + "Date: "+new java.util.Date()+"\n"
	            /*  "Content-Disposition : attachment \n"*/
	            + "Content-type: " + getMimeType(file) + "; charset=UTF-8\n"+
	            "Content-Length: " +  file.length() +"\n\n";
			socket.getOutputStream().write(res.getBytes());
			
			byte[] data = Files.readAllBytes(file.toPath());
	       //     + "<html><body>OK</body></html>";

			socket.getOutputStream().write(data);
			socket.close();
		}
		catch(Exception e)
		{
			
		}
			
	}

	public String getFileName(BufferedReader bufferedReader) throws Exception {
		// TODO Auto-generated method stub
		String line = bufferedReader.readLine();
		
		StringTokenizer str = new StringTokenizer(line);
		str.nextToken();
		String fileName = str.nextToken();
	
		String actualFileName = fileName.substring(1, fileName.length());
		return actualFileName;
	}
}