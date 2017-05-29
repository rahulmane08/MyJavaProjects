package com.mane.rahul.services;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class HelloWorldInterceptor extends AbstractPhaseInterceptor<Message> {

	public HelloWorldInterceptor(String phase) {
		super(Phase.INVOKE);		
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		
		System.out.println("interception invoked");
	}

}
