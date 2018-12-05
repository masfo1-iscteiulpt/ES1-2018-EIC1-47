package gmail;

import java.io.IOException;

import javax.mail.NoSuchProviderException;

import gui.BdaGUI;

public class GmailMain {

	public static void main(String[] args) {
		
		//receiver

		BdaGUI frame = new BdaGUI();
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		try {
			boolean mail = tmu.getEmails("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com", "CrokaNation12",
					frame);
			if (mail == true) {
				System.out.println("Gmail sucess");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Sender
		
		
		MailSender sender = new MailSender();
		sender.setDestination("Trabalhosiscte12@gmail.com");
		boolean test = sender.sendMessage("Testing SMTP-SSL", "This is a test");
		if (test) {
			System.out.println("Sending sucess");
		}
	}

}
