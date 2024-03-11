package chatserverproducer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer implements ChatServerInterface {

	private int port;
	private ServerSocket serverSocket;
	private HashSet<String> names = new HashSet<>();
	private HashSet<PrintWriter> writers = new HashSet<>();
	private Thread serverThread;

	public ChatServer(int port) {
		this.port = port;
	}

	// port setter and getter
	public int getPort() {
		return port;
	}
	
	//stop server thread and start server in new thread | new port
	public void setPort(int port) {
		stopServerThread();
		this.port = port;
		startServerInThread();
	}
	//runs server in main thread
	//for testing purposes
	//WARNING: this method is not thread safe
	public void startServer() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("CChat Server started on port " + port);
			while (true) {
				new Handler(serverSocket.accept()).start();
			}
		} catch (IOException e) {
			System.out.println("azazaz2 " + e);
		} finally {
			stopServer();
		}
	}
	
	//runs server in new thread
	//this is currently used 
	public void startServerInThread() {
		Thread serverThread = new Thread(() -> {
			startServer();
		});
		serverThread.start();
	}

	public void stopServerThread() {
		// stop server thread
		try {
			serverSocket.close();
			serverThread.interrupt();
			System.out.println("ChatServer producer : Chat Server stopped");
		} catch (IOException e) {
			System.out.println("ChatServer producer : Exception occured " + e);
		}

	}

	public void stopServer() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
				System.out.println("Chat Server stopped");
			}
		} catch (IOException e) {
			System.out.println("ChatServer producer : Exception occured  " + e);
		}
	}

	private class Handler extends Thread {
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private String name;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {

				// Create character streams for the socket.
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				//initial message sent to client
				out.println("NAMEACCEPTED");
				name = in.readLine();
				
				names.add(name);
				
				writers.add(out);
				broadcastLoggedInClients();

				for (PrintWriter writer : writers) {
					writer.println("MESSAGE ...Hi !! " + name
							+ " welcome to our chat server...");
				}
				
				//private messaging
				while (true) {

					String input = in.readLine();

					if (input.contains(">>")) {
						String[] parts = input.split(">>");
						String receiver = parts[0].trim();
						String message = parts[1].trim();

						for (String n : names) {
							if (n.equals(receiver)) {

								for (PrintWriter writer : writers) {

									if (writer.equals(out)
											|| n.contains(receiver)) {
										writer.println("PRIVATEMESSAGE "
												+ receiver + " " + name + " !!"
												+ message);
									}
								}
								break;
							}
						}
					} else {
						for (PrintWriter writer : writers) {
							writer.println(
									"MESSAGE " + name + ":" + "" + input);
						}

					}

				}
			} catch (IOException e) {
				System.out.println(e);
			} finally {

			}

			if (out != null) {
				writers.remove(out);
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
			broadcastLoggedInClients();
		}
	}

	private void broadcastLoggedInClients() {
		StringBuilder clientListMessage = new StringBuilder();
		clientListMessage.append("USERLIST");
		for (String client : names) {
			clientListMessage.append(client).append(",");
		}
		for (PrintWriter writer : writers) {
			writer.println(clientListMessage);
		}
	}
}
