
package com.cs4.ServiceBus.emailservice;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.cs4.appointmentManagement.domain.Appointment;
import com.cs4.appointmentManagement.domain.Patient;

/**
 * Email adapter to process Appointment notifications. This is integrated in the bus. 
 * Uses thymeleaf to parse email template. Uses the template "appointmentCreated.html".
 * Uses Java Mail to send email.
 * @author sanjeev
 *
 */


@Service("AppointmentEmailService")
public class AppointmentEmailService {

	
	/**
	 * DI JavaMailSender
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * DI Spring Template Engine
	 */
	@Autowired
	private SpringTemplateEngine templateEngine;

	/**
	 * Send HTML mail (simple)
	 * Returns MimeMessage that is used by chain
	 * 
	 * @param appointment
	 * @return MimeMessage
	 * @throws MessagingException
	 */
	@ServiceActivator()
	public MimeMessage handle(Appointment appointment) throws MessagingException {

		Patient patient = appointment.getPatient();

		String recipientEmail = patient.getEmail();
		String recipientName = patient.getFirstName();
		return handle(recipientName, recipientEmail, appointment, null);

	}

	
	/**
	 * Sends HTML mail(simple). 
	 * Returns MimeMessage that is used by chain in the bus
	 * @param recipientName
	 * @param recipientEmail
	 * @param appointment
	 * @param locale
	 * @return
	 * @throws MessagingException
	 */
	public MimeMessage handle(final String recipientName, final String recipientEmail, Appointment appointment,
			Locale locale) throws MessagingException {

		if (locale == null)
			locale = new Locale("en");

		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("appointment", appointment);
		thymeContext.setVariable("doctor", appointment.getDoctor());
		thymeContext.setVariable("patient", appointment.getPatient());

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("You have an appointment");

		message.setTo(recipientEmail);

		final String htmlContent = this.templateEngine.process("appointmentCreated", thymeContext);
		message.setText(htmlContent, true /* isHtml */);
		
		/**
		 * Logs the email information on the console
		 */

		System.out.println("**** Patient Info *****");
		System.out.println("------------------------");
		System.out.println(
				"Name : " + appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
		System.out.println("Description : " + appointment.getDescription());
		System.out.println("------------------------");

		System.out.println("**** Doctor Info *****");
		System.out.println("------------------------");
		System.out.println(
				"Name : " + appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName());
		System.out.println("------------------------");

		System.out.println("**** Appointment Info *****");
		System.out.println("------------------------");
		System.out.println("Date Time: " + appointment.getDateTime());
		System.out.println("------------------------");

		System.out.println("Sending Appointment Email to " + recipientName + " at " + recipientEmail);
		return mimeMessage;
	}

}
