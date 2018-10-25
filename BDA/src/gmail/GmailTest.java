package gmail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GmailTest {

	@Test
	void connectionTest() throws IOException {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String connect = tmu.getConnectedStatus("imap", "imap.gmail.com", "993"
				, "Trabalhosiscte12@gmail.com", "CrokaNation12");
		assertEquals("Connected_to_IMAP", connect );
	}
	
	@Test
	void failConnectionTest() throws IOException {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String connect = tmu.getConnectedStatus("imap", "imap.gmail.com", "993"
				, "Trabalhosiscte12@gmail.com", "CrokaNation1000");
		assertEquals("Could not connect to the message store", connect );
	}
	
	@Test
	void htmlRemvTest() {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String htmlFree = tmu.htmlRemove("<div>olá Eu, sou Eu!!!");
		assertEquals(" olá Eu, sou Eu!!!", htmlFree);
	}

}
