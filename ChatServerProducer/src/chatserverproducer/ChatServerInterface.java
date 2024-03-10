package chatserverproducer;

public interface ChatServerInterface {
	public void startServer();
	public void startServerInThread();
	public void stopServer();
	public void stopServerThread();
	public void setPort(int port);
	public int getPort();
}
