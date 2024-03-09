package usermanagementproducer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    private int Frameno = 2;
    public void initiate(BundleContext cntext) {
        UIreference = cntext.getServiceReference(ServicePublish.class.getName());
        ServicePublish UIProducerService = (ServicePublish) cntext.getService(UIreference);
        UIProducerService.createLogFrame().setVisible(true);
        
        chatFrames.put(2 , (JFrame) UIProducerService.createChatFrame());
        chatFrames.put(3 , (JFrame) UIProducerService.createChatFrame());
        chatFrames.put(4 , (JFrame) UIProducerService.createChatFrame());
        chatFrames.put(5 , (JFrame) UIProducerService.createChatFrame());
        chatFrames.put(6 , (JFrame) UIProducerService.createChatFrame());
        
        chatFrames.put(1, (JFrame) UIProducerService.createRegFrame());
        chatFrames.get(1).setVisible(true);
        this.comps = UIProducerService.sendComponent();
    }

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
        
        // Get UI components for registration
        JTextField regUsernameField = (JTextField) comps.get("register_textfield_un");
        JTextField regPasswordField = (JTextField) comps.get("register_textfield_pwd");
        JButton registerButton = (JButton) comps.get("register_button_reg");
        JLabel regOutputLabel = (JLabel) comps.get("register_label_out");

        // Add action listener to register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = regUsernameField.getText();
                String password = regPasswordField.getText();

                // Register the user
                registerUser(username, password);
            }
        });
    }
    
    // Method to add user name and password
    public void addUser(String username, String password) {
        unpw.put(username, password);
    }

    // Method to validate login credentials
    private boolean isValidLogin(String username, String password) {
        return unpw.containsKey(username) && unpw.get(username).equals(password);
    }

    public void showChatFrame(String username) {
    	
    	JFrame chatFrame = chatFrames.get(Frameno);
		if (chatFrame.isVisible()) {
			Frameno++;
			chatFrame = chatFrames.get(Frameno);
			
		}

        chatFrame.setTitle("Chatte - " + username); // Set title with logged-in username
        chatFrame.setSize(600, 400); // Set size as per your requirement
        chatFrame.setLocationRelativeTo(null); // Center the frame on the screen
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        chatFrame.setVisible(true); // Make the frame visible
        
    }
    

    @Override
    public void registerUser(String username, String password) {
        JLabel regOutputLabel = (JLabel) comps.get("register_label_out");

        // Validate registration credentials
        if (isValidRegistration(username, password)) {
            // Show success message and add user
            addUser(username, password);
            regOutputLabel.setForeground(java.awt.Color.GREEN);
            regOutputLabel.setText("Registration successful!");
        } else {
            // Show error message
            regOutputLabel.setForeground(java.awt.Color.RED);
            regOutputLabel.setText("Username already exists!");
        }
    }
    
    private boolean isValidRegistration(String username, String password) {
        // Check if username already exists
        return !unpw.containsKey(username);
    }
}
