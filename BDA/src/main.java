import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.mail.NoSuchProviderException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import twitter4j.conf.ConfigurationBuilder;
import facebook.Facebook;
import gmail.RetrieveEmailsUsingIMAP;
import gui.BdaGUI;
import gui.Config;
import gui.MessagePanel;
import gui.OfflineMessage;
import twitter.Twitter_Class;

public class main {

	public static BdaGUI frame;
	public static boolean netConection = false;
	public static Config configuration;

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		try {
			configuration = new Config();
			loadSettings(configuration);
		} catch (ParserConfigurationException | SAXException | TransformerFactoryConfigurationError
				| TransformerException | XPathExpressionException e) {
			e.printStackTrace();
		}
		EventQueue.invokeAndWait(new Runnable() {
			public void run() {
				try {
					frame = new BdaGUI(configuration);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Socket s = new Socket();
		InetSocketAddress addr = new InetSocketAddress("www.google.com", 80);
		try {
			s.connect(addr, 3000);
			netConection = true;
		} catch (Exception e) {

		} finally {
			try {
				s.close();
			} catch (Exception e) {
			}
		}
		if (netConection) {
			startServices(configuration);
		} else {
			offlineServices();
		}
	}

	public static void startServices(Config config) {
		ArrayList<OfflineMessage> posts = new ArrayList<OfflineMessage>();

		// tw init
		if (config.getTwAAT() != null) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			Twitter_Class tw = new Twitter_Class(cb);
			tw.sign_in(config.getTwACK(), config.getTwACS(), config.getTwAAT(), config.getTwAATS());
			tw.printTweets(frame, posts);
		}

		// gmail init
		if (config.getGmailMail() != null) {
			RetrieveEmailsUsingIMAP tmu = new RetrieveEmailsUsingIMAP();
			try {
				tmu.getEmails(config.getGmailProtocol(), "imap.gmail.com", "993", config.getGmailMail(),
						config.getGmailPassword(), frame, posts);
			} catch (NoSuchProviderException | IOException e) {
				System.out.println("Gmail service unavailable");
			}
		}

		// fb init
		if (config.getFbAcessToken() != null) {
			try {
				Facebook fb = new Facebook();
				fb.getUser();
				fb.getExtendedAccessToken(config.getFbAcessToken(),config.getFbAppId(), config.getFbAppSecret());
				fb.filterFacebookPost(config.getFbAcessToken(), frame,
						posts);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		FileOutputStream f;
		try {
			f = new FileOutputStream("file.txt");
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(posts);
			s.close();
			System.out.println("File Saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void offlineServices() {
		ArrayList<OfflineMessage> posts = new ArrayList<OfflineMessage>();
		FileInputStream in;
		try {
			in = new FileInputStream("file.txt");
			ObjectInputStream s = new ObjectInputStream(in);
			posts = (ArrayList<OfflineMessage>) s.readObject();
			for (int i = 0; i != posts.size(); i++) {
				frame.addMessage(new MessagePanel(posts.get(i).getSender(), posts.get(i).getMessageContent(),
						posts.get(i).getServiceType(), posts.get(i).getDateSent(), null));
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void loadSettings(Config config) throws ParserConfigurationException, SAXException,
			TransformerFactoryConfigurationError, TransformerException, XPathExpressionException {
		try {
			File inputFile = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			// read settings
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			// gmail
			XPathExpression expr = xpath.compile("/CONFIGURATION/Gmail/@*");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			if (nl.item(0) != null) {
				// gmail
				config.setGmailMail(nl.item(0).getFirstChild().getNodeValue());
				// password
				config.setGmailPassword(nl.item(1).getFirstChild().getNodeValue());
				// protocol
				config.setGmailProtocol(nl.item(2).getFirstChild().getNodeValue());
			} else {
				config.setGmailMail(null);
				config.setGmailPassword(null);
				config.setGmailProtocol(null);
			}

			// facebook
			expr = xpath.compile("/CONFIGURATION/Facebook/@*");
			nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			if (nl.item(0) != null) {
				// AccessToken
				config.setFbAcessToken(nl.item(0).getFirstChild().getNodeValue());
				// AppId
				config.setFbAppId(nl.item(1).getFirstChild().getNodeValue());
				// ASecret
				config.setFbAppSecret(nl.item(2).getFirstChild().getNodeValue());
			} else {
				config.setFbAcessToken(null);
				config.setFbAppId(null);
				config.setFbAppSecret(null);
			}

			// twitter
			expr = xpath.compile("/CONFIGURATION/Twitter/@*");
			nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			if (nl.item(0) != null) {
				// AAT
				config.setTwAAT(nl.item(0).getFirstChild().getNodeValue());
				// AATS
				config.setTwAATS(nl.item(1).getFirstChild().getNodeValue());
				// ACK
				config.setTwACK(nl.item(2).getFirstChild().getNodeValue());
				// ACS
				config.setTwACS(nl.item(3).getFirstChild().getNodeValue());
			} else {
				config.setTwAAT(null);
				config.setTwAATS(null);
				config.setTwACK(null);
				config.setTwACS(null);
			}

		} catch (IOException e) {
			System.out.println("settings file not found, creating one");

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("CONFIGURATION");
			doc.appendChild(rootElement);

			Element newElement1 = doc.createElement("Gmail");
			newElement1.setAttribute("Protocol", "imap");
			newElement1.setAttribute("Account", "gmail");
			newElement1.setAttribute("Password", "password");

			Element newElement2 = doc.createElement("Facebook");
			newElement2.setAttribute("AppId", "id");
			newElement2.setAttribute("AppSecret", "secret");
			newElement2.setAttribute("AccessToken", "token");

			Element newElement3 = doc.createElement("Twitter");
			newElement3.setAttribute("AuthConsumerKey", "aKey");
			newElement3.setAttribute("AuthConsumerSecret", "aSecret");
			newElement3.setAttribute("AuthAccessToken", "token");
			newElement3.setAttribute("AuthAccessTokenSecret", "tokenSecret");

			// Add new nodes to XML document (root element)
			rootElement.appendChild(newElement1);
			rootElement.appendChild(newElement2);
			rootElement.appendChild(newElement3);

			// Save XML document
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new File("config.xml"));
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		}
	}

}
