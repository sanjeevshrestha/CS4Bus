package com.cs4.ServiceBus.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.cs4.ServiceBus.integration.AppointmentGateway;
import com.cs4.appointmentManagement.domain.Appointment;


/**
 * Listener that listens to the JMS channel to receive Appointment data. 
 * This then pushes the data through the gateway to the bus for other components to process
 * 
 * @author sanjeev
 *
 */
public class AppointmentListener implements MessageListener {

	/**
	 * DI ApplicationContext
	 */
	@Autowired
	private ApplicationContext applicationContext;
	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(AppointmentListener.class);

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 * onMessage method of listener that is registered to listen on ActiveMQ Queue
	 */
	public void onMessage(Message message) {
		System.out.println("Received Appointment Message");

		Appointment appointment = null;
		AppointmentGateway appointmentGateway;

		try {

			/**
			 * Check if the data is Object
			 */
			if (message instanceof ActiveMQTextMessage) {

				ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
				System.out.println("Received Text instead of Appointment Object...");
				System.out.println(amqMessage.getText());
			} else {
				ObjectMessage objectMessage = (ObjectMessage) message;
				appointment = (Appointment) objectMessage.getObject();
				appointmentGateway = (AppointmentGateway) applicationContext.getBean("appointment");
				appointmentGateway.process(appointment);

				System.out.println(appointment.getDescription());

			}

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Appointment Message received");

	}
}