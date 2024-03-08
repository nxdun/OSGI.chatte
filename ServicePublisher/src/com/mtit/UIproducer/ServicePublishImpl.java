package com.mtit.UIproducer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ServicePublishImpl implements ServicePublish {
	
    //hashmap to store the components so we can manipulate them in a different service
	    private static HashMap<String, Component> components = new HashMap<String, Component>();

	 //im here to send the components to the other service
	@Override
	public HashMap<String, Component> sendComponent() {
		return components;
	}
	
	//im here to create the raw login frame
	@Override
	public Frame createLogFrame() {
		//create a new JFrame
		JFrame frame = new JFrame();
		//add it to components array
		components.put("frame",frame);
		
		//set the default actions
		frame.setTitle("Chatte : login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		components.put("",login_textfield_pw);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(102, 58, 244, 33);
		contentPane.add(textField);
		components.put("textField",textField);
		
		JButton login_button_log = new JButton("New button");
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel register_username = new JLabel("User Name");
		register_username.setBounds(37, 90, 101, 14);
		contentPane.add(register_username);
		
		JTextField register_textfield_un = new JTextField();
		register_textfield_un.setBounds(169, 87, 186, 20);
		contentPane.add(register_textfield_un);
		register_textfield_un.setColumns(10);
		
		JLabel register_pdw = new JLabel("Password");
		register_pdw.setBounds(37, 137, 101, 14);
		contentPane.add(register_pdw);
		
		JTextField register_textfield_pwd = new JTextField();
		register_textfield_pwd.setBounds(169, 134, 186, 20);
		contentPane.add(register_textfield_pwd);
		register_textfield_pwd.setColumns(10);
		
		JButton register_button_reg = new JButton("Register");
		register_button_reg.setBounds(132, 183, 89, 23);
		contentPane.add(register_button_reg);
		
		JButton register_button_reset = new JButton("Reset");
		register_button_reset.setBounds(229, 183, 89, 23);
		contentPane.add(register_button_reset);
		
		JButton btnNewButton_2 = new JButton("Go Back");
		btnNewButton_2.setBounds(19, 11, 89, 23);
		contentPane.add(btnNewButton_2);
		return frame;
	}

	@Override
	public Frame createChatFrame() {
		JFrame frame = new JFrame();
		
		frame.setTitle("Chatte");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 527, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextField message_panel = new JTextField();
		message_panel.setBounds(10, 230, 392, 20);
		contentPane.add(message_panel);
		message_panel.setColumns(10);
		
		JButton send_brodcast = new JButton("Send");
		send_brodcast.setBounds(412, 229, 89, 23);
		contentPane.add(send_brodcast);
		
		JButton send_pvt = new JButton("Private Message");
		send_pvt.setBounds(352, 22, 149, 23);
		contentPane.add(send_pvt);
		
		JButton send_grp = new JButton("Group Message");
		send_grp.setBounds(352, 56, 149, 23);
		contentPane.add(send_grp);
		
		JScrollPane main_chat = new JScrollPane();
		main_chat.setBounds(10, 11, 326, 210);
		contentPane.add(main_chat);
		
		JScrollPane user_list = new JScrollPane();
		user_list.setBounds(352, 90, 149, 129);
		contentPane.add(user_list);
		return frame;
	}



}
