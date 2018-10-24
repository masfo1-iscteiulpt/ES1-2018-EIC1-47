package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BdaGUI extends JFrame {

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

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BdaGUI frame = new BdaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public BdaGUI() {
		setTitle("Bom Dia Academia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setBackground(new Color(50, 50, 50));
		
		twPanel = new JPanel();
		twPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter tw");
			}
		});
		twPanel.setMinimumSize(new Dimension(100, 100));
		twPanel.setBackground(new Color(29, 161, 243));
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
			}
		});
		fbPanel.setMinimumSize(new Dimension(100, 100));
		fbPanel.setBackground(new Color(74, 110, 170));
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
			}
		});
		emailPanel.setMinimumSize(new Dimension(100, 100));
		emailPanel.setBackground(new Color(193, 64, 63));
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
				System.out.println("Remove filter");
			}
		});
		bdaPanel.setMinimumSize(new Dimension(100, 100));
		bdaPanel.setBorder(null);
		bdaPanel.setBackground(new Color(255, 138, 0));
		
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
		
		GroupLayout gl_mainPanel = new GroupLayout(mainPanel);
		gl_mainPanel.setHorizontalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 575, Short.MAX_VALUE)
		);
		gl_mainPanel.setVerticalGroup(
			gl_mainPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 579, Short.MAX_VALUE)
		);
		
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
				System.out.println("Perform search");
			}
		});
		search.setPreferredSize(new Dimension(24, 24));
		search.setIcon(new ImageIcon(BdaGUI.class.getResource("/resources/search.png")));
		
		searchField = new JTextField();
		searchField.setForeground(Color.WHITE);
		searchField.setCaretColor(Color.WHITE);
		searchField.setBorder(null);
		searchField.setColumns(10);
		searchField.setBackground(new Color(50, 50, 50));
		
		
		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(253)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(102))
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(search, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(searchField, Alignment.LEADING))
					.addGap(3))
		);
		menuPanel.setLayout(gl_menuPanel);
		
		mainPanel.setLayout(gl_mainPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
