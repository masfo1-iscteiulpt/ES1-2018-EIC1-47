package Twitter;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter_Class {
	ConfigurationBuilder cbc;
	/**
	 * Initiates a Twitter_Class object
	 * @param ConfigurationBuilder
	 */
	public Twitter_Class(ConfigurationBuilder ConfigurationBuilder) {
		this.cbc=ConfigurationBuilder;
		
	}
	/**
	 * Initiates a Twitter account with the given tokens
	 * 
	 */
	public void sign_in(){
		cbc.setDebugEnabled(true)
  	  	.setOAuthConsumerKey("pRXsTFA4vsYjkjYIphXveKKgV")
  	  	.setOAuthConsumerSecret("CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU")
  	  	.setOAuthAccessToken("1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw")
  	  	.setOAuthAccessTokenSecret("jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
	}
	/**
	 * Prints the first 20 Tweets from the logged user
	 * 
	 */
	public void printTweets() {
		TwitterFactory tf = new TwitterFactory(cbc.build());
    	Twitter twitter = tf.getInstance();        		
        List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline();
			System.out.println("------------------------\n Showing home timeline \n------------------------");
			int counter=0;
			int counterTotal = 0;
			for (Status status : statuses) {
				if (status.getUser().getName() != null) {
					System.out.println(" ");
					System.out.println(status.getUser().getName() + ":" + status.getText() + " - " + status.getCreatedAt());
					System.out.println(" ");
					System.out.println("-------------------------------------------------------------------");
					counter++;
				}
				counterTotal++;
			}
			System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
