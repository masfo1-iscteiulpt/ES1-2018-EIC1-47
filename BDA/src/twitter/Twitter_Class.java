package twitter;


import java.util.ArrayList;
import java.util.List;

import enums.ServiceType;
import gui.BdaGUI;
import gui.MessagePanel;
import gui.OfflineMessage;
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
	private TwitterFactory tf;
	private Twitter twitter;
	/**
	 * Initiates a Twitter_Class object
	 * @param ConfigurationBuilder
	 */
	public Twitter_Class(ConfigurationBuilder ConfigurationBuilder) {
		this.cbc=ConfigurationBuilder;
		
	}
	/**
	 * Initiates a Twitter account with the given tokens
	 * @param acess consumer key
	 * @param Acess consumer secret
	 * @param Acess token
	 * @paramAcesss token secret
	 * 
	 */
	public boolean sign_in(String ack, String acs, String aat, String aats){
		cbc.setDebugEnabled(true)
  	  	.setOAuthConsumerKey(ack)
  	  	.setOAuthConsumerSecret(acs)
  	  	.setOAuthAccessToken(aat)
  	  	.setOAuthAccessTokenSecret(aats);
		tf = new TwitterFactory(cbc.build());
    	twitter = tf.getInstance();  
		return true;
	}
	/**
	 * Prints the first 20 Tweets from the logged user
	 * @param frame 
	 * @param posts 
	 * 
	 */
	
	public void printTweets(BdaGUI frame, ArrayList<OfflineMessage> posts) {      		
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
					frame.addMessage(new MessagePanel(status.getUser().getName(), status.getText(), ServiceType.TW, status.getCreatedAt(), status));
					posts.add(new OfflineMessage(status.getUser().getName(), status.getText(), ServiceType.TW, status.getCreatedAt()));
				}
				counterTotal++;
			}
			System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
			} catch (TwitterException e) {
				e.printStackTrace();
				System.out.println("twitter service unavailable");
			}
		
	}
	/**
	 * 
	 * @return ConfigurationBuilder
	 */
	public ConfigurationBuilder getCbc() {
		return cbc;
	}
	/**
	 * 
	 * @return TwitterFactory
	 */
	public TwitterFactory getTf() {
		return tf;
	}
	/**
	 * 
	 * @return Twitter
	 */
	public Twitter getTwitter() {
		return twitter;
	}
	/**
	 * Retweets a tweet of the given Status
	 *  @param Status
	 * 
	 */
	public boolean reTweet(Status status) {
        if(status.isRetweetedByMe() && status.getRetweetCount() >= 1 ){
        	System.out.println("Já foi Retweeted");	
        	return true; // this twit retweeted by your and others
        } 
		else{
    		try {
    			twitter.retweetStatus(status.getId());
    			return true;
    		} catch (TwitterException e) {
    			e.printStackTrace();
    			return false;
    		}
    		}
	}
	
}
