package com.mtit.UIproducer;
import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;

public interface ServicePublish {
	//method
	public void headStart();
	public HashMap<String, Component> sendComponent();
	public Frame createLogFrame();
	public Frame createRegFrame();
	public Frame createChatFrame();

}
