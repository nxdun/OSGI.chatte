package usermanagementproducer;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;




public class ServicePublishImpl implements UserManagePublish {
	
	private HashMap<String, Component> comps = new HashMap<String, Component>();
	@Override
	public void setUIRules(HashMap<String, Component> components) {
		this.comps = components;
		
		run();
    }
	
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