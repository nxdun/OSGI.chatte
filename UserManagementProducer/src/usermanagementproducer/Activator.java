package usermanagementproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		
		
		System.out.println("..........Publisher Start usermanagementproducer..........");
		UserManagePublish ServicePublishImpl = new ServicePublishImpl();
		
		//register the service
		publishServiceRegistration = context.registerService(
				UserManagePublish.class.getName(), ServicePublishImpl, null);

		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("..........Publisher Stop usermanagementproducer..........");
		publishServiceRegistration.unregister();
		
	}

}
