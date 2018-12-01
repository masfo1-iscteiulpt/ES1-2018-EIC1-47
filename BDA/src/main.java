import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.mail.NoSuchProviderException;
import twitter4j.conf.ConfigurationBuilder;
import facebook.Facebook;
import gmail.RetrieveEmailsUsingIMAP;
import gui.BdaGUI;
import twitter.Twitter_Class;

public class main {

	public static BdaGUI frame;

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			public void run() {
				try {
					frame = new BdaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		startServices();
	}

	public static void startServices() {
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
			boolean mail = tmu.getEmails("imap", "imap.gmail.com", "993", 
					"Trabalhosiscte12@gmail.com", "CrokaNation12", frame);
			if(mail == true) {
				System.out.println("Gmail sucess");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// fb init
		Facebook fb = new Facebook();
		try {
			fb.getUser();
			fb.getExtendedAccessToken();
			fb.filterFacebookPost(frame);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
