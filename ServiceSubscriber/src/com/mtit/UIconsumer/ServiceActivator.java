package com.mtit.UIconsumer;

import org.osgi.framework.BundleActivator;


import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.mtit.UIproducer.ServicePublish;

public class ServiceActivator implements BundleActivator {

	//ServiceReffernce
	ServiceReference  serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("................UI Consumer is started................");
		serviceReference = context.getServiceReference(ServicePublish.class.getName());
		
		
		ServicePublish servicePublish = (ServicePublish) context.getService(serviceReference);
		
		//TODO:in here im going to handle UI creation logic
		//initiate mtltiple users 
		//do not cancel any jframe
		
		
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("................UI Consumer is stopped................");
		//detach the service
		context.ungetService(serviceReference);
		
	}

}