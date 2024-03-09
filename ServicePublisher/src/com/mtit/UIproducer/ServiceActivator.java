package com.mtit.UIproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration UIProducerRegistration;
	
	//start the lifecycle of producer service
	public void start(BundleContext context) throws Exception {
		System.out.println("................UI producer is started................");
		ServicePublish publisherService = new ServicePublishImpl();
		//register the service
		UIProducerRegistration = context.registerService(
				ServicePublish.class.getName(), publisherService, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("................UI producer is stopped................");
		UIProducerRegistration.unregister();
		
	}

}