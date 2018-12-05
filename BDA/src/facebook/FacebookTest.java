package facebook;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

class FacebookTest {

	@Test
	void getUsertest() {
		Facebook ftu = new Facebook();
		assertEquals("115472076112743 Rui Santos Esteves", ftu.getUser());
	}
	
	@Test
	void getExtendedAccessTokenTest() {
		Facebook ftu = new Facebook();
		assertEquals(" Mon Dec 24 21:23:22 GMT 2018", ftu.getExtendedAccessToken());
	}

	

}
