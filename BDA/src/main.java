import java.awt.EventQueue;
import java.io.IOException;

import facebook.Facebook;
import gmail.RetrieveEmailsUsingIMAP;
import gui.BdaGUI;
import twitter.Twitter_Class;
import twitter4j.conf.ConfigurationBuilder;

public class main {

	public static BdaGUI frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new BdaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// tw init
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			Twitter_Class tw = new Twitter_Class(cb);
			tw.sign_in();
			tw.printTweets(frame);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// gmail init
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		try {
			tmu.getEmails("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com", "CrokaNation12", frame);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fb init
		Facebook fb = new Facebook();
		fb.getUser();
		fb.getExtendedAccessToken();
		fb.filterFacebookPost();
	}

}