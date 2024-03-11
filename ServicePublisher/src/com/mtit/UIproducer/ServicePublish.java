package com.mtit.UIproducer;
import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;

import org.osgi.framework.BundleContext;

public interface ServicePublish {
	//method
	//UI Consumer will call this method to initiate the UI
	public void initiate(BundleContext cntext);
	//Contains in terminal functionality for manipulate other Services
	public void headStart();
	//sends all UI components to the UserManagement service
	public HashMap<String, Component> sendComponent();
	//initialize relevant frames
	public Frame createLogFrame();
	public Frame createRegFrame();
	public Frame createChatFrame();
	public Frame privateChatFrame();
	public Frame PrivateSelectUserFrame();

}
