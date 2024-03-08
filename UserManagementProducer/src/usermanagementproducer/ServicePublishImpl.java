package usermanagementproducer;

import java.awt.Component;
import java.util.HashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.UIproducer.ServicePublish;




public class ServicePublishImpl implements UserManagePublish {
	ServiceReference  UIreference;
	
	public void initiate(BundleContext cntext){

		UIreference = cntext.getServiceReference(ServicePublish.class.getName());
		ServicePublish UIProducerService = (ServicePublish) cntext.getService(UIreference);
		UIProducerService.createLogFrame().setVisible(true);
		this.comps = UIProducerService.sendComponent();

	}
	
	private HashMap<String, Component> comps = new HashMap<String, Component>();
	
	
	public void run() {
		System.out.println("ServicePublishImpl on action");
		

			if(comps.isEmpty()) {
				System.out.println("No components to set rules");
			} else {
				System.out.println("Setting rules for components");
				for (String key : comps.keySet()) {
					System.out.println("Setting rules for " + key);
					Component comp = comps.get(key);
					comp.setEnabled(false);
				}
			
		}
	}
	
	
		
	

}