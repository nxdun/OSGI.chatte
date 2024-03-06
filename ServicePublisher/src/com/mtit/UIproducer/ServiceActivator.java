package com.mtit.UIproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Publisher Start");
		ServicePublish publisherService = new ServicePublishImpl();
		
		//register the service
		publishServiceRegistration = context.registerService(
				ServicePublish.class.getName(), publisherService, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Publisher Stop");
		publishServiceRegistration.unregister();
		
	}

}