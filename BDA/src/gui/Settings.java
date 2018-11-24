package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JPanel fbPanel;
	private JPanel twPanel;
	private JPanel mailPanel;
	private JTextField fbAI;
	private JTextField twACK;
	private JTextField gmail;
	private JTextField fbAS;
	private JTextField twACS;
	private JTextField gmailPass;
	private JTextField twAAT;
	private JTextField twAATS;
	private JTextField fbAT;

	public Settings() {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JCheckBox chckbxFb = new JCheckBox("FB");
		chckbxFb.setSelected(true);
		chckbxFb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = fbPanel.getComponents();
				if(chckbxFb.isSelected()) {
					for(Component c : cmpnts) {
						c.setEnabled(true);
					}
				} else {
					for(Component c : cmpnts) {
						c.setEnabled(false);
					}
				}
			}
		});
		
		JCheckBox chckbxTw = new JCheckBox("TW");
		chckbxTw.setSelected(true);
		chckbxTw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = twPanel.getComponents();
				if(chckbxTw.isSelected()) {
					for(Component c : cmpnts) {
						c.setEnabled(true);
					}
				} else {
					for(Component c : cmpnts) {
						c.setEnabled(false);
					}
				}
			}
		});
		
		JCheckBox chckbxGmail = new JCheckBox("Gmail");
		chckbxGmail.setSelected(true);
		chckbxGmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component[] cmpnts = mailPanel.getComponents();
				if(chckbxGmail.isSelected()) {
					for(Component c : cmpnts) {
						c.setEnabled(true);
					}
				} else {
					for(Component c : cmpnts) {
						c.setEnabled(false);
					}
				}
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
				System.out.println("save changes");
				boolean fw = chckbxTw.isSelected();
				boolean tw = chckbxTw.isSelected();
				boolean gm = chckbxGmail.isSelected();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addComponent(chckbxFb)
					.addGap(111)
					.addComponent(chckbxTw, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(112)
					.addComponent(chckbxGmail)
					.addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(fbPanel, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twPanel, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mailPanel, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(172)
					.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(196, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxGmail)
						.addComponent(chckbxTw)
						.addComponent(chckbxFb))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(twPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(mailPanel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(fbPanel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(saveButton)
					.addGap(7))
		);
		
		gmail = new JTextField();
		gmail.setColumns(10);
		
		gmailPass = new JTextField();
		gmailPass.setColumns(10);
		
		JLabel lblGmail = new JLabel("Gmail");
		
		JLabel lblPassword = new JLabel("Password");
		GroupLayout gl_mailPanel = new GroupLayout(mailPanel);
		gl_mailPanel.setHorizontalGroup(
			gl_mailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mailPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mailPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGmail, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(gmail, 130, 130, 130)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(gmailPass, 130, 130, 130))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_mailPanel.setVerticalGroup(
			gl_mailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mailPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblGmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gmailPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(188, Short.MAX_VALUE))
		);
		mailPanel.setLayout(gl_mailPanel);
		
		twACK = new JTextField();
		twACK.setColumns(10);
		
		twACS = new JTextField();
		twACS.setColumns(10);
		
		twAAT = new JTextField();
		twAAT.setColumns(10);
		
		twAATS = new JTextField();
		twAATS.setColumns(10);
		
		JLabel twAckLbl = new JLabel("Auth Consumer Key");
		
		JLabel twAcsLbl = new JLabel("Auth Consumer Secret");
		
		JLabel twAatLbl = new JLabel("Auth Acess Token");
		
		JLabel twAatsLbl = new JLabel("Auth Acess Token Secret");
		GroupLayout gl_twPanel = new GroupLayout(twPanel);
		gl_twPanel.setHorizontalGroup(
			gl_twPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_twPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_twPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_twPanel.createSequentialGroup()
							.addComponent(twAckLbl)
							.addContainerGap(49, Short.MAX_VALUE))
						.addGroup(gl_twPanel.createSequentialGroup()
							.addComponent(twACK, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_twPanel.createSequentialGroup()
							.addComponent(twAatsLbl, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(Alignment.TRAILING, gl_twPanel.createSequentialGroup()
							.addGroup(gl_twPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(twAcsLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
								.addComponent(twACS, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
							.addComponent(twAATS, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
							.addComponent(twAAT, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_twPanel.createSequentialGroup()
							.addComponent(twAatLbl, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(40, Short.MAX_VALUE))))
		);
		gl_twPanel.setVerticalGroup(
			gl_twPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_twPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(twAckLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twACK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(twAcsLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twACS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(twAatLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twAAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twAatsLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(twAATS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		twPanel.setLayout(gl_twPanel);
		
		fbAI = new JTextField();
		fbAI.setColumns(10);
		
		fbAS = new JTextField();
		fbAS.setColumns(10);
		
		JLabel fbAiLbl = new JLabel("App Id");
		
		JLabel fbAsLbl = new JLabel("App Secret");
		
		JLabel fbAtLbl = new JLabel("Acces Token");
		
		fbAT = new JTextField();
		fbAT.setColumns(10);
		GroupLayout gl_fbPanel = new GroupLayout(fbPanel);
		gl_fbPanel.setHorizontalGroup(
			gl_fbPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fbPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fbPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(fbAiLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(fbAI, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(fbAsLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(fbAS, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(fbAtLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(fbAT, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_fbPanel.setVerticalGroup(
			gl_fbPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fbPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(fbAiLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fbAI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fbAsLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fbAS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fbAtLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fbAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		fbPanel.setLayout(gl_fbPanel);
		contentPane.setLayout(gl_contentPane);
	}
}