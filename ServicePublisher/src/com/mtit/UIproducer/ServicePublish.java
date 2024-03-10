package com.mtit.UIproducer;
import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;

import org.osgi.framework.BundleContext;

public interface ServicePublish {
	//method
	public void initiate(BundleContext cntext);
	public void headStart();
	public HashMap<String, Component> sendComponent();
	public Frame createLogFrame();
	public Frame createRegFrame();
	public Frame createChatFrame();
	public Frame privetChat();
	public Frame PrivateSelectUser();

}
