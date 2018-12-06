package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import enums.ServiceType;
import enums.Time;

@SuppressWarnings("serial")
/**
 * This is the main graphical user interface class.
 * 
 * @author Daniel Freitas
 * @version 1.0
 *
 */
public class BdaGUI extends JFrame {

	public JPanel resultsPanel;
	public GroupLayout layout;
	public GroupLayout.Group sequential;
	public GroupLayout.ParallelGroup parallel;
	public JComboBox<Time> searchAdvTime;
	public JTextField searchAdvUser;
	public LinkedList<MessagePanel> messages = new LinkedList<MessagePanel>();
	private JPanel contentPane;
	private JPanel twPanel;
	private JPanel mainPanel;
	private JPanel bdaPanel;
	private JPanel fbPanel;
	private JPanel emailPanel;
	private JLabel twLogo;
	private JLabel fbLogo;
	private JLabel emailLogo;
	private JLabel search;
	private JTextField searchField;
	private JScrollPane resultsScrollPane;
	private JLabel searchAdv;
	private JPanel searchAdvPanel;
	private JLabel serachAdvLbl;
	private JLabel settings;
	private Config config;
	
	/**
	 * Creates a graphical user interface and all it's features.
	 * @param runnable 
	 */
	public BdaGUI(Config configuration) {
		setTitle("Bom Dia Academia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		config = configuration;
		mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setBackground(new Color(50, 50, 50));
		
		twPanel = new JPanel();
		twPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter tw");
				filterMessages(ServiceType.TW);
			}
		});
		twPanel.setMinimumSize(new Dimension(100, 100));
		twPanel.setBackground(ServiceType.TW.color());
		twPanel.setBorder(null);
		
		twLogo = new JLabel("");
		twLogo.setHorizontalAlignment(SwingConstants.CENTER);
		twLogo.setSize(new Dimension(100, 100));
		twLogo.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/tw.png")));
		GroupLayout gl_twPanel = new GroupLayout(twPanel);
		gl_twPanel.setHorizontalGroup(
			gl_twPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_twPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(twLogo, GroupLayout.PREFERRED_SIZE, 55, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_twPanel.setVerticalGroup(
			gl_twPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_twPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(twLogo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		twPanel.setLayout(gl_twPanel);
		
		fbPanel = new JPanel();
		fbPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter fb");
				filterMessages(ServiceType.FB);
			}
		});
		fbPanel.setMinimumSize(new Dimension(100, 100));
		fbPanel.setBackground(ServiceType.FB.color());
		fbPanel.setBorder(null);
		
		fbLogo = new JLabel("");
		fbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		fbLogo.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/fb.png")));
		GroupLayout gl_fbPanel = new GroupLayout(fbPanel);
		gl_fbPanel.setHorizontalGroup(
			gl_fbPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_fbPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(fbLogo, GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_fbPanel.setVerticalGroup(
			gl_fbPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fbPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(fbLogo, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(63, Short.MAX_VALUE))
		);
		fbPanel.setLayout(gl_fbPanel);
		
		emailPanel = new JPanel();
		emailPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter email");
				filterMessages(ServiceType.GM);
			}
		});
		emailPanel.setMinimumSize(new Dimension(100, 100));
		emailPanel.setBackground(ServiceType.GM.color());
		emailPanel.setBorder(null);
		
		emailLogo = new JLabel("");
		emailLogo.setHorizontalAlignment(SwingConstants.CENTER);
		emailLogo.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/email.png")));
		GroupLayout gl_emailPanel = new GroupLayout(emailPanel);
		gl_emailPanel.setHorizontalGroup(
			gl_emailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_emailPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(emailLogo, GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_emailPanel.setVerticalGroup(
			gl_emailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_emailPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(emailLogo, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		emailPanel.setLayout(gl_emailPanel);
		
		
		bdaPanel = new JPanel();
		bdaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Remove filters");
				removeFilters();
			}
		});
		bdaPanel.setMinimumSize(new Dimension(100, 100));
		bdaPanel.setBorder(null);
		bdaPanel.setBackground(ServiceType.BDA.color());
		
		JLabel bdaLogo = new JLabel("");
		bdaLogo.setHorizontalAlignment(SwingConstants.CENTER);
		bdaLogo.setSize(new Dimension(100, 100));
		bdaLogo.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/bda1.png")));
		
		GroupLayout gl_bdaPanel = new GroupLayout(bdaPanel);
		gl_bdaPanel.setHorizontalGroup(
			gl_bdaPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bdaPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(bdaLogo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_bdaPanel.setVerticalGroup(
			gl_bdaPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bdaPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(bdaLogo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(69, Short.MAX_VALUE))
		);
		bdaPanel.setLayout(gl_bdaPanel);
		
		resultsScrollPane = new JScrollPane();
		resultsScrollPane.setBorder(null);
		resultsScrollPane.setViewportBorder(null);
		resultsScrollPane.setBorder(null);
		
		resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(50, 50, 50));
		resultsScrollPane.setViewportView(resultsPanel);
		
		layout = new GroupLayout(resultsPanel);
        resultsPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        parallel = layout.createParallelGroup();
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(parallel));
        sequential = layout.createSequentialGroup();
        layout.setVerticalGroup(sequential);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(200, 200, 200));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(emailPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(fbPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(twPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bdaPanel, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
						.addComponent(menuPanel, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(menuPanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bdaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(twPanel, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
							.addComponent(fbPanel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
							.addComponent(emailPanel, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
					.addGap(0))
		);
		
		search = new JLabel("");
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(searchField.getText() != "") {
					System.out.println("Perform search for " + searchField.getText());
					filterMessages(searchField.getText());
				}
			}
		});
		search.setPreferredSize(new Dimension(24, 24));
		search.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/search.png")));
		
		searchAdv = new JLabel("");
		searchAdv.setPreferredSize(new Dimension(24, 24));
		searchAdv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(searchAdvPanel.isVisible()) {
					searchAdvPanel.setVisible(false);
				} else {
					searchAdvPanel.setVisible(true);
				}
			}
		});
		searchAdv.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/searchAdv.png")));
		
		searchField = new JTextField("");
		searchField.setForeground(Color.WHITE);
		searchField.setCaretColor(Color.WHITE);
		searchField.setBorder(null);
		searchField.setColumns(10);
		searchField.setBackground(new Color(50, 50, 50));
		
		searchAdvPanel = new JPanel();
		searchAdvPanel.setVisible(false);
		searchAdvPanel.setBackground(new Color(100, 100, 100));		
		
		serachAdvLbl = new JLabel("Advanced Search");
		serachAdvLbl.setForeground(Color.WHITE);
		
		searchAdvUser = new JTextField();
		searchAdvUser.setColumns(10);
		
		JLabel searchAdvBut = new JLabel();
		searchAdvBut.setHorizontalAlignment(SwingConstants.CENTER);
		searchAdvBut.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/searchAdv.png")));
		searchAdvBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				performAdvSearch();
			}
		});
		
		searchAdvTime = new JComboBox<Time>();
		searchAdvTime.setModel(new DefaultComboBoxModel<Time>(new Time[] {Time.H12, Time.D1, Time.D2, Time.D5, Time.D10, Time.D30, Time.ALL}));
		searchAdvTime.setSelectedIndex(6);
		
		JLabel searchAdvTimeLbl = new JLabel("Time:");
		JLabel searchAdvUserLbl = new JLabel("From user:");
		
		settings = new JLabel("");
		settings.setPreferredSize(new Dimension(24, 24));
		settings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Settings frame = new Settings(configuration);
				frame.setVisible(true);
			}
		});
		settings.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/settings.png")));
		
		GroupLayout gl_searchAdvPanel = new GroupLayout(searchAdvPanel);
		gl_searchAdvPanel.setHorizontalGroup(
			gl_searchAdvPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchAdvPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_searchAdvPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchAdvPanel.createSequentialGroup()
							.addComponent(searchAdvTimeLbl)
							.addContainerGap())
						.addGroup(gl_searchAdvPanel.createSequentialGroup()
							.addGroup(gl_searchAdvPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(searchAdvTime, Alignment.LEADING, 0, 86, Short.MAX_VALUE)
								.addComponent(searchAdvUser)
								.addGroup(gl_searchAdvPanel.createSequentialGroup()
									.addGap(2)
									.addGroup(gl_searchAdvPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(searchAdvUserLbl)
										.addComponent(serachAdvLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(searchAdvBut, Alignment.CENTER, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
							.addGap(32))))
		);
		gl_searchAdvPanel.setVerticalGroup(
			gl_searchAdvPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchAdvPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(serachAdvLbl)
					.addGap(23)
					.addComponent(searchAdvUserLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchAdvUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(searchAdvTimeLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchAdvTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
					.addComponent(searchAdvBut)
					.addGap(20))
		);
		searchAdvPanel.setLayout(gl_searchAdvPanel);
		
		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addContainerGap(253, Short.MAX_VALUE)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(searchAdv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(settings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(searchField, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(searchAdv, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(10)
						.addComponent(settings, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		
		menuPanel.setLayout(gl_menuPanel);
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createSequentialGroup()
					.addComponent(resultsScrollPane, GroupLayout.PREFERRED_SIZE, 500, Short.MAX_VALUE)
					.addComponent(searchAdvPanel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))		
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPanel.createParallelGroup()
					.addComponent(resultsScrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
					.addComponent(searchAdvPanel, GroupLayout.PREFERRED_SIZE, 579, Short.MAX_VALUE))
		);
		
		mainPanel.setLayout(gl_mainPanel);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Adds a MessagePanel component to the gui.
	 * @param mp The MessagePanel to be added.
	 */
	public void addMessage(MessagePanel mp) {
		messages.add(mp);
		parallel.addGroup(layout.createSequentialGroup().addComponent(mp));
		sequential.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(mp));
		mp.validate();
	}
	
	/**
	 * Filters the displayed messages on gui by service.
	 * @param st The desired service to filter the messages.
	 */
	public void filterMessages(ServiceType st) {
		removeFilters();
		for(MessagePanel p : messages) {
			if(p.getService() != st) {
				p.setVisible(false);
			}
		}
	}
	
	/**
	 * Filters the displayed messages on gui by a key string.
	 * @param key The desired string to filter the messages.
	 */
	public void filterMessages(String key) {
		for(MessagePanel p : messages) {
			if(!p.getMessage().contains(key)) {
				p.setVisible(false);
			}
		}
	}
	
	/**
	 * Removes the all the filters applied to the gui's messages.
	 */
	public void removeFilters() {
		for(MessagePanel p : messages) {
			if(!p.isVisible()) {
				p.setVisible(true);
			}
		}
	}
	
	/**
	 * Performs an advanced search in function of the desired time and sender's name.
	 */
	public void performAdvSearch() {
		removeFilters();
		long ms = ((Time) searchAdvTime.getSelectedItem() ).getSeconds();
		
		for(MessagePanel p : messages) {
			if(!searchAdvUser.getText().equals("") && !p.getSender().equals(searchAdvUser.getText())) {
				p.setVisible(false);
			}
			if(ms!=0 && p.getDate().getTime() < (System.currentTimeMillis() - ms*1000)) {
				p.setVisible(false);
			}
		}
	}
}
