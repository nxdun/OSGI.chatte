package usermanagementproducer;
import org.osgi.framework.BundleContext;

public interface UserManagePublish {
	//methods
	public void addLoginLogic();
	public void addRegistrationLogic();
	public void initiate(BundleContext context);
	public void addUser(String string, String string2);
	void registerUser(String username, String password);
	
	//chat client methods
	public void showChatFrame(String username, int frameno, int port);//show chat frame and user name
	public void cframethread(String username, int frameno, int port);
	public void stopThread();
	public void privateChatFrame(String username, String msg);
}
