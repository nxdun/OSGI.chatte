package com.mtit.UIconsumer;

import java.awt.Frame;

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
		
		
		//now handling ui navigation logic in a single lifecycle
		Frame frame = servicePublish.headStart();
		frame.setVisible(true);
		servicePublish.sendComponent().get("login_button_log").setVisible(false);
		
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("................UI Consumer is stopped................");
		//detach the service
		context.ungetService(serviceReference);
		
	}

}