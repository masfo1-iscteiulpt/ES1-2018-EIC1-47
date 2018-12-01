package gmail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Properties;
import javax.mail.NoSuchProviderException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gui.BdaGUI;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitGmailTest {
	

	@Test
	public void afailConnectionTest() throws IOException {
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
	}

	@Test
	public void zfailMailTest() throws IOException {
		try {
			BdaGUI frame = new BdaGUI();
			RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
			boolean test = tmu.getEmails("not-imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com", "CrokaNation12", frame);
		} catch (NoSuchProviderException ex) {
			assertEquals("No provider for not-imap", ex.getMessage());
		}
	}
	
	@Test
	public void connectTest() throws IOException {
		RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
		String connect = tmu.getConnectedStatus("imap", "imap.gmail.com", "993", "Trabalhosiscte12@gmail.com",
				"CrokaNation12");
		assertEquals("Connected_to_IMAP", connect);
	}
	
	@Test
	public void mailTest() throws IOException {
		
			BdaGUI frame = new BdaGUI();
			RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
			try {
				boolean test = tmu.getEmails("imap", "imap.gmail.com", "993", 
						"Trabalhosiscte12@gmail.com", "CrokaNation12", frame);
				assertTrue(test);
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
