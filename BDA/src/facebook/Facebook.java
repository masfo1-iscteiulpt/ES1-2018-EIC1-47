package facebook;



import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;

import enums.ServiceType;
import gui.BdaGUI;
import gui.MessagePanel;
import gui.OfflineMessage;

public class Facebook {

	/*Token de Acesso conta do Facebook */
	private String token = "EAALKZAOc8jaMBAMDZBlW1g5JiWh0JZCRpXrGeo0AY5oDHxfvD3gFxZB42rI1m3mGxKsHVhVyjrZC8PdHPxVdksATGoUJZAtL6xVbyReLinqVgr3wvOyIvXZCEbTXad3q6YZCVOIKBp3THG3OADfqURHTmJhjwMnEBkozB3N3ZADZAkfm0eJYUyELZAi";
	private boolean test = false;

	/**
	 * 
	 * Description: Permite saber o user que possui aquele Access Token
	 * @author masfo1-iscteiulpt
	 * 
	 * 	 
	 */
	public boolean getUser() {
		String accessToken2 = token;
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());
		return true;
	}
	
	/**
	 * 
	 * Description: Permite saber a validade do Extended Access Token
	 * @param string2 
	 * @param string 
	 * @param string3 
	 * 	 
	 */
	public boolean getExtendedAccessToken(String AcessToken, String AppId, String AppSecret) {
		String accessToken4 = AcessToken;
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken(AppId, AppSecret);
		System.out.println("ExtendedAccessToken: " + extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());
		return true;
	}

	/**
	 * 
	 * Description: 
	 * -Permite filtrar os post que o user que recebe usando aquele Access Token
	 * -E permite saber quantos são
	 * -Apresenta resultados na consola
	 * @param AcessToken 
	 * @param frame 
	 * @param posts 
	 * 	 
	 */
	public boolean filterFacebookPost(String AcessToken, BdaGUI frame, ArrayList<OfflineMessage> posts) {
		String accessToken5 = AcessToken;
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed", Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
					System.out.println("---- Post " + counter5 + " ----");
					System.out.println("Id: " + "fb.com/" + aPost.getId());
					String mc = aPost.getMessage();
					mc += "\n" + "Caption: " + aPost.getDescription();
					mc += "\n" + "Created: " + aPost.getCreatedTime();
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					counter5++;
					
					frame.addMessage(new MessagePanel("", mc, ServiceType.FB, aPost.getCreatedTime(), null));
					posts.add(new OfflineMessage("", mc, ServiceType.FB, aPost.getCreatedTime()));
				counterTotal++;
			}
		}
		return true;
	}
	

}