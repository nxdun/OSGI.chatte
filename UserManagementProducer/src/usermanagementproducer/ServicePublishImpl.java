package usermanagementproducer;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.UIproducer.ServicePublish;


public class ServicePublishImpl implements UserManagePublish {
    
    ServiceReference UIreference;
    ServicePublish UIProducerService;

    private HashMap<Integer, JFrame> chatFrames = new HashMap<Integer, JFrame>();
    private ArrayList<JFrame> frameArray = new ArrayList<JFrame>();
    private HashMap<String, Component> comps = new HashMap<String, Component>();
    private HashMap<String, String> unpw = new HashMap<String, String>();
    private int Frameno = 2;
    private JTextArea user_list_m ;
    private JTextArea main_chat_m ;
    

    //initiation of the service takes frames the UI
    public void initiate(BundleContext cntext) {
        UIreference = cntext.getServiceReference(ServicePublish.class.getName());
        ServicePublish UIProducerService = (ServicePublish) cntext.getService(UIreference);
        
        
        //creating some chat frames because every frame is singleton
        // 0 - 10 chatframes
		for (int i = 2; i < 7; i++) {
			chatFrames.put(i, (JFrame) UIProducerService.createChatFrame());
		}
		for (int i = 0; i <= 9 ; i++) {
			frameArray.add((JFrame) UIProducerService.createChatFrame());
		}

		//creating the registration frame
		chatFrames.put(0, (JFrame) UIProducerService.createLogFrame());
        chatFrames.put(1, (JFrame) UIProducerService.createRegFrame());
       
        //take UI components from the UI service
        this.comps = UIProducerService.sendComponent();
        
        user_list_m = new JTextArea();
        main_chat_m = new JTextArea();
    }
    
    public void addLoginLogic() {
    	// Get UI components for login
        JTextField usernameField = (JTextField) comps.get("login_textfield_un");
        JTextField passwordField = (JTextField) comps.get("login_textfield_pw");
        JButton loginButton = (JButton) comps.get("login_button_log");
        JLabel outputLabel = (JLabel) comps.get("login_label_out");

        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//takes the username and password from the UI
                String username = usernameField.getText();
                String password = passwordField.getText();

                // Validate login credentials
                if (isValidLogin(username, password)) {
                    // Show success message and proceed
                    outputLabel.setForeground(java.awt.Color.GREEN);
                    outputLabel.setText("Login successful!");
                    
                    //displays chatframe from here
                    showChatFrame(username,3);

                } else {
                    // Show error message
                    outputLabel.setForeground(java.awt.Color.RED);
                    outputLabel.setText("Invalid username or password!");
                }
            }
        });
        chatFrames.get(0).setVisible(true);
    }
    
	public void addRegistrationLogic() {
		 
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
                regUsernameField.setText("");
                regPasswordField.setText("");
            }
        });
        chatFrames.get(1).setVisible(true);
	}
	//registration logic method 
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
	 //registration logic method 2
	    private boolean isValidRegistration(String username, String password) {
	        // Check if username already exists
	        return !unpw.containsKey(username);
	    }
    

    // Method to add user name and password
    public void addUser(String username, String password) {
        unpw.put(username, password);
    }

    // Method to validate login credentials
    private boolean isValidLogin(String username, String password) {
        return unpw.containsKey(username) && unpw.get(username).equals(password);
    }

    
    public void cframethread(String username, int frameno) {
		// thread to display chat frame
		Thread cframethread = new Thread() {
			public void run() {
				showChatFrame(username, frameno);
			}
		};
		cframethread.start();
    }
    
    
    //displays chat frame max 6 USERS
    public void showChatFrame(String username, int frameno) {
    	JFrame chatFrame = chatFrames.get(frameno);
    	ShowChatFrames showChatFrames = new ShowChatFrames(chatFrame, username, comps);
      
    }
    

  
}

 class ShowChatFrames {
    private JTextArea user_list_m = new JTextArea() ;
    private JTextArea main_chat_m = new JTextArea() ;
	
	//constructor
	public ShowChatFrames(JFrame frame, String username, HashMap<String, Component> comps) {
		
		 System.out.println("startshowChatFrame");
			try {
			JFrame chatFrame = frame;
			// get chatFrame current Componenta
			Component[] components = chatFrame.getContentPane().getComponents();


		    chatFrame.setTitle("Chatte - " + username); // Set title with logged-in username
		    chatFrame.setSize(600, 400); // Set size as per your requirement
		    chatFrame.setLocationRelativeTo(null); // Center the frame on the screen

		    
		    //import needy components
		    //get Jpanel from the components
		    //JPanel contentPane = (JPanel) comps.get("frame");
		    JPanel contentPane = (JPanel) chatFrame.getContentPane();
		    JTextField message_panel = (JTextField) contentPane.getComponent(0) ;
		    JButton send_brodcast = null;
		    JButton send_pvt = null;
		    JButton send_grp = null;
		    JScrollPane main_chat = (JScrollPane) comps.get("main_chat");
		    JScrollPane user_list = (JScrollPane) comps.get("user_list");
		    
		    for (Component component : components) {
		        if (component instanceof JTextField && component.getName() != null && component.getName().equals("message_panel")) {
		            message_panel = (JTextField) component;
		        } else if (component instanceof JButton) {
		            JButton button = (JButton) component;
		            switch (button.getText()) {
		                case "Send":
		                    send_brodcast = button;
		                    break;
		                case "Private Message":
		                    send_pvt = button;
		                    break;
		                case "Group Message":
		                    send_grp = button;
		                    break;
		            }
		        } 
		    }
		    
		    //alternate method to get the components
		    for (Component component : components) {
		        if (component instanceof JScrollPane) {
		            Rectangle bounds = component.getBounds();
		            // Check if the bounds match for main_chat
		            if (bounds.x == 10 && bounds.y == 11 && bounds.width == 326 && bounds.height == 210) {
		                main_chat = (JScrollPane) component;
		            }
		            // Check if the bounds match for user_list
		            else if (bounds.x == 352 && bounds.y == 90 && bounds.width == 149 && bounds.height == 129) {
		                user_list = (JScrollPane) component;
		            }
		        }
		    }

		    	main_chat.setViewportView(main_chat_m);

				user_list.setViewportView(user_list_m);

		    
		    user_list_m.setEditable(false);
		    main_chat_m.setEditable(false);
		    System.out.println("startshowChatFrame2 messcss" + main_chat_m );
		    //add action listener
		    send_brodcast.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	JTextField message_panel = (JTextField) contentPane.getComponent(0) ;
		        	String message = message_panel.getText();
		        	System.out.println("msg is "+message);
		        		main_chat_m.append("You: " + message + "\n");
		        		message_panel.setText("");
		        	
		        }
		    });
			send_pvt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField message_panel = (JTextField) contentPane.getComponent(0) ;
					String message = message_panel.getText();
					System.out.println("p msg is " + message);
					main_chat_m.append("You: " + message + "\n");
					message_panel.setText("");
				}
			});
		
		    chatFrame.setVisible(true); // Make the frame visible
		} catch (Exception e) {
			System.out.println("error in showChatFrame " + e);
		}
		

		
	}
	

}










