package Twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class Twitter_main  {
	public static void main(String[] args) {
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	Twitter_Class tw = new Twitter_Class(cb);
        	tw.sign_in();
        	tw.printTweets();
        } catch (Exception e) { System.out.println(e.getMessage()); }
     }
}
    