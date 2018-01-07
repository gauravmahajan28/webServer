import java.io.BufferedReader;
import java.io.File;


public interface IncomingRequestHandler 
{
	 public String getFileName(BufferedReader bufferedReader) throws Exception;
	 
}
