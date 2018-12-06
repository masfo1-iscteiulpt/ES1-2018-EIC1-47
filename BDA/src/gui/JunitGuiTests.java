package gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.Date;
import javax.swing.JFrame;
import org.junit.Test;

import enums.ServiceType;

public class JunitGuiTests {

	@Test
	public void guiAddMessageTest() {
		BdaGUI g = new BdaGUI(new Config());
		assertEquals(g.messages.size(), 0);

		MessagePanel testMessage = new MessagePanel("From", "MessageContent", ServiceType.FB,
				new Date(System.currentTimeMillis()));
		g.addMessage(testMessage);

		assertEquals(g.messages.size(), 1);
		assertEquals(g.messages.getLast(), testMessage);

		MessagePanel testMessage2 = new MessagePanel("From",
				"MessageContentLongerThan200Charssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
				ServiceType.FB, new Date(System.currentTimeMillis()));
		g.addMessage(testMessage2);

		assertEquals(g.messages.size(), 2);
		assertEquals(g.messages.getLast(), testMessage2);
		assertEquals(testMessage2.getHeader(), testMessage2.getMessage().substring(0, 200));
	}

	@Test
	public void guiRemoveFiltersTest() {
		BdaGUI g = new BdaGUI(new Config());
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.FB, new Date(System.currentTimeMillis())));
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.TW, new Date(System.currentTimeMillis())));
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.GM, new Date(System.currentTimeMillis())));
		g.messages.getFirst().setVisible(false);
		g.messages.getLast().setVisible(false);

		g.removeFilters();

		for (MessagePanel p : g.messages) {
			assertEquals(p.isVisible(), true);
		}
	}

	@Test
	public void guiFilterMessagesServiceTypeTest() {
		BdaGUI g = new BdaGUI(new Config());
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.FB, new Date(System.currentTimeMillis())));
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.TW, new Date(System.currentTimeMillis())));
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.GM, new Date(System.currentTimeMillis())));

		g.filterMessages(ServiceType.FB);

		assertEquals(g.messages.get(0).isVisible(), true);
		assertEquals(g.messages.get(1).isVisible(), false);
		assertEquals(g.messages.get(2).isVisible(), false);

		g.filterMessages(ServiceType.TW);

		assertEquals(g.messages.get(0).isVisible(), false);
		assertEquals(g.messages.get(1).isVisible(), true);
		assertEquals(g.messages.get(2).isVisible(), false);

		g.filterMessages(ServiceType.GM);

		assertEquals(g.messages.get(0).isVisible(), false);
		assertEquals(g.messages.get(1).isVisible(), false);
		assertEquals(g.messages.get(2).isVisible(), true);
	}

	@Test
	public void guiFilterMessagesKeyTest() {
		BdaGUI g = new BdaGUI(new Config());
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.FB, new Date(System.currentTimeMillis())));
		g.messages.add(new MessagePanel("From", "Hello", ServiceType.TW, new Date(System.currentTimeMillis())));
		g.messages.add(new MessagePanel("From", "Bye", ServiceType.GM, new Date(System.currentTimeMillis())));

		g.filterMessages("Hello");

		assertEquals(g.messages.get(0).isVisible(), false);
		assertEquals(g.messages.get(1).isVisible(), true);
		assertEquals(g.messages.get(2).isVisible(), false);
	}

	@Test
	public void guiAdvandecSearchTest() {
		BdaGUI g = new BdaGUI(new Config());
		g.messages
				.add(new MessagePanel("From", "MessageContent", ServiceType.FB, new Date(System.currentTimeMillis())));
		g.messages.add(new MessagePanel("Other", "Hello", ServiceType.TW, new Date(System.currentTimeMillis())));
		g.messages.add(
				new MessagePanel("From", "Bye", ServiceType.GM, new Date(System.currentTimeMillis() - 1000000000)));
		g.messages.add(
				new MessagePanel("Other", "Bye", ServiceType.GM, new Date(System.currentTimeMillis() - 1000000000)));

		g.searchAdvTime.setSelectedIndex(0); // 12h
		g.searchAdvUser.setText("From"); // User "From"
		g.performAdvSearch();

		assertEquals(g.messages.get(0).isVisible(), true);
		assertEquals(g.messages.get(1).isVisible(), false);
		assertEquals(g.messages.get(2).isVisible(), false);

		g.searchAdvTime.setSelectedIndex(6); // All
		g.searchAdvUser.setText(""); // Everyone
		g.performAdvSearch();

		assertEquals(g.messages.get(0).isVisible(), true);
		assertEquals(g.messages.get(1).isVisible(), true);
		assertEquals(g.messages.get(2).isVisible(), true);

	}

	@Test
	public void settingsFrameTest() {
		Settings frame = new Settings(new Config());
		assertNotNull(frame);
		assertEquals(frame.isResizable(), false);
		assertEquals(frame.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		assertEquals(frame.getBounds(), new Rectangle(100, 100, 500, 400));
	}

	@Test
	public void changeStateFbTest() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.fbPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxFb.setSelected(false);

		frame.changeStateFb(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), false);
		}
	}

	@Test
	public void changeStateFbTest2() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.fbPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxFb.setSelected(true);

		frame.changeStateFb(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}
	}

	@Test
	public void changeStateTwTest() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.twPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxTw.setSelected(false);

		frame.changeStateTw(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), false);
		}
	}

	@Test
	public void changeStateTwTest2() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.twPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxTw.setSelected(true);

		frame.changeStateTw(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}
	}

	@Test
	public void changeStateGmTest() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.mailPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxGm.setSelected(false);

		frame.changeStateGm(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), false);
		}
	}

	@Test
	public void changeStateGmTest2() {
		Settings frame = new Settings(new Config());
		Component[] cmpnts = frame.mailPanel.getComponents();

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}

		frame.chckbxGm.setSelected(true);

		frame.changeStateGm(cmpnts);

		for (Component c : cmpnts) {
			assertEquals(c.isEnabled(), true);
		}
	}

}
