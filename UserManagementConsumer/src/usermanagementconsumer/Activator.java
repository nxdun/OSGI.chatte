package usermanagementconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import usermanagementproducer.UserManagePublish;

public class Activator implements BundleActivator {

	//ServiceReffernce
		ServiceReference  serviceReference;

		public void start(BundleContext context) throws Exception {
			System.out.println("................UM Consumer is started................");
			serviceReference = context.getServiceReference(UserManagePublish.class.getName());
			
			
			UserManagePublish servicePublish = (UserManagePublish) context.getService(serviceReference);
			

			
		}

		public void stop(BundleContext context) throws Exception {
			
			System.out.println("................UM Consumer is stopped................");
			//detach the service
			context.ungetService(serviceReference);
			
		}

}
