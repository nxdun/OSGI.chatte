package com.mtit.UIproducer;
import java.awt.Color;
import java.awt.Frame;



import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ServicePublishImpl implements ServicePublish {

	@Override
	public String publishService() {


		//import swing package
		
		return "Execute the publish of ServicePublisher";
	}

	@Override
	public Frame createFrame() {
		JFrame frame = new JFrame();
		
		frame.setTitle("Chatte : login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel login_label_un = new JLabel("UserName");
		login_label_un.setHorizontalAlignment(SwingConstants.CENTER);
		login_label_un.setBounds(175, 24, 103, 24);
		contentPane.add(login_label_un);
		
		JLabel login_label_pw = new JLabel("Password");
		login_label_pw.setHorizontalAlignment(SwingConstants.CENTER);
		login_label_pw.setBounds(175, 111, 103, 24);
		contentPane.add(login_label_pw);
		
		JTextField login_textfield_pw = new JTextField();
		login_textfield_pw.setBounds(102, 137, 244, 33);
		contentPane.add(login_textfield_pw);
		login_textfield_pw.setColumns(10);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(102, 58, 244, 33);
		contentPane.add(textField);
		
		JButton login_button_log = new JButton("New button");
		
		
		login_button_log.setForeground(new Color(0, 0, 64));
		login_button_log.setBackground(new Color(128, 0, 255));
		login_button_log.setBounds(84, 190, 256, 33);
		contentPane.add(login_button_log);
		
		
		JLabel login_label_out = new JLabel("");
		

				login_button_log.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						login_label_out.setText("Login Success");
					}
				});
		
		login_label_out.setHorizontalAlignment(SwingConstants.CENTER);
		login_label_out.setBounds(175, 239, 103, 24);
		contentPane.add(login_label_out);
		return frame;
	}

}
