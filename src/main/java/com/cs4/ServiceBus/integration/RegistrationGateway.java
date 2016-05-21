package com.cs4.ServiceBus.integration;

import org.springframework.integration.annotation.Gateway;

import com.cs4.appointmentManagement.domain.User;

/**
 * @author sanjeev
 *
 */
public interface RegistrationGateway {
	/**
     * Process a Appointment.
     */
	/**
	 * @param user
	 */
	@Gateway(requestChannel="processRegistration")
	public void process(User user);
}