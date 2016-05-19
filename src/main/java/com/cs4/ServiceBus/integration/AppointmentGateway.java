package com.cs4.ServiceBus.integration;

import org.springframework.integration.annotation.Gateway;

import com.cs4.appointmentManagement.domain.Appointment;


public interface AppointmentGateway {
	
	/**
     * Process a Appointment.
     */
	@Gateway(requestChannel="processAppointment")
	public void process(Appointment appointment);
	
}