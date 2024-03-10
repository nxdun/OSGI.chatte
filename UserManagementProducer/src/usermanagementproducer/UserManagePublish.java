package usermanagementproducer;
import org.osgi.framework.BundleContext;

public interface UserManagePublish {
	//method
	public void addLoginLogic();
	public void addRegistrationLogic();
	public void ChatClientLogic();
	
	public void initiate(BundleContext context);
	
	public void showChatFrame(String username);//show chat frame and user name

	public void addUser(String string, String string2);
	
	void registerUser(String username, String password);
	
	

}
