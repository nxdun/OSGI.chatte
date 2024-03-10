package com.mtit.UIconsumer;

import org.osgi.framework.BundleActivator;


import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import usermanagementproducer.UserManagePublish;
import com.mtit.UIproducer.ServicePublish;

public class ServiceActivator implements BundleActivator {

	//ServiceReffernce
	ServiceReference  UMserviceReference;
	ServiceReference  serviceReference;


	public void start(BundleContext context) throws Exception {
		try {
		System.out.println("................UI Consumer is started................");
		serviceReference = context.getServiceReference(ServicePublish.class.getName());
		UserManagePublish UMproducerService = (UserManagePublish) context.getService(UMserviceReference);
		ServicePublish servicePublish = (ServicePublish) context.getService(serviceReference);
		
		//TODO:in here im going to handle UI creation logic
		servicePublish.initiate(context);
		//initiates the service with conncection to the UI
		UMproducerService.addLoginLogic();
		UMproducerService.addRegistrationLogic();
	} catch (Exception e) {
		System.out.println(e);
	}
		
	}
	

	public void stop(BundleContext context) throws Exception {
		
		
		//detach the service
		context.ungetService(serviceReference);
		System.out.println("................UI Consumer is stopped................");
	}

}