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
			ConfigurationBuilder cb = new ConfigurationBuilder();
			Twitter_Class tw = new Twitter_Class(cb);
			tw.sign_in("pRXsTFA4vsYjkjYIphXveKKgV", "CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU","1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw", "jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
			tw.printTweets(frame);
		// gmail init
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		try {
			tmu.getEmails("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com", "CrokaNation12", frame);
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
