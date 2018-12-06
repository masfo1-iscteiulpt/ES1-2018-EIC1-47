package gui;

public class Config {

	private String gmailProtocol;
	private String gmailMail;
	private String gmailPassword;
	
	public void setGmailProtocol(String gmailProtocol) {
		this.gmailProtocol = gmailProtocol;
	}
	public void setGmailMail(String gmailMail) {
		this.gmailMail = gmailMail;
	}
	public void setGmailPassword(String gmailPassword) {
		this.gmailPassword = gmailPassword;
	}
	public String getGmailProtocol() {
		return gmailProtocol;
	}
	public String getGmailMail() {
		return gmailMail;
	}
	public String getGmailPassword() {
		return gmailPassword;
	}
}
