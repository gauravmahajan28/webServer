import java.io.BufferedReader;
import java.io.File;


public interface IncomingRequestHandler 
{
	 public String getMimeType(File f);
	 public String getFileName(BufferedReader bufferedReader) throws Exception;
	 
}
