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
	 * @return destination for email
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination for email
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * constructor for Sender class
	 * 
	 * @param destination - destination for email
	 */
	public MailSender() {
	}

	private Properties getProperties() {
		System.out.println("Connecting please wait...");
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtps");
		properties.put("mail.smtps.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.port", SMTP_HOST_PORT);
		properties.put("mail.smtps.auth", "true");
		return properties;
	}

	/**
	 * @param sub  - subject for email
	 * @param cont - email content
	 * @return boolean for success state
	 * @throws Exception
	 */
	public boolean sendMessage(String sub, String cont) {
		boolean mail = false;
		Session session = Session.getInstance(getProperties());
		System.out.println("Session created");
		session.setDebug(true);

		try {
			Transport transport = session.getTransport();
			MimeMessage message = new MimeMessage(session);
			// e-mail subject
			message.setSubject(sub);
			// e-mail message
			message.setContent(cont, "text/plain");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(getDestination()));

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


}
