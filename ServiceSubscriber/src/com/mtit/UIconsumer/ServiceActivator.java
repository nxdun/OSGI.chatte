package com.mtit.UIconsumer;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import usermanagementproducer.UserManagePublish;
import com.mtit.UIproducer.ServicePublish;

public class ServiceActivator implements BundleActivator {

	// ServiceReffernce
	ServiceReference serviceReference;
	ServiceReference UMserviceReference;

	public void start(BundleContext context) throws Exception {

		System.out.println("................UI Consumer is started................");
			// ui producer service
			serviceReference = context.getServiceReference(ServicePublish.class.getName());
			ServicePublish uiproducer = (ServicePublish) context.getService(serviceReference);

			// user management producer service
			UMserviceReference = context.getServiceReference(UserManagePublish.class.getName());
			UserManagePublish umproducer = (UserManagePublish) context.getService(UMserviceReference);
			
			// initiates the service with conncection to the UI
			try {
				uiproducer.initiate(context);
				umproducer.addLoginLogic();
				umproducer.addRegistrationLogic();
			
			uiproducer.headStart();
			
		} catch (NullPointerException e) {
			System.out.println( "nuu UI Consumer Looks like Referenced Service is not started yet. Please start the service(UI producer, UM Service) first." );
		}
	}

	public void stop(BundleContext context) throws Exception {

		// detach the service
		System.out.println(
				"................UI Consumer is stopped................");
		context.ungetService(serviceReference);
	}

}