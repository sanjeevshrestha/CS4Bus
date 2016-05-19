
package com.cs4.ServiceBus.emailservice;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.cs4.appointmentManagement.domain.Appointment;
import com.cs4.appointmentManagement.domain.Doctor;
import com.cs4.appointmentManagement.domain.Patient;

@Service("AppointmentEmailService")
public class AppointmentEmailService {


	private static final String JPG_MIME = "image/jpg";
	private static final String DOCX_MIME = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

	/*
	 * Send HTML mail (simple)
	 */
	@ServiceActivator()
	public MimeMessage handle(Appointment appointment) throws MessagingException {

		Patient patient=new Patient();
		patient.setEmail("sanjeevsthaus@gmail.com");
		patient.setFirstName("Sanjeev");
		
		String recipientEmail = patient.getEmail();
		String recipientName = patient.getFirstName();
		return handle(recipientName, recipientEmail, appointment, null);

	}

	public MimeMessage handle(final String recipientName, final String recipientEmail, Appointment appointment,
			Locale locale) throws MessagingException {
		
		Doctor doctor=new Doctor();
		doctor.setFirstName("Sanjeev");
		doctor.setLastName("Shrestha");
		
		Patient patient=new Patient();
		patient.setLastName("Shrestha");
		patient.setFirstName("Rajiv");
		
		

		if (locale == null)
			locale = new Locale("en");

		// Prepare the evaluation context
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("appointment", appointment);
		System.out.println(appointment.getDoctor());
		thymeContext.setVariable("doctor",doctor);
		thymeContext.setVariable("patient",patient);


		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("You have an appointment");

		message.setTo(recipientEmail);

		final String htmlContent = this.templateEngine.process("appointmentCreated", thymeContext);
		message.setText(htmlContent, true /* isHtml */);

		System.out.println("Sending Appointment Email to "+recipientName);
		return mimeMessage;
	}

}
