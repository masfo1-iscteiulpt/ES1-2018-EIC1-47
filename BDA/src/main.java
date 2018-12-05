import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.mail.NoSuchProviderException;
import twitter4j.conf.ConfigurationBuilder;
import facebook.Facebook;
import gmail.RetrieveEmailsUsingIMAP;
import gui.BdaGUI;
import gui.MessagePanel;
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
		Socket s= new Socket();
		InetSocketAddress addr=new InetSocketAddress("www.google.com",80);
		try{
		s.connect(addr,3000);
		startServices();
		} catch(Exception e){
			offlineServices();
		} finally {
			try{s.close();}
			catch(Exception e){}
		}
	}

	public static void startServices() {
		ArrayList<MessagePanel> posts= new ArrayList<MessagePanel>();
		// tw init
			ConfigurationBuilder cb = new ConfigurationBuilder();
			Twitter_Class tw = new Twitter_Class(cb);
			tw.sign_in("pRXsTFA4vsYjkjYIphXveKKgV", "CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU","1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw", "jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
			tw.printTweets(frame, posts);
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
		
			FileOutputStream f;
			try {
				f = new FileOutputStream("file.txt");
				ObjectOutputStream s = new ObjectOutputStream(f);
				s.writeObject(posts);
				s.close();
				System.out.println("File Saved");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void offlineServices() {
		
	}
	
}
