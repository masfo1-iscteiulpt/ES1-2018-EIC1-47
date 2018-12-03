package gmail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
import javax.activation.*;

/**
 * This is a application to connect to Gmail server to retrieve received
 * e-mails.
 * 
 * @author Luis Fernandes
 * @version 1.0
 *
 */

public class MailSender {
	/**
	 * SMTP_HOST_NAME - protocol
	 */
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	/**
	 * SMTP_HOST_PORT - Port for app
	 */
	private static final int SMTP_HOST_PORT = 465;
	/**
	 * SMTP_AUTH_USER - user's email "***@gmail.com"
	 */
	private static final String SMTP_AUTH_USER = "Trabalhosiscte12@gmail.com";
	/**
	 * SMTP_AUTH_PWD - user's password
	 */
	private static final String SMTP_AUTH_PWD = "CrokaNation12";
	/**
	 * destination - destination for email
	 */
	private String destination;

	/**
	 * @param destination - destination for email
	 */
	public MailSender(String destination) {
		this.destination = destination;
	}

	/**
	 * @param sub  - subject for email
	 * @param cont - email content
	 * @return boolean for success state
	 * @throws Exception
	 */
	public boolean sendMessage(String sub, String cont) {
		Properties props = new Properties();
		boolean mail = false;
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", SMTP_HOST_NAME);
		props.put("mail.smtps.auth", "true");

		Session mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
		Transport transport;

		try {
			transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			// e-mail subject
			message.setSubject(sub);
			// e-mail message
			message.setContent(cont, "text/plain");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.destination));

			transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			System.out.println("Message Sent!");
			mail = true;
			transport.close();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return mail;

	}

	public static void main(String[] args) throws Exception {
		MailSender sender = new MailSender("Trabalhosiscte12@gmail.com");
		boolean test = sender.sendMessage("Testing SMTP-SSL", "This is a test");
		if (test) {
			System.out.println("Sending sucess");
		}
	}

}
