package com.cs4.ServiceBus.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.cs4.appointmentManagement.domain.Appointment;

/**
 * @author sanjeev
 *
 */
@MessageEndpoint
public class AppointmentRouter {

    /**
     * 
     */
    final Logger logger = LoggerFactory.getLogger(AppointmentRouter.class);
    
 
	/**
	 * @param appointment
	 * @return
	 */
	@Router(inputChannel="processAppointment")
	public String processAppointment(Appointment appointment) {
	    String destination = "appointmentMailer";
	    

		return destination;
	}

}
