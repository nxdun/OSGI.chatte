package usermanagementproducer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.UIproducer.ServicePublish;




public class ServicePublishImpl implements UserManagePublish {
	ServiceReference  UIreference;
	
	public void initiate(BundleContext cntext){

		UIreference = cntext.getServiceReference(ServicePublish.class.getName());
		ServicePublish UIProducerService = (ServicePublish) cntext.getService(UIreference);
		UIProducerService.createLogFrame().setVisible(true);
		UIProducerService.createChatFrame().setVisible(false);
		this.comps = UIProducerService.sendComponent();

	}
	
	private HashMap<String, Component> comps = new HashMap<String, Component>();
	
	
	 public void run() {
	        // Get UI components for login
	        JTextField usernameField = (JTextField) comps.get("login_textfield_un");
	        JTextField passwordField = (JTextField) comps.get("login_textfield_pw");
	        JButton loginButton = (JButton) comps.get("login_button_log");
	        JLabel outputLabel = (JLabel) comps.get("login_label_out");

	        // Add action listener to login button
	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String username = usernameField.getText();
	                String password = passwordField.getText();

	                // Validate login credentials
	                if (isValidLogin(username, password)) {
	                    // Show success message and proceed
	                    outputLabel.setText("Login successful!");
	                    showChatFrame(username);
	                    
	                } else {
	                    // Show error message
	                    outputLabel.setText("Invalid username or password");
	                }
	            }
	        });
	    }

	    // Method to validate login credentials
	    private boolean isValidLogin(String username, String password) {
	        return username.equals("admin") && password.equals("admin123");
	    }

	    // Method to show the chat frame upon successful login
	    public void showChatFrame(String username) {
			JFrame chatFrame = (JFrame) comps.get("ChatFrame");
			chatFrame.setVisible(true);
			 chatFrame.setTitle("Chatte - " + username); // Set title with logged-in username
	    }
	}