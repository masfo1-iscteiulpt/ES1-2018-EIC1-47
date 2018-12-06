package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import enums.ServiceType;
import twitter4j.Status;

import java.util.Date;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
/**
 * A specialized JPanel that represents a message obtained from a service.
 * 
 * @author Daniel Freitas
 * @version 1.0
 */
public class MessagePanel extends JPanel {
	
	private static final long serialVersionUID = -6800556301140860310L;
	private JPanel replyPanel;
	public JTextPane fullMesage;
	public JTextPane headerMsg;
	private ServiceType serviceType;
	private String messageContent;
	private String sender;
	private Date dateSent;
	private Status status;
	private JTextField subject;
	
	/**
	 * Created a MessagePanel with the specified sender, message content, service type and date.
	 * @param from	The sender's name.
	 * @param mc	The message content.
	 * @param st	The service type.
	 * @param date	The date the message was sent.
	 * @param status2 
	 */
	public MessagePanel(String from, String mc, ServiceType st, Date date, Status status) {
		this.status = status;
		serviceType = st;
		messageContent = mc;
		dateSent = date;
		sender = from;
		
		JPanel headerPane = new JPanel();
		headerPane.setBackground(new Color(220, 220, 220));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(st.color());
		
		JLabel retweetLbl = new JLabel("");
		retweetLbl.setHorizontalAlignment(SwingConstants.CENTER);
		retweetLbl.setIcon(new ImageIcon(MessagePanel.class.getResource("/resources/retweet.png")));
		
		JLabel expandMsgLbl = new JLabel("");
		expandMsgLbl.setHorizontalAlignment(SwingConstants.CENTER);
		expandMsgLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				expandMessage();
			}
		});
		expandMsgLbl.setIcon(new ImageIcon(MessagePanel.class.getResource("/resources/showmore.png")));
		
		JLabel replyLbl = new JLabel("");
		replyLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(replyPanel.isVisible()) {
					replyPanel.setVisible(false);
				} else {
					replyPanel.setVisible(true);
				}
			}
		});
		
		replyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		replyLbl.setIcon(new ImageIcon(MessagePanel.class.getResource("/resources/reply.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(replyLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(expandMsgLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(retweetLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(expandMsgLbl, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(retweetLbl, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(replyLbl, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(headerPane, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(headerPane, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		JLabel fromLabel = new JLabel(sender);
		fromLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		
		JLabel dateLabel = new JLabel(dateSent.toString());
		dateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		fullMesage = new JTextPane();
		fullMesage.setBorder(null);
		fullMesage.setEditable(false);
		fullMesage.setText(messageContent);
		fullMesage.setVisible(false);
		
		headerMsg = new JTextPane();
		headerMsg.setEditable(false);
		headerMsg.setBorder(null);
		if(mc.length()>200) {
			headerMsg.setText(messageContent.substring(0, 200));
		} else {
			headerMsg.setText(messageContent);
		}
		
		replyPanel = new JPanel();
		replyPanel.setBackground(new Color(200, 200, 200));
		replyPanel.setVisible(false);
		
		GroupLayout gl_headerPane = new GroupLayout(headerPane);
		gl_headerPane.setHorizontalGroup(
			gl_headerPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(fromLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addComponent(replyPanel)
				.addComponent(headerMsg)
				.addComponent(fullMesage)
		);
		gl_headerPane.setVerticalGroup(
			gl_headerPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_headerPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fromLabel)
						.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(headerMsg)
					.addComponent(fullMesage)
					.addComponent(replyPanel))
		);
		
		JTextArea messageToSend = new JTextArea();
		
		JLabel sendLbl = new JLabel("");
		sendLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Reply");
			}
		});
		
		sendLbl.setIcon(new ImageIcon(MessagePanel.class.getResource("/resources/send.png")));
		
		subject = new JTextField();
		subject.setColumns(10);
		GroupLayout gl_replyPanel = new GroupLayout(replyPanel);
		gl_replyPanel.setHorizontalGroup(
			gl_replyPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_replyPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_replyPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(subject, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
						.addComponent(messageToSend, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sendLbl, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		gl_replyPanel.setVerticalGroup(
			gl_replyPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_replyPanel.createSequentialGroup()
					.addGap(15)
					.addComponent(subject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(messageToSend, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_replyPanel.createSequentialGroup()
					.addGap(111)
					.addComponent(sendLbl, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
					.addGap(71))
		);
		replyPanel.setLayout(gl_replyPanel);
		headerPane.setLayout(gl_headerPane);
		setLayout(groupLayout);
		
		if(!this.serviceType.equals(ServiceType.TW)) {
			retweetLbl.setVisible(false);
		} else {
			replyLbl.setVisible(false);
		}
	}
	
	/**
	 * Expands the MessagePanel showing the full message.
	 */
	public void expandMessage() {
		if(fullMesage.isVisible()) {
			fullMesage.setVisible(false);
			headerMsg.setVisible(true);
		} else {
			fullMesage.setVisible(true);
			headerMsg.setVisible(false);
		}	
	}

	/**
	 * Returns the service type of the MessagePanel.
	 * @return the ServiceType of this MessagePanel.
	 */
	public ServiceType getService() {
		return serviceType;
	}

	/**
	 * Returns the message content.
	 * @return the message content.
	 */
	public String getMessage() {
		return messageContent;
	}
	
	/**
	 * Returns the sender of the message.
	 * @return the sender's name.
	 */
	public String getSender() {
		return sender;
	}
	
	/**
	 * Returns the date the message was sent.
	 * @return the date the message was sent.
	 */
	public Date getDate() {
		return dateSent;
	}

	/**
	 * The message header if the message is too long.
	 * @return the message header.
	 */
	public String getHeader() {
		return headerMsg.getText();
	}
	
	public Status getStatus() {
		return status;
	}
}
