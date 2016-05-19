package com.cs4.ServiceBus.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.cs4.appointmentManagement.domain.User;

@MessageEndpoint
public class RegistrationRouter {

    final Logger logger = LoggerFactory.getLogger(RegistrationRouter.class);
    
 
	@Router(inputChannel="processRegisration")
	public String processRegisration(User user) {
	    String destination = "registrationMailer";
	    
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
