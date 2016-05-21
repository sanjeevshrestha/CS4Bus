package com.cs4.ServiceBus.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.cs4.appointmentManagement.domain.User;

/**
 * @author sanjeev
 *
 */
@MessageEndpoint
public class RegistrationRouter {

    /**
     * 
     */
    final Logger logger = LoggerFactory.getLogger(RegistrationRouter.class);
    
 
	/**
	 * @param user
	 * @return
	 */
	@Router(inputChannel="processRegisration")
	public String processRegisration(User user) {
	    String destination = "registrationMailer";
	    

	
		return destination;
	}

}
