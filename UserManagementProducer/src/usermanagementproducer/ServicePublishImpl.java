package usermanagementproducer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	
	ServiceReference UIreference;
	ServicePublish UIProducerService;

	private HashMap<Integer, JFrame> chatFrames = new HashMap<Integer, JFrame>();
	private HashMap<String, Component> comps = new HashMap<String, Component>();
	private HashMap<String, String> unpw = new HashMap<String, String>();

	public void initiate(BundleContext cntext) {

		UIreference = cntext
				.getServiceReference(ServicePublish.class.getName());
		ServicePublish UIProducerService = (ServicePublish) cntext
				.getService(UIreference);
		UIProducerService.createLogFrame().setVisible(true);
		chatFrames.put(0,(JFrame) UIProducerService.createChatFrame());
		chatFrames.put(1,(JFrame) UIProducerService.createRegFrame());
		this.comps = UIProducerService.sendComponent();

	}

	public void run() {
		unpw.put("admin", "admin");

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
					outputLabel.setForeground(java.awt.Color.GREEN);
					outputLabel.setText("Login successful!");
					showChatFrame(username);

				} else {
					// Show error message
					outputLabel.setForeground(java.awt.Color.RED);
					outputLabel.setText("Invalid username or password!");
				}
			}
		});
	}
	//method to add user name and password
	public void addUser(String username, String password) {
		unpw.put(username, password);
	}

	// Method to validate login credentials
	private boolean isValidLogin(String username, String password) {

		return unpw.containsKey(username)
				&& unpw.get(username).equals(password);
	}

	// Method to show the chat frame upon successful login
	public void showChatFrame(String username) {
		//get int 0 from chat frame
		JFrame chatFrame = chatFrames.get(0);
		chatFrame.setVisible(true);
		chatFrame.setTitle("Chatte - " + username); // Set title with logged-in username
													
	}
}