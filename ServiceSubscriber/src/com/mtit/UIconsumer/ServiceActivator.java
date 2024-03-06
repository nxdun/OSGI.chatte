package com.mtit.UIconsumer;

import org.osgi.framework.BundleActivator;


import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.UIproducer.ServicePublish;

public class ServiceActivator implements BundleActivator {

	//ServiceReffernce
	ServiceReference  serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(ServicePublish.class.getName());
		
		ServicePublish servicePublish = (ServicePublish) context.getService(serviceReference);
		System.out.println(servicePublish.publishService());
		
		//Login
		servicePublish.createLogFrame().setVisible(true);
		servicePublish.createLogFrame().setVisible(false);
		
		//Register
		servicePublish.createRegFrame().setVisible(true);
		
		//Chat
		servicePublish.createChatFrame().setVisible(true);
		
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Good Bye!!");
		//detach the service
		context.ungetService(serviceReference);
		
	}

}