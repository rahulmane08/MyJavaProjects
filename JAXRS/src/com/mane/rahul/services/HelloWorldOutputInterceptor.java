package com.mane.rahul.services;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class HelloWorldOutputInterceptor extends
		AbstractPhaseInterceptor<Message> {

	public HelloWorldOutputInterceptor(String phase) {
		super(Phase.SEND);
	
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		System.out.println("output interceptor invoked");

	}

}
