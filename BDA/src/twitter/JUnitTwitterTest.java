package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gui.BdaGUI;
import gui.Config;
import gui.OfflineMessage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class JUnitTwitterTest {

	@Test
	public void sign_inTest() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Twitter_Class tw = new Twitter_Class(cb);
		boolean test = tw.sign_in("pRXsTFA4vsYjkjYIphXveKKgV", "CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU",
				"1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw", "jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
		assertTrue(test);
	}

	@Test
	public void printTweetsTest() {
		BdaGUI frame = new BdaGUI(new Config());
		ArrayList<OfflineMessage> posts = new ArrayList<OfflineMessage>();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Twitter_Class tw = new Twitter_Class(cb);
		tw.sign_in("pRXsTFA4vsYjkjYIphXveKKgV", "CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU",
				"1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw", "jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
		tw.printTweets(frame, posts);
	}

	@Test
	public void reTweetTest() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Twitter_Class tw = new Twitter_Class(cb);
		tw.sign_in("pRXsTFA4vsYjkjYIphXveKKgV", "CfNV7ZgEwN2hhysP8kAGmy6hWMF7mypqX6CPtRwDy7rQ63Z5rU",
				"1053263399889653761-Eu236nomWzitNbXoEcKsOvQVCOchZw", "jKSOccpi450YERoIGJeP9qPQLRJbGTrEZtKoG4IlRDuip");
		tw.getCbc();
		tw.getTf();
		List<Status> statuses = tw.getTwitter().getHomeTimeline();
		boolean test = tw.reTweet(statuses.get(0));
		assertTrue(test);

	}
}
