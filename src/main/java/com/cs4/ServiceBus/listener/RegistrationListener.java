package com.cs4.ServiceBus.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegistrationListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    public void onMessage(Message message) {
    	System.out.println("Received Registration Message");

        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
        	logger.info(objectMessage.getObject().toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
        
        logger.info("Registration Message received" );
        logger.info(objectMessage.toString());
		
    }
}