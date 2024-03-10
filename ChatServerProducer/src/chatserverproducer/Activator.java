package chatserverproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	private ChatServer chatServer;
	ServiceRegistration CSProducerRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
    	try {
    	chatServer = new ChatServer(9002); // Initialize with port 9002
    	ChatServerInterface ChatPublisher = chatServer;// Initialize with port 9002
    	CSProducerRegistration = context.registerService(ChatServerInterface.class.getName(), ChatPublisher, null);
    	
        System.out.println("Chat Server bundle started.");
        ChatPublisher.startServerInThread(); // Start the server in a separate thread
	} catch (Exception e) {
		System.out.println(e);
	}
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Chat Server bundle stopped.");
        CSProducerRegistration.unregister();
        
    }
}
