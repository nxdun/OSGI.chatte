package usermanagementproducer;

import java.awt.Component;
import java.util.HashMap;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.mtit.UIproducer.ServicePublish;

public class ServicePublishImpl implements UserManagePublish {
	
	ServiceReference  UIproducerreference;
	BundleContext context;
	private HashMap<String, Component> comps = new HashMap<String, Component>();

	public void run() {
		UIproducerreference = context.getServiceReference(ServicePublish.class.getName());
		ServicePublish UIproducerService = (ServicePublish) context.getService(UIproducerreference);

				Component comp = comps.get("btnNewButton_2");
				//onclick event
				comp.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						
					}
				});
				
			
			
		}
	}
	
	
		
	

