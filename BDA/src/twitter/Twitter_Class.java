package twitter;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JLabel;

import enums.ServiceType;
import gui.BdaGUI;
import gui.MessagePanel;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * This is a application to connect to Twitter server to retrieve received
 * tweets.
 * 
 * @author Hugo Croca
 * @version 1.0
 *
 */
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
	public void sign_in(String cK, String cS, String aT, String aTS){
		cbc.setDebugEnabled(true)
  	  	.setOAuthConsumerKey(cK)
  	  	.setOAuthConsumerSecret(cS)
  	  	.setOAuthAccessToken(aT)
  	  	.setOAuthAccessTokenSecret(aTS);
	}
	/**
	 * Prints the first 20 Tweets from the logged user
	 * @param frame 
	 * 
	 */
	
	public void printTweets(BdaGUI frame) {
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
					frame.addMessage(new MessagePanel(status.getUser().getName(), status.getText(), ServiceType.TW, status.getCreatedAt()));
				}
				counterTotal++;
			}
			System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Retweets a tweet of the given ID
	 *  @param TweetId
	 * 
	 */
	private void test(){
		System.out.println("OLA");
	}
	private void reTweet(long id) {
		TwitterFactory tf = new TwitterFactory(cbc.build());
    	Twitter twitter = tf.getInstance();        		
    	try {
			twitter.retweetStatus(id);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
