package com.mtit.UIproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {
	
	//initialize the service registration
	ServiceRegistration UIProducerRegistration;
	
	//start the lifecycle of producer service
	public void start(BundleContext context) throws Exception {
		
		System.out.println("................UI producer is started................");
        //initialize the interface | implementation class
		ServicePublish publisherService = new ServicePublishImpl();
		//registers service with the context(start)
		UIProducerRegistration = context.registerService(ServicePublish.class.getName(), publisherService, null);
	}

	public void stop(BundleContext context) throws Exception {
		try {
		UIProducerRegistration.unregister();
	} catch (Exception e) {
		System.out.println("UI producer: Error while unregistering the service");
	}
		System.out.println("................UI producer is stopped................");
		
	}

}