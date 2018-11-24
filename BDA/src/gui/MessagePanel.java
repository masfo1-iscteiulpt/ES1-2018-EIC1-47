package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.Date;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MessagePanel extends JPanel{
	
	private ServiceType serviceType;
	private String messageContent;
	private String emitter;
	private Date dateSent;
	private JTextPane fullMesage;
	private JTextPane headerMsg;
	
	public MessagePanel(String from, String mc, ServiceType st, Date date) {
		serviceType = st;
		messageContent = mc;
		dateSent = date;
		emitter = from;
		
		JPanel headerPane = new JPanel();
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("EXPAND MSG");
				if(fullMesage.isVisible()) {
					fullMesage.setVisible(false);
					headerMsg.setVisible(true);
				} else {
					fullMesage.setVisible(true);
					headerMsg.setVisible(false);
				}
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
		JLabel fromLabel = new JLabel(emitter);
		fromLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		
		JLabel dateLabel = new JLabel(dateSent.toString());
		dateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		fullMesage = new JTextPane();
		//fullMesage.setWrapStyleWord(true);
		//fullMesage.setLineWrap(true);
		fullMesage.setBorder(null);
		fullMesage.setEditable(false);
		fullMesage.setText(mc);
		fullMesage.setVisible(false);
		
		headerMsg = new JTextPane();
		headerMsg.setEditable(false);
		headerMsg.setBorder(null);
		if(mc.length()>200) {
			headerMsg.setText(mc.substring(0, 200));
		} else {
			headerMsg.setText(mc);
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

	public ServiceType getService() {
		return serviceType;
	}

	public String getMessage() {
		return messageContent;
	}
}
