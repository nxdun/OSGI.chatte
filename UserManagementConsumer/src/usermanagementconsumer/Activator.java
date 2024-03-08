package usermanagementconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import usermanagementproducer.UserManagePublish;
import com.mtit.UIproducer.ServicePublish;

public class Activator implements BundleActivator {

	//ServiceReffernece
		ServiceReference  UIproducerreference;
		ServiceReference  UsermanagerProducerreference;

		public void start(BundleContext context) throws Exception {
			System.out.println("................Start usermanagement consumer................");
			
			UIproducerreference = context.getServiceReference(UserManagePublish.class.getName());
			UsermanagerProducerreference = context.getServiceReference(ServicePublish.class.getName());
			
			
			UserManagePublish UIproducerService = (UserManagePublish) context.getService(UIproducerreference);
			ServicePublish UsermanagerProducerService = (ServicePublish) context.getService(UsermanagerProducerreference);
			
			System.out.println("creating things to send to other service");
			
			UsermanagerProducerService.createLogFrame().setVisible(true);

			System.out.println("starting to send rules");
	        
	
			//recives raw ui data from the service
			//normally all ui components are sent to the service as hashset
			//in here methodwisely run ui functions seperately
			//use publisher service only to set rules
			//and here run method and implement the rules
			UIproducerService.setUIRules(UsermanagerProducerService.sendComponent());
	        //a sample method
			UIproducerService.run();
			
		}

		public void stop(BundleContext context) throws Exception {
			
			System.out.println("..................Stop Subscriber Service..................");
			//detach the service biii all
			context.ungetService(UIproducerreference);
			context.ungetService(UsermanagerProducerreference);
			
		}

}
