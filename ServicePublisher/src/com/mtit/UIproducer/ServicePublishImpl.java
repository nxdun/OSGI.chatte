package com.mtit.UIproducer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import chatserverproducer.ChatServerInterface;
import usermanagementproducer.UserManagePublish;

public class ServicePublishImpl implements ServicePublish {
	
	ServiceReference CSreference; //get chat producer
	ServiceReference UMreference; //get user management producer
	
	//get chat server interface
	ChatServerInterface chatServer;
	UserManagePublish UM;
    //hashmap to store the components so we can manipulate them in a different service
	    private static HashMap<String, Component> components = new HashMap<String, Component>();

	    	//this will take initiative in chatserver connction
			public void initiate(BundleContext cntext) {
				//get the chat server interface
				CSreference = cntext.getServiceReference(ChatServerInterface.class.getName());
				chatServer = (ChatServerInterface) cntext.getService(CSreference);
				//get the user management interface
				UMreference = cntext.getServiceReference(UserManagePublish.class.getName());
				UM = (UserManagePublish) cntext.getService(UMreference);
	        }


			//contains in terminal functionality for manipulate other Services
	    	public void headStart() {
	    		try {
		    		
		    		System.out.print("_____________________________________________________"
		    				+ 		   "\n         hi there! Welcome to chat server wizard"
		    				+ 		   "\n_____________________________________________________"
		    				+ 		   "\n_____________________________________________________");

		    		while(true) {
		    			//menu
		    			System.out.println("\n"
		    					+ 		   "\n__Chat Server Service Management__"
		    					+ "\n1) Take current CHAT SERVER PORT"
		    					+ "\n2)Stops CHAT SERVER"
		    					+ "\n3)Set a new PORT and restart CHATSERVER"
		    					+ "\n4)kick a client from server"
		    					+ 		   "\n__Chat Clients Service Management__"
		    					+ "\n5)Stops all client ui threads"
		    					+ "\n6)register a new user"
		    					+ 		   "\n__Other__"
		    					+ "\n0) TESTING : start log in frame"
		    					+ "\n99)exit from entire framework"
		    					+ "\n_____________________________________________________"
		    					+ "\n"
		    					);
		    			Scanner sc = new Scanner(System.in);
		    			int choice = sc.nextInt();
		    			switch(choice) {
		    			case 0:
		    				//toggles the log frame visibility
		    				System.out.println("testing purpose");
		    				UM.addLoginLogic();
		    				UM.addRegistrationLogic();
		    				break;
		    			case 1:
		    				//prints running chat server port
		    				System.out.println("current chat server port is : "+ chatServer.getPort());
		    				break;
		    			case 2:
		    				//calls ChatServer service to stop the server
							System.out.println("..STOPPING chat server..");
							try {
								chatServer.stopServer();
							} catch (Exception e) {
								System.out.println("cant stop server/server is not running../already stopped..");
								e.printStackTrace();
							}
							System.out.println("..server stopped..");
							break;
						case 3 :
							// sets a new port and restarts the server
							System.out.println("enter  new port you want to set");
							Scanner sc2 = new Scanner(System.in);
							int newPort = sc2.nextInt();
							//validate the port number
							if (newPort < 1024 || newPort > 65535) {
								System.out.println("TRY AGAIN : invalid port number");
								break;
							}
							chatServer.setPort(newPort);
							break;
						case 4 :
							// removes a client from the server
							System.out.println(
									"enter the client name you want to remove");
							Scanner sc3 = new Scanner(System.in);
							String client = sc3.next();
							System.out.println(chatServer.removeClient(client));
						case 5 :
							// stops all client ui threads
							System.out.println("stopping all client threads");
							UM.stopThread();
							break;
						case 6 :
							// registers a new user
							Scanner sc4 = new Scanner(System.in);
							System.out.println("enter the username");
							String username = sc4.next();
							System.out.println("enter the password");
							String password = sc4.next();
							try {
								UM.addUser(username, password);
							} catch (Exception e) {
								System.out.println("error in adding user");
								e.printStackTrace();
							}
				
							break;
						case 99 :
							//exits the java VM
							System.out.println("are you sure you want to exit? (y/n)");
							Scanner sc5 = new Scanner(System.in);
							String exit = sc5.next();
							if(exit.equals("y") || exit.equals("Y")) {
	                            System.exit(0);
							} else {
								System.out.println("returning to main menu");
							}
							break;
		    			default:
		    				System.out.println("invalid choice ");
		    				break;
		    			}
		    		}
				} catch (Exception e) {
					System.out.println("error in headstart" + e);
					e.printStackTrace();
				}
			} 
	    		
	    	

	    	
	 //im here to send the components to the other service
	@Override
	public HashMap<String, Component> sendComponent() {
		return components;
	}
	
	//all methods from here are for creating frames
	//also puts components to the hashmap with Same Name
		@Override
		public Frame createLogFrame() {
			//create a new JFrame
			JFrame frame = new JFrame();
			//add it to components array
			components.put("frame",frame);
			
			//set the default actions
			frame.setTitle("Chatte : login");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setBounds(100, 100, 450, 300);
			JPanel contentPane = new JPanel();
			//add name to the array
			
			components.put("frame",contentPane);
			
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel login_label_un = new JLabel("UserName");
			login_label_un.setHorizontalAlignment(SwingConstants.CENTER);
			login_label_un.setBounds(175, 24, 103, 24);
			contentPane.add(login_label_un);
			components.put("login_label_un",login_label_un);
			
			JLabel login_label_pw = new JLabel("Password");
			login_label_pw.setHorizontalAlignment(SwingConstants.CENTER);
			login_label_pw.setBounds(175, 111, 103, 24);
			contentPane.add(login_label_pw);
			components.put("login_label_pw",login_label_pw);
			
			JTextField login_textfield_pw = new JTextField();
			login_textfield_pw.setBounds(102, 137, 244, 33);
			contentPane.add(login_textfield_pw);
			login_textfield_pw.setColumns(10);
			components.put("login_textfield_pw",login_textfield_pw);
			
			JTextField login_textfield_un = new JTextField();
			login_textfield_un.setColumns(10);
			login_textfield_un.setBounds(102, 58, 244, 33);
			contentPane.add(login_textfield_un);
			components.put("login_textfield_un",login_textfield_un);
			
			JButton login_button_log = new JButton("Login");
			login_button_log.setForeground(new Color(0, 0, 64));
			login_button_log.setBackground(new Color(128, 0, 255));
			login_button_log.setBounds(84, 190, 256, 33);
			contentPane.add(login_button_log);
			components.put("login_button_log",login_button_log);
			
			
			JLabel login_label_out = new JLabel("");
			login_label_out.setHorizontalAlignment(SwingConstants.CENTER);
			login_label_out.setBounds(175, 239, 103, 24);
			contentPane.add(login_label_out);
			components.put("login_label_out",login_label_out);
			
			
			return frame;
		}


		@Override
		public Frame createRegFrame() {
			JFrame frame = new JFrame();
			
			frame.setTitle("Chatte : Register");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame .setBounds(100, 100, 450, 300);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel register_server = new JLabel("Server Name");
			register_server.setBounds(37, 48, 101, 14);
			contentPane.add(register_server);
			
			JTextField register_textfield_sn = new JTextField();
			register_textfield_sn.setBounds(169, 45, 186, 20);
			contentPane.add(register_textfield_sn);
			register_textfield_sn.setColumns(10);
			components.put("register_textfield_sn",register_textfield_sn);
			
			JLabel register_username = new JLabel("User Name");
			register_username.setBounds(37, 90, 101, 14);
			contentPane.add(register_username);
			
			JTextField register_textfield_un = new JTextField();
			register_textfield_un.setBounds(169, 87, 186, 20);
			contentPane.add(register_textfield_un);
			register_textfield_un.setColumns(10);
			components.put("register_textfield_un",register_textfield_un);
			
			
			JLabel register_pdw = new JLabel("Password");
			register_pdw.setBounds(37, 137, 101, 14);
			contentPane.add(register_pdw);
			
			JTextField register_textfield_pwd = new JTextField();
			register_textfield_pwd.setBounds(169, 134, 186, 20);
			contentPane.add(register_textfield_pwd);
			register_textfield_pwd.setColumns(10);
			components.put("register_textfield_pwd",register_textfield_pwd);
			
			JButton register_button_reg = new JButton("Register");
			register_button_reg.setBounds(132, 183, 89, 23);
			contentPane.add(register_button_reg);
			components.put("register_button_reg",register_button_reg);
			
			JButton register_button_reset = new JButton("Reset");
			register_button_reset.setBounds(229, 183, 89, 23);
			contentPane.add(register_button_reset);
			
			JButton btnNewButton_2 = new JButton("Go Back");
			btnNewButton_2.setBounds(19, 11, 89, 23);
			contentPane.add(btnNewButton_2);
			
			JLabel register_label_out = new JLabel("");
			register_label_out.setHorizontalAlignment(SwingConstants.CENTER);
			register_label_out.setBounds(175, 239, 103, 24);
			contentPane.add(register_label_out);
			components.put("register_label_out",register_label_out);
			
			
			return frame;
		}

		@Override
		public Frame createChatFrame() {
			JFrame frame = new JFrame();
			components.put("ChatFrame",frame);
			
			frame.setTitle("Chatte");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setBounds(100, 100, 527, 300);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			components.put("frame",contentPane);

			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JTextField message_panel = new JTextField();
			message_panel.setBounds(10, 230, 392, 20);
			contentPane.add(message_panel);
			message_panel.setColumns(10);
			components.put("message_panel",message_panel);
			
			JButton send_brodcast = new JButton("Send");
			send_brodcast.setBounds(412, 229, 89, 23);
			contentPane.add(send_brodcast);
			components.put("send_brodcast",send_brodcast);
			
			JButton send_pvt = new JButton("Private Message");
			send_pvt.setBounds(352, 22, 149, 23);
			contentPane.add(send_pvt);
			components.put("send_pvt",send_pvt);
			
			JButton send_grp = new JButton("Group Message");
			send_grp.setBounds(352, 56, 149, 23);
			contentPane.add(send_grp);
			components.put("send_grp",send_grp);
			
			JScrollPane main_chat = new JScrollPane();
			main_chat.setBounds(10, 11, 326, 210);
			contentPane.add(main_chat);
			components.put("main_chat",main_chat);
			
			JScrollPane user_list = new JScrollPane();
			user_list.setBounds(352, 90, 149, 129);
			contentPane.add(user_list);
			components.put("user_list",user_list);
			return frame;
		}
		public Frame privateChatFrame() {
			JFrame frame = new JFrame();
			
			frame.setTitle("Private Message");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setBounds(100, 100, 484, 333);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			components.put("pvtframe",contentPane);

			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JTextField Pvt_msg_txtField = new JTextField();
			Pvt_msg_txtField.setBounds(10, 263, 338, 20);
			contentPane.add(Pvt_msg_txtField);
			Pvt_msg_txtField.setColumns(10);
			components.put("Pvt_msg_txtField",Pvt_msg_txtField);
			
			JButton Pvt_msg_Send = new JButton("Send");
			Pvt_msg_Send.setBounds(369, 262, 89, 23);
			contentPane.add(Pvt_msg_Send);
			components.put("Pvt_msg_Send",Pvt_msg_Send);
			
			JTextPane textPane_pvt = new JTextPane();
			textPane_pvt.setBounds(10, 11, 448, 227);
			contentPane.add(textPane_pvt);
			components.put("textPane_pvt",textPane_pvt);
			
			JButton pvtchat_gobackbtn = new JButton("Go back");
			pvtchat_gobackbtn.setBounds(10, 0, 89, 23);
			contentPane.add(pvtchat_gobackbtn);
			components.put("pvtchat_gobackbtn",pvtchat_gobackbtn);
			
			return frame;
		}
		
		public Frame PrivateSelectUserFrame() {
			JFrame frame = new JFrame();
			
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setBounds(100, 100, 295, 225);
			JPanel contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			components.put("selectuserframe",contentPane);

			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JTextField textField_selectuname = new JTextField();
			textField_selectuname.setBounds(37, 56, 214, 20);
			contentPane.add(textField_selectuname);
			textField_selectuname.setColumns(10);
			components.put("textField_selectuname",textField_selectuname);
			
			JLabel selectUname = new JLabel("User name");
			selectUname.setBounds(37, 29, 99, 14);
			contentPane.add(selectUname);
			
			JButton select_btnuser = new JButton("Select");
			select_btnuser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			select_btnuser.setBounds(94, 106, 89, 23);
			contentPane.add(select_btnuser);
			components.put("select_btnuser",select_btnuser);
			
			JButton gobackBtn_selectuser = new JButton("Go back");
			gobackBtn_selectuser.setBounds(94, 140, 89, 23);
			contentPane.add(gobackBtn_selectuser);
			components.put("gobackBtn_selectuser",gobackBtn_selectuser);
			
			return frame;
		}



	}
