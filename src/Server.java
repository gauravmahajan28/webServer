
public interface Server {

	public void createServerSocket(int portNumber) throws Exception;
	
	public String getCurrentWorkingDirectory() throws Exception;
	
	public void createThreadPool(int numberOfThreads) throws Exception;
	
	public void acceptClientConnections(String rootDirectory) throws Exception;
	
}
