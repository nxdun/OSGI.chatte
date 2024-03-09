package chatserverproducer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {

    private final int port;
    private ServerSocket serverSocket;
    private HashSet<String> names = new HashSet<>();
    private HashSet<PrintWriter> writers = new HashSet<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Chat Server started on port " + port);
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }
    public void startServerInThread() {
        Thread serverThread = new Thread(() -> {
            startServer();
        });
        serverThread.start();
    }

    public void stopServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
                System.out.println("Chat Server stopped");
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                writers.add(out);
                broadcastLoggedInClients();

                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    // Process input and broadcast messages...
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                broadcastLoggedInClients();
            }
        }

        private void broadcastLoggedInClients() {
            StringBuilder clientListMessage = new StringBuilder();
            clientListMessage.append("CLIENTLIST ");
            for (String client : names) {
                clientListMessage.append(client).append(",");
            }
            for (PrintWriter writer : writers) {
                writer.println(clientListMessage);
            }
        }
    }
}
