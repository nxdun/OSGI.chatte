package com.mtit.UIproducer;
import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;

public interface ServicePublish {
	//method
	public ArrayList<Component> sendComponent();
	public Frame createLogFrame();
	public Frame createRegFrame();
	public Frame createChatFrame();

}
