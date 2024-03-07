package usermanagementconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import usermanagementproducer.UserManagePublish;
import com.mtit.UIproducer.ServicePublish;

public class Activator implements BundleActivator {

	//ServiceReffernce
		ServiceReference  serviceReference;
		ServiceReference  serviceReference2;

		public void start(BundleContext context) throws Exception {
			System.out.println("Start Subscriber Service");
			serviceReference = context.getServiceReference(UserManagePublish.class.getName());
			serviceReference2 = context.getServiceReference(ServicePublish.class.getName());
			
			
			UserManagePublish umPublish = (UserManagePublish) context.getService(serviceReference);
			ServicePublish UIPublish = (ServicePublish) context.getService(serviceReference2);
			
			System.out.println("creating things to send to other service");
			
			UIPublish.createLogFrame().setVisible(true);

			System.out.println("starting to send rules");
	        
	        
	        
	        umPublish.setUIRules(UIPublish.sendComponent());
	        //send data to other service
	        System.out.println("starting to send rules 2 ");
	        umPublish.run();
	        System.out.println("starting to send rules 3 ");
			
		}

		public void stop(BundleContext context) throws Exception {
			
			System.out.println("Good Bye!!");
			//detach the service
			context.ungetService(serviceReference);
			
		}

}
