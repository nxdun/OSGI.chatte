package chatserverproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {
	//private ChatServer chatServer;
	ServiceRegistration CSProducerRegistration;
	private ChatServer chatServer;
    @Override
    public void start(BundleContext context) throws Exception {
    	try {
    		//runs a single instance of chat server constructor
    		 chatServer = new ChatServer(9002);
    		//runs in thread so that it does not block the main thread
        	 chatServer.startServerInThread();
        	 //register the chat server as a service
        	 ChatServerInterface ChatPublisher = chatServer; ;
        	 CSProducerRegistration = context.registerService(ChatServerInterface.class.getName(), ChatPublisher, null);
        	 System.out.println("..................Chat Server bundle started..................");
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
