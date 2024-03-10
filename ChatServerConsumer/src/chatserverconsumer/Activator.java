package chatserverconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import chatserverproducer.ChatServerInterface;
public class Activator implements BundleActivator {

	// ServiceRefferncee
	ServiceReference serviceReference;

 
	public void start(BundleContext context) throws Exception {

		System.out.println(
				"................SERVER Consumer is started................");
		serviceReference = context
				.getServiceReference(ChatServerInterface.class.getName());

		ChatServerInterface servicePublish = (ChatServerInterface) context.getService(serviceReference);
	

	}

	public void stop(BundleContext context) throws Exception {

		System.out.println(
				"................SERVER Consumer is stopped................");
		// detach the service
		context.ungetService(serviceReference);

	}

}

