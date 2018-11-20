package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

public class MessagePanel extends JPanel{
	
	private ServiceType serviceType;
	private String messageContent;
	
	public MessagePanel(String mc, ServiceType st) {
		serviceType = st;
		messageContent = mc;
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBorder(null);
		textArea.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(st.color());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addComponent(textArea))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
				.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		textArea.setText(mc);
	}

	public ServiceType getService() {
		return serviceType;
	}
}
