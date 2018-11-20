package facebook;

import java.awt.Color;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

import gui.BdaGUI;
import gui.MessagePanel;
import gui.ServiceType;

public class Facebook {

	private String token = "EAALKZAOc8jaMBAAsUVjACUZA7gMMkciI8ZBc0LoEZCPlmTwIJAhMY4dxleLJjdLuDh56VTXWr5576tZBmm7PCVBb68jGy9Yq9ZBUPWQNprbhEgUWZCvFaA8K5Tidbhf55O4RLxD5K7O3BzQYIFvQqZAMtwcqw2mrRHXVK9IkkCqU7jiCk92LLZAhO2uEjtP4NwfvmnKZCfoxZCmnakZB2qV73NbT0ebbGnWyTNQZD";

	/**
	 * 
	 * Description: Permite saber o user que possui aquele Access Token
	 * @author masfo1-iscteiulpt
	 * 
	 * 	 
	 */
	public String getUser() {
		String accessToken2 = token;
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());
		return me2.getId() + " " + me2.getName();
	}
	
	/**
	 * 
	 * Description: Permite saber a validade do Extended Access Token
	 * 	 
	 */
	public String getExtendedAccessToken() {
		String accessToken4 = token;
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("785484678466979", "98d490925815e4a3eb615da0d94a5983");
		System.out.println("ExtendedAccessToken: " + extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());
		
		return " " + extendedAccessToken4.getExpires().toString();
	}

	/**
	 * 
	 * Description: 
	 * -Permite filtrar os post que o user que recebe usando aquele Access Token
	 * -E permite saber quantos são
	 * -Apresenta resultados na consola
	 * @param frame 
	 * 	 
	 */
	public void filterFacebookPost(BdaGUI frame) {
		String accessToken5 = token;
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed", Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				//if (aPost.getMessage() != null && aPost.getMessage().contains("PROJETO SE")) {
					System.out.println("---- Post " + counter5 + " ----");
					System.out.println("Id: " + "fb.com/" + aPost.getId());
					String mc = "Message: " + aPost.getMessage();
					mc += "\n" + "Caption: " + aPost.getDescription();
					mc += "\n" + "Created: " + aPost.getCreatedTime();
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					counter5++;
					
					frame.addMessage(new MessagePanel(mc, ServiceType.FB));
				//}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5 + "/" + counterTotal);
	}
	

	
	
}
