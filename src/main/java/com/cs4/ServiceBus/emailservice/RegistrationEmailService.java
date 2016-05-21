
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

import com.cs4.appointmentManagement.domain.User;

@Service("RegistrationEmailService")
public class RegistrationEmailService {


	/**
	 * Email adapter to process Registration notifications. This is integrated in the bus. 
	 * Uses thymeleaf to parse email template. Uses the template "registration.html".
	 * Uses Java Mail to send email.
	 * @author sanjeev
	 *
	 */

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
	public MimeMessage handle(User user) throws MessagingException {

		// Need to use your own recipient here
		String recipientEmail = user.getEmail();
		String recipientName = user.getFname();
		return handle(recipientName, recipientEmail, user, null);

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
	public MimeMessage handle(final String recipientName, final String recipientEmail, User user,
			Locale locale) throws MessagingException {

		if (locale == null)
			locale = new Locale("en");

		// Prepare the evaluation context
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("user", user);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Welcome to Appointment Manager");

		message.setTo(recipientEmail);

		final String htmlContent = this.templateEngine.process("registration", thymeContext);
		message.setText(htmlContent, true /* isHtml */);

		return mimeMessage;
	}

}
