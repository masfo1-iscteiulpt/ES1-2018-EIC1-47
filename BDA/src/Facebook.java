import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {

	/**
	 * 
	 * Description: Permite saber o user que possui aquele Access Token
	 * @author masfo1-iscteiulpt
	 * 
	 * 	 
	 */
	public void getUser() {
		String accessToken2 = "EAACec3COZB6ABAPQOD6ZC37zJdEXqkU2jnPoeZA4vUBdBlV7RzFuGbn0WrpjdfaslqZCxjcZAbRE9tGHMWpVWAWSDt589sXHwMdvxLTLPSow5QVvL9HgA7R3r0o9A6F8Ex3tkAsdKMuSZC5DdMfq8Ejj9B5ZAoCEy6ZB9LZBR7NOnYjaJB1ZBbsZCskBuZBDBHKpZCZChPBEJ0FhKqRZC12qWxaXvGAdp7y49hFmIsZD";
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());
	}
	
	/**
	 * 
	 * Description: Permite saber a validade do Extended Access Token
	 * 	 
	 */
	public void getExtendedAccessToken() {
		String accessToken4 = "EAACec3COZB6ABAPQOD6ZC37zJdEXqkU2jnPoeZA4vUBdBlV7RzFuGbn0WrpjdfaslqZCxjcZAbRE9tGHMWpVWAWSDt589sXHwMdvxLTLPSow5QVvL9HgA7R3r0o9A6F8Ex3tkAsdKMuSZC5DdMfq8Ejj9B5ZAoCEy6ZB9LZBR7NOnYjaJB1ZBbsZCskBuZBDBHKpZCZChPBEJ0FhKqRZC12qWxaXvGAdp7y49hFmIsZD";
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("490458541421089",
				"b4b0922f88578643b1c3795ed4cde605");
		System.out.println("ExtendedAccessToken: " + extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());
	}

	/**
	 * 
	 * Description: 
	 * -Permite filtrar os post que o user que recebe usando aquele Access Token
	 * -E permite saber quantos são
	 * -Apresenta resultados na consola
	 * 	 
	 */
	public void filterFacebookPost() {
		// "EAACec3COZB6ABAPQOD6ZC37zJdEXqkU2jnPoeZA4vUBdBlV7RzFuGbn0WrpjdfaslqZCxjcZAbRE9tGHMWpVWAWSDt589sXHwMdvxLTLPSow5QVvL9HgA7R3r0o9A6F8Ex3tkAsdKMuSZC5DdMfq8Ejj9B5ZAoCEy6ZB9LZBR7NOnYjaJB1ZBbsZCskBuZBDBHKpZCZChPBEJ0FhKqRZC12qWxaXvGAdp7y49hFmIsZD
		String accessToken5 = "EAACec3COZB6ABAPQOD6ZC37zJdEXqkU2jnPoeZA4vUBdBlV7RzFuGbn0WrpjdfaslqZCxjcZAbRE9tGHMWpVWAWSDt589sXHwMdvxLTLPSow5QVvL9HgA7R3r0o9A6F8Ex3tkAsdKMuSZC5DdMfq8Ejj9B5ZAoCEy6ZB9LZBR7NOnYjaJB1ZBbsZCskBuZBDBHKpZCZChPBEJ0FhKqRZC12qWxaXvGAdp7y49hFmIsZD";
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed", Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				if (aPost.getMessage() != null && aPost.getMessage().contains("Inform")) {
					System.out.println("---- Post " + counter5 + " ----");
					System.out.println("Id: " + "fb.com/" + aPost.getId());
					System.out.println("Message: " + aPost.getMessage());
					System.out.println("Created: " + aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5 + "/" + counterTotal);
	}
	

	
	
}
