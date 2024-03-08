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

				Component comp = comps.get("login_button_log");
			
			
		}
	}
	
	
		
	

