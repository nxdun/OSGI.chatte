package usermanagementproducer;
import java.awt.Component;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;

import org.osgi.framework.BundleContext;

public interface UserManagePublish {
	//method
	public void run();
	
	public void initiate(BundleContext context);
	
	public void showChatFrame(String username);//show chat frame and user name
	
	

}
