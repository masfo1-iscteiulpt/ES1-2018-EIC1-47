package gmail;

import java.io.IOException;

import javax.mail.NoSuchProviderException;

import gui.BdaGUI;
import gui.Config;

public class GmailMain {

	public static void main(String[] args) {
		
		//receiver
		BdaGUI frame = new BdaGUI(new Config());
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		try {
			boolean mail = tmu.getEmails("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com", "CrokaNation12",
					frame, null);
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
		
		
		MailSender sender = new MailSender("Trabalhosiscte12@gmail.com", "Trabalhosiscte12@gmail.com", "CrokaNation12");
		boolean test = sender.sendMessage("Testing SMTP-SSL", "This is a test");
		if (test) {
			System.out.println("Sending sucess");
		}
	}

}
