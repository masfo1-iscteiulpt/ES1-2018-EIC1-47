package gmail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.NoSuchProviderException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gui.BdaGUI;
import gui.OfflineMessage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitGmailTest {
	
	@Test
	public void failConnectionTest() throws IOException {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String connect = tmu.getConnectedStatus("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com",
				"CrokaNation1000");
		assertEquals("Could not connect to the message store", connect);
	}
	

	@Test
	public void htmlRemvTest() {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String htmlFree = tmu.htmlRemove("<div>olá Eu, sou Eu!!!");
		assertEquals(" olá Eu, sou Eu!!!", htmlFree);
	}

	@Test
	public void propertiesNotNull() {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		Properties properties = tmu.getServerProperties("imap", "imap.gmail.com", "993");
		assertNotNull("Propreties", properties);
		System.out.println("recive complete");
	}
	
	@Test
	public void mailTest() throws IOException {
			ArrayList<OfflineMessage> posts= new ArrayList<OfflineMessage>();
			BdaGUI frame = new BdaGUI();
			RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
			try {
				boolean test = tmu.getEmails("imap", "imap.gmail.com", "993", 
						"Trabalhosiscte12@gmail.com", "CrokaNation12", frame, posts);
				assertTrue(test);
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			}
		
	}


}
