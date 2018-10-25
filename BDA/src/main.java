import java.awt.EventQueue;

import gui.BdaGUI;
import twitter.Twitter_Class;
import twitter4j.conf.ConfigurationBuilder;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BdaGUI frame = new BdaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//twitter main xd
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			Twitter_Class tw = new Twitter_Class(cb);
			tw.sign_in();
			tw.printTweets();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
