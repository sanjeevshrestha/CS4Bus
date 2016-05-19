package com.cs4.ServiceBus.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.cs4.appointmentManagement.domain.Appointment;

@MessageEndpoint
public class AppointmentRouter {

    final Logger logger = LoggerFactory.getLogger(AppointmentRouter.class);
    
 
	@Router(inputChannel="processAppointment")
	public String processAppointment(Appointment appointment) {
	    String destination = "appointmentMailer";
	    
//  	    switch (appointment.getStatus()) {
//	        case DELIVERY:
//	        	destination = "deliveryOrder";
//	            break;
//            case PICKUP:
//            	destination = "pickupProcess";
//                break;	            
//	    }
	
		return destination;
	}

}
