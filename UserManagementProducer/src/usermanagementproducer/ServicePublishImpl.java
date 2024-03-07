package usermanagementproducer;

import java.awt.Component;
import java.util.ArrayList;




public class ServicePublishImpl implements UserManagePublish {
	
	private ArrayList<Component> comps = new ArrayList<Component>();
	@Override
	public void setUIRules(ArrayList<Component> components) {
		this.comps = components;
		
		run();
    }
	
	public void run() {
		System.out.println("ServicePublishImpl on action");
		

			if(comps.isEmpty()) {
				System.out.println("No components to set rules");
			} else {
				for (Component comp : comps) {
					System.out.println("Setting rules for: " + comp.getName());
				}
			
		}
	}
	
	
		
	

}