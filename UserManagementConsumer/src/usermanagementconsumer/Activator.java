package usermanagementconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import usermanagementproducer.UserManagePublish;

public class Activator implements BundleActivator {

	//ServiceReffernece
		ServiceReference  UMreference;

		public void start(BundleContext context) throws Exception {
			System.out.println("................Start usermanagement consumer................");
			UMreference = context.getServiceReference(UserManagePublish.class.getName());
			UserManagePublish UMproducerService = (UserManagePublish) context.getService(UMreference);
			//initiates the service with conncection to the UI
			UMproducerService.initiate(context);	
			
			
		}

		public void stop(BundleContext context) throws Exception {
			System.out.println("..................Stop usermanagement consumer..................");
			UserManagePublish UMproducerService = (UserManagePublish) context.getService(UMreference);
			UMproducerService.stopThread();
			context.ungetService(UMreference);
		}

}
