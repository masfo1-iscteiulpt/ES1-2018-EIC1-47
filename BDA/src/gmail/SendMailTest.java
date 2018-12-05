package gmail;

import static org.junit.Assert.*;

import org.junit.Test;

public class SendMailTest {

	@Test
	public void test() {
		MailSender sender = new MailSender();
		sender.setDestination("Trabalhosiscte12@mail.com");
		boolean test = sender.sendMessage("Testing SMTP-SSL", "This is a test");
		assertTrue(test);

	}

}
