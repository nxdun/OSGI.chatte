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
			//ServicePublishImpl construct
	
			
			//initiates the service with conncection to the UI
			UMproducerService.initiate(context);
			UMproducerService.showChatFrame("nadun", 3, 9003);
		    UMproducerService.cframethread("vishwa", 2, 9003);
	
			
			
			
		}

		public void stop(BundleContext context) throws Exception {
			
			System.out.println("..................Stop usermanagement consumer..................");
			//detach the service biii all
			context.ungetService(UMreference);
			
		}

}
