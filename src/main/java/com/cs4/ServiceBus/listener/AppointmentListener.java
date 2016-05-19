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


public class AppointmentListener implements MessageListener {

	@Autowired
	private ApplicationContext applicationContext;
	private static final Logger logger = LoggerFactory.getLogger(AppointmentListener.class);

	public void onMessage(Message message) {
		System.out.println("Received Appointment Message");

		Appointment appointment = null;
		AppointmentGateway appointmentGateway;

		try {

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