package facebook;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import gui.BdaGUI;

class FacebookTest {

	private BdaGUI frame;
	
	@Test
	void getUsertest() {
		Facebook ftu = new Facebook();
		assertEquals("1193488397483337 Hugo Cruz", ftu.getUser());
	}
	
	@Test
	void getExtendedAccessTokenTest() {
		Facebook ftu = new Facebook();
		assertEquals(" Fri Feb 01 13:16:07 GMT 2019", ftu.getExtendedAccessToken());
	}

	@Test
	void getFilterFacebookPosts() {
		Facebook ftu = new Facebook();
		assertTrue(ftu.filterFacebookPost(frame));
	}
	

}
