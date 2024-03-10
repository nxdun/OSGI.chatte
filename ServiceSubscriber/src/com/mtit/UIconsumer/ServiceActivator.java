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
	
		System.out.println("................UI Consumer is started................");
		//ui producer service
		serviceReference = context.getServiceReference(ServicePublish.class.getName());
		ServicePublish servicePublish = (ServicePublish) context.getService(serviceReference);
		
		//user management producer service
		UMserviceReference = context.getServiceReference(UserManagePublish.class.getName());
		UserManagePublish UMproducerService = (UserManagePublish) context.getService(UMserviceReference);
		
		
		//TODO:in here im going to handle UI creation logic
		
		
		//initiates the service with conncection to the UI
		UMproducerService.addLoginLogic();
		UMproducerService.addRegistrationLogic();
		servicePublish.initiate(context);
		servicePublish.headStart();
		
	}
	

	public void stop(BundleContext context) throws Exception {
		
		
		//detach the service
		System.out.println("................UI Consumer is stopped................");
		context.ungetService(serviceReference);
	}

}