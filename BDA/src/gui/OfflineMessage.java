package gui;

import java.io.Serializable;
import java.util.Date;

import enums.ServiceType;

public class OfflineMessage implements Serializable {

	private ServiceType serviceType;
	private String messageContent;
	private Date dateSent;
	private String sender;

	public OfflineMessage(String from, String mc, ServiceType st, Date date) {
		serviceType = st;
		messageContent = mc;
		dateSent = date;
		sender = from;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public String getSender() {
		return sender;
	}

}
