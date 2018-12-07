package facebook;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import gui.BdaGUI;
import gui.Config;
import gui.OfflineMessage;

public class FacebookTest {
	
	String acessToken = "EAALKZAOc8jaMBAMDZBlW1g5JiWh0JZCRpXrGeo0AY5oDHxfvD3gFxZB42rI1m3mGxKsHVhVyjrZC8PdHPxVdksATGoUJZAtL6xVbyReLinqVgr3wvOyIvXZCEbTXad3q6YZCVOIKBp3THG3OADfqURHTmJhjwMnEBkozB3N3ZADZAkfm0eJYUyELZAi";
	String appID = "785484678466979";
	String appSecret = "98d490925815e4a3eb615da0d94a5983";
	
    @Test
    public void getExtendedAccessTokenTest() {
        Facebook ftu = new Facebook();
        boolean test = ftu.getExtendedAccessToken(acessToken, appID, appSecret);
        assertTrue(test);
    }

    @Test
    public void getFilterFacebookPosts() {
        Facebook ftu = new Facebook();
        BdaGUI frame = new BdaGUI(new Config());
        ArrayList<OfflineMessage> posts = new ArrayList<OfflineMessage>();
        boolean test=ftu.filterFacebookPost(acessToken, frame, posts);
        assertTrue(test);
        
    }
    
}



