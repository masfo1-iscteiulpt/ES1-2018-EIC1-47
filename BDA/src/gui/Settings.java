package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import enums.ServiceType;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
/**
 * A settings frame that allows the user to tweak features.
 * 
 * @author Daniel Freitas
 * @version 1.0
 */
public class Settings extends JFrame {

	public JCheckBox chckbxFb;
	public JCheckBox chckbxGm;
	public JCheckBox chckbxTw;
	public JPanel fbPanel;
	public JPanel twPanel;
	public JPanel mailPanel;
	private JPanel contentPane;
	private JTextField fbAI;
	private JTextField twACK;
	private JTextField gmail;
	private JTextField fbAS;
	private JTextField twACS;
	private JTextField gmailPass;
	private JTextField twAAT;
	private JTextField twAATS;
	private JTextField fbAT;

	/**
	 * Creates a settings frame.
	 */
	public Settings(Config config) {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		chckbxFb = new JCheckBox("FB");
		chckbxFb.setSelected(true);
		chckbxFb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = fbPanel.getComponents();
				changeStateFb(cmpnts);
			}
		});

		chckbxTw = new JCheckBox("TW");
		chckbxTw.setSelected(true);
		chckbxTw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = twPanel.getComponents();
				changeStateTw(cmpnts);
			}
		});

		chckbxGm = new JCheckBox("Gmail");
		chckbxGm.setSelected(true);
		chckbxGm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = mailPanel.getComponents();
				changeStateGm(cmpnts);
			}
		});
		
		
		
		fbPanel = new JPanel();
		fbPanel.setBackground(ServiceType.FB.color());
		twPanel = new JPanel();
		twPanel.setBackground(ServiceType.TW.color());
		mailPanel = new JPanel();
		mailPanel.setBackground(ServiceType.GM.color());

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveConfigChanges();
				} catch (ParserConfigurationException | TransformerFactoryConfigurationError
						| TransformerException e1) {
					System.out.println("unable to save changes");
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addGap(55).addComponent(chckbxFb).addGap(111)
								.addComponent(chckbxTw, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE).addGap(112)
								.addComponent(chckbxGm).addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(fbPanel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(twPanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(mailPanel, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(172)
						.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(196, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(chckbxGm)
						.addComponent(chckbxTw).addComponent(chckbxFb))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(twPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(mailPanel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(fbPanel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(saveButton).addGap(7)));

		JLabel lblGmail = new JLabel("Gmail");
		JLabel lblPassword = new JLabel("Password");
		gmail = new JTextField(config.getGmailMail());
		gmail.setColumns(10);
		gmailPass = new JTextField(config.getGmailPassword());
		gmailPass.setColumns(10);

		GroupLayout gl_mailPanel = new GroupLayout(mailPanel);
		gl_mailPanel.setHorizontalGroup(gl_mailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mailPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_mailPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGmail, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(gmail, 130, 130, 130)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(gmailPass, 130, 130, 130))
						.addContainerGap(30, Short.MAX_VALUE)));
		gl_mailPanel.setVerticalGroup(gl_mailPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_mailPanel
				.createSequentialGroup().addContainerGap().addComponent(lblGmail)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(gmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblPassword)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(gmailPass, GroupLayout.PREFERRED_SIZE,
						GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(188, Short.MAX_VALUE)));
		mailPanel.setLayout(gl_mailPanel);

		twACK = new JTextField(config.getTwACK());
		twACK.setColumns(10);

		twACS = new JTextField(config.getTwACS());
		twACS.setColumns(10);

		twAAT = new JTextField(config.getTwAAT());
		twAAT.setColumns(10);

		twAATS = new JTextField(config.getTwAATS());
		twAATS.setColumns(10);

		JLabel twAckLbl = new JLabel("Auth Consumer Key");

		JLabel twAcsLbl = new JLabel("Auth Consumer Secret");

		JLabel twAatLbl = new JLabel("Auth Access Token");

		JLabel twAatsLbl = new JLabel("Auth Access Token Secret");
		GroupLayout gl_twPanel = new GroupLayout(twPanel);
		gl_twPanel.setHorizontalGroup(gl_twPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_twPanel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_twPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_twPanel.createSequentialGroup().addComponent(twAckLbl).addContainerGap(49,
								Short.MAX_VALUE))
						.addGroup(gl_twPanel.createSequentialGroup()
								.addComponent(twACK, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE).addGap(18))
						.addGroup(Alignment.TRAILING, gl_twPanel.createSequentialGroup()
								.addComponent(twAatsLbl, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE).addGap(18))
						.addGroup(Alignment.TRAILING,
								gl_twPanel.createSequentialGroup()
										.addGroup(gl_twPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(twAcsLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														134, Short.MAX_VALUE)
												.addComponent(twACS, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
										.addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
								.addComponent(twAATS, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE).addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
								.addComponent(twAAT, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE).addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
								.addComponent(twAatLbl, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(40, Short.MAX_VALUE)))));
		gl_twPanel.setVerticalGroup(gl_twPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_twPanel.createSequentialGroup().addContainerGap().addComponent(twAckLbl)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(twACK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(twAcsLbl)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(twACS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(twAatLbl)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(twAAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(twAatsLbl)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(twAATS, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(19)));
		twPanel.setLayout(gl_twPanel);

		fbAI = new JTextField(config.getFbAppId());
		fbAI.setColumns(10);

		fbAS = new JTextField(config.getFbAppSecret());
		fbAS.setColumns(10);

		JLabel fbAiLbl = new JLabel("App Id");

		JLabel fbAsLbl = new JLabel("App Secret");

		JLabel fbAtLbl = new JLabel("Access Token");

		fbAT = new JTextField(config.getFbAcessToken());
		fbAT.setColumns(10);
		GroupLayout gl_fbPanel = new GroupLayout(fbPanel);
		gl_fbPanel.setHorizontalGroup(gl_fbPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fbPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_fbPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(fbAiLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(fbAI, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(fbAsLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(fbAS, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(fbAtLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addComponent(fbAT, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
						.addContainerGap()));
		gl_fbPanel.setVerticalGroup(gl_fbPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_fbPanel
				.createSequentialGroup().addContainerGap().addComponent(fbAiLbl)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(fbAI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(fbAsLbl)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(fbAS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(fbAtLbl)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(fbAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(137, Short.MAX_VALUE)));
		fbPanel.setLayout(gl_fbPanel);
		contentPane.setLayout(gl_contentPane);
		
		if (config.getGmailMail() == null) {
			chckbxGm.setSelected(false);
			Component[] cmpnts = mailPanel.getComponents();
			changeStateGm(cmpnts);
		} 
		
		if (config.getFbAcessToken() == null) {
			chckbxFb.setSelected(false);
			Component[] cmpnts = fbPanel.getComponents();
			changeStateGm(cmpnts);
		} 
		
		if (config.getTwAAT() == null) {
			chckbxTw.setSelected(false);
			Component[] cmpnts = twPanel.getComponents();
			changeStateGm(cmpnts);
		} 
	}

	/**
	 * Saves the configuration to a .xml file
	 * 
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	protected void saveConfigChanges()
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		System.out.println("saving config changes");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("CONFIGURATION");
		doc.appendChild(rootElement);

		Element newElement1 = doc.createElement("Gmail");
		if (chckbxGm.isSelected()) {
			newElement1.setAttribute("Protocol", "imap");
			newElement1.setAttribute("Account", gmail.getText());
			newElement1.setAttribute("Password", gmailPass.getText());
		}

		Element newElement2 = doc.createElement("Facebook");
		if (chckbxFb.isSelected()) {
			newElement2.setAttribute("AppId", fbAI.getText());
			newElement2.setAttribute("AppSecret", fbAS.getText());
			newElement2.setAttribute("AccessToken", fbAT.getText());
		}

		Element newElement3 = doc.createElement("Twitter");
		if (chckbxTw.isSelected()) {
			newElement3.setAttribute("AuthConsumerKey", twACK.getText());
			newElement3.setAttribute("AuthConsumerSecret", twACS.getText());
			newElement3.setAttribute("AuthAccessToken", twAAT.getText());
			newElement3.setAttribute("AuthAccessTokenSecret", twAATS.getText());
		}

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
		this.dispose();
	}

	/**
	 * Enables/disables the Facebook Panel.
	 * 
	 * @param cmpnts
	 *            the Facebook panels components.
	 */
	public void changeStateFb(Component[] cmpnts) {
		if (chckbxFb.isSelected()) {
			for (Component c : cmpnts) {
				c.setEnabled(true);
			}
		} else {
			for (Component c : cmpnts) {
				c.setEnabled(false);
			}
		}
	}

	/**
	 * Enables/disables the Twitter Panel.
	 * 
	 * @param cmpnts
	 *            the Twitter panels components.
	 */
	public void changeStateTw(Component[] cmpnts) {
		if (chckbxTw.isSelected()) {
			for (Component c : cmpnts) {
				c.setEnabled(true);
			}
		} else {
			for (Component c : cmpnts) {
				c.setEnabled(false);
			}
		}
	}

	/**
	 * Enables/disables the G-mail Panel.
	 * 
	 * @param cmpnts
	 *            the G-mail panels components.
	 */
	public void changeStateGm(Component[] cmpnts) {
		if (chckbxGm.isSelected()) {
			for (Component c : cmpnts) {
				c.setEnabled(true);
			}
		} else {
			for (Component c : cmpnts) {
				c.setEnabled(false);
			}
		}
	}
}