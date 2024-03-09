package chatserverproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private ChatServer chatServer;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Chat Server bundle started.");
        chatServer = new ChatServer(9002); // Initialize with port 9002
        chatServer.startServerInThread(); // Start the server in a separate thread
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Chat Server bundle stopped.");
        if (chatServer != null) {
            chatServer.stopServer(); // No arguments passed
        }
    }
}
