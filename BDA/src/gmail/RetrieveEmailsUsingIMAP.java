package gmail;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;


import enums.ServiceType;
import gui.BdaGUI;
import gui.MessagePanel;
import gui.OfflineMessage;

/**
 * This is a application to connect to Gmail server to retrieve received
 * e-mails.
 * 
 * @author Luis Fernandes
 * @version 1.2
 *
 */
public class RetrieveEmailsUsingIMAP {

	/**
	 * @param protocol imap
	 * @param host
	 * @param port
	 * @return
	 */
	protected static Properties getServerProperties(String protocol, String host, String port) {
		System.out.println("Connecting please wait...");
		Properties properties = new Properties();
		properties.put(String.format("mail.%s.host", protocol), host);
		properties.put(String.format("mail.%s.port", protocol), port);
		properties.setProperty(String.format("mail.%s.socketFactory.class", protocol),
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty(String.format("mail.%s.socketFactory.fallback",
				protocol), "false");
		properties.setProperty(String.format("mail.%s.socketFactory.port",
				protocol), String.valueOf(port));
		return properties;
	}

	/**
	 * this method prints e-mails located in user's Gmail INBOX
	 * 
	 * @param protocol imap
	 * @param host     imap.gmail.com
	 * @param port     port 993
	 * @param userName user's mail "****gmail.com"
	 * @param password user's password
	 * @param frame 
	 * @param posts 
	 * @throws IOException
	 */
	public boolean getEmails(String protocol, String host, String port, String userName, String password, BdaGUI frame, ArrayList<OfflineMessage> posts)
			throws IOException, NoSuchProviderException {

		System.out.println("Inside getEmails method...");
		boolean mail = false;
		Properties properties = getServerProperties(protocol, host, port);
		Session session = Session.getDefaultInstance(properties);
		System.out.println("Connected to: " + host);

		try {
			Store store = session.getStore(protocol);
			store.connect(userName, password);

			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);

			int count = inbox.getMessageCount();
			System.out.println("You have " + count + " emails in your inbox.");
			Message[] messages = inbox.getMessages(1, count);
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				Address[] fromAddresses = message.getFrom();
				String fromemail = fromAddresses[0].toString();
				String toemail = parseAddresses(message.getRecipients(RecipientType.TO));
				String ccemails = parseAddresses(message.getRecipients(RecipientType.CC));
				String subject = message.getSubject();
				String sentdate = message.getSentDate().toString();

				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();

					}

					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							// Do no overwrite existing file
							File file = new File(fileName);
							for (int j = 0; file.exists(); j++) {
								file = new File(fileName + j);
							}
							part.saveFile("D:/emailattach" + File.separator + fileName);
							messageContent = getText(message); // to get message body of attached emails
						} else {
							// this part for the message content
							messageContent = part.getContent().toString();
						}

					}
					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}		
				String mc = htmlRemove(messageContent);
				//mc += "\t To: " + toemail;
				//mc += "\t CC: " + ccemails;
				//mc += "\t subject: " + subject;
				//mc += "\n" + "\n"+ "Message: " + messageContent;
				mc += "\t Attachments: " + attachFiles;
				frame.addMessage(new MessagePanel(fromemail, mc, ServiceType.GM, message.getSentDate(), null));
				posts.add(new OfflineMessage(fromemail, mc, ServiceType.GM, message.getSentDate()));
			}

			inbox.close(false);
			mail = true;
			store.close();
		} catch (NoSuchProviderException ex) {
			mail = false;
			System.out.println("No provider for protocol: " + protocol);
			ex.printStackTrace();
		} catch (MessagingException ex) {
			mail = false;
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
		}
		return mail;
	}

	/**
	 * this method is use to remove html tags in the message text.
	 * 
	 * @param text message text content
	 * @return message text without html tags
	 */
	public String htmlRemove(String text) {
		String message = text.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
		return message;
	}

	/**
	 * @param address
	 * @return
	 */
	private String parseAddresses(Address[] address) {

		String listOfAddress = "";
		if ((address == null) || (address.length < 1))
			return null;
		if (!(address[0] instanceof InternetAddress))
			return null;

		for (int i = 0; i < address.length; i++) {
			InternetAddress internetAddress = (InternetAddress) address[0];
			listOfAddress += internetAddress.getAddress() + ",";
		}
		return listOfAddress;
	}

	/**
	 * This method is use to check whether user entered credentials are correct or
	 * not. If connection established then credentials are correct otherwise not.
	 * 
	 * @param protocol imap
	 * @param host     imap.gmail.com
	 * @param port     port 993
	 * @param userName user's gmail "****gmail.com"
	 * @param password user's password
	 * @return boolean value for connection status
	 */

	public String getConnectedStatus(String protocol, String host, String port, String userName, String password) {
		Properties properties = getServerProperties(protocol, host, port);
		Session session = Session.getDefaultInstance(properties);
		String isconnected = "";
		try {
			// ---- Start Connection Establishment----------
			Store store = session.getStore(protocol);
			System.out.println("Connecting to email....");
			store.connect(userName, password);
			isconnected = "Connected_to_IMAP";
			System.out.println("Is Connected: " + isconnected);
			System.out.println("Connected to :" + protocol);
		} catch (NoSuchProviderException ex) {
			String ex1 = "No provider for protocol: " + protocol;
			System.out.println(ex1);
			return ex1;
			// ex.printStackTrace();
		} catch (MessagingException ex) {
			String ex2 = "Could not connect to the message store";
			System.out.println(ex2);
			return ex2;
			// ex.printStackTrace();
		}
		return isconnected;
	}

	/**
	 * This method is use to handle MIME message. a message with an attachment is
	 * represented in MIME as a multipart message. In the simple case, the results
	 * of the Message object's getContent method will be a MimeMultipart object. The
	 * first body part of the multipart object will be the main text of the message.
	 * The other body parts will be attachments.
	 * 
	 * @param p message
	 * @return null
	 * @throws MessagingException
	 * @throws IOException
	 */

	public static String getText(Part p) throws MessagingException, IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}

}