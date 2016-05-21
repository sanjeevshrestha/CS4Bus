package com.cs4.ServiceBus.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Listener that listens to the JMS channel to receive Registration data. 
 * This then pushes the data through the gateway to the bus for other components to process
 * 
 * @author sanjeev
 *
 */
public class RegistrationListener implements MessageListener {
    /**
     * 
     */
    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    /* (non-Javadoc)
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     * onMessage method of listener that is registered to listen on ActiveMQ Queue
     */
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