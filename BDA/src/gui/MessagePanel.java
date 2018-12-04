package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import enums.ServiceType;
import java.util.Date;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
/**
 * A specialized JPanel that represents a message obtained from a service.
 * 
 * @author Daniel Freitas
 * @version 1.0
 */
public class MessagePanel extends JPanel{
	
	private static final long serialVersionUID = -6800556301140860310L;
	public JTextPane fullMesage;
	public JTextPane headerMsg;
	private ServiceType serviceType;
	private String messageContent;
	private String sender;
	private Date dateSent;
	
	/**
	 * Created a MessagePanel with the specified sender, message content, service type and date.
	 * @param from	The sender's name.
	 * @param mc	The message content.
	 * @param st	The service type.
	 * @param date	The date the message was sent.
	 */
	public MessagePanel(String from, String mc, ServiceType st, Date date) {
		serviceType = st;
		messageContent = mc;
		dateSent = date;
		sender = from;
		
		JPanel headerPane = new JPanel();
		headerPane.setBackground(new Color(220, 220, 220));
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				expandMessage();
			}
		});
		panel.setBorder(null);
		panel.setBackground(st.color());
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 68, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 101, Short.MAX_VALUE)
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
		
		GroupLayout gl_headerPane = new GroupLayout(headerPane);
		gl_headerPane.setHorizontalGroup(
			gl_headerPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(fromLabel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addComponent(headerMsg).addComponent(fullMesage)
		);
		gl_headerPane.setVerticalGroup(
			gl_headerPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_headerPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_headerPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fromLabel)
						.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(headerMsg, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addComponent(fullMesage, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					)
		);
		headerPane.setLayout(gl_headerPane);
		setLayout(groupLayout);
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
}
