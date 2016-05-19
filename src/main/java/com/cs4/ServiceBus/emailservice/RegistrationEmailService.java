
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

@Service("RegistrationEmailService")
public class RegistrationEmailService {

	private static final String IM_THE_GUY = "templates/images/imtheguy.jpg";

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

		// Need to use your own recipient here
		String recipientEmail = "sanjeevsthaus@gmail.com";
		String recipientName = "Corner Stone Technologies";
		return handle(recipientName, recipientEmail, appointment, null);

	}

	public MimeMessage handle(final String recipientName, final String recipientEmail, Appointment appointment,
			Locale locale) throws MessagingException {

		if (locale == null)
			locale = new Locale("en");

		// Prepare the evaluation context
		final Context thymeContext = new Context(locale);
		thymeContext.setVariable("name", recipientName);
		thymeContext.setVariable("appointment", appointment);

		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject("Appointment Details");

		message.setTo(recipientEmail);

		final String htmlContent = this.templateEngine.process("orderReceivedMail", thymeContext);
		message.setText(htmlContent, true /* isHtml */);

		return mimeMessage;
	}

}
