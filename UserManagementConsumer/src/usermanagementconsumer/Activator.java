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
			
			System.out.println("creating things to send to other service");
			
			UMproducerService.initiate(context);
			UMproducerService.run();
			
		}

		public void stop(BundleContext context) throws Exception {
			
			System.out.println("..................Stop Subscriber Service..................");
			//detach the service biii all
			context.ungetService(UMreference);
			
		}

}
