package gui;

public class Config {

	private String gmailProtocol;
	private String gmailMail;
	private String gmailPassword;
	private String fbAcessToken;
	private String fbAppId;
	private String fbAppSecret;
	private String twAAT;
	private String twAATS;
	private String twACK;
	private String twACS;
	
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
	public String getFbAcessToken() {
		return fbAcessToken;
	}
	public void setFbAcessToken(String fbAcessToken) {
		this.fbAcessToken = fbAcessToken;
	}
	public String getFbAppId() {
		return fbAppId;
	}
	public void setFbAppId(String fbAppId) {
		this.fbAppId = fbAppId;
	}
	public String getFbAppSecret() {
		return fbAppSecret;
	}
	public void setFbAppSecret(String fbAppSecret) {
		this.fbAppSecret = fbAppSecret;
	}
	public String getTwAAT() {
		return twAAT;
	}
	public void setTwAAT(String twAAT) {
		this.twAAT = twAAT;
	}
	public String getTwAATS() {
		return twAATS;
	}
	public void setTwAATS(String twAATS) {
		this.twAATS = twAATS;
	}
	public String getTwACK() {
		return twACK;
	}
	public void setTwACK(String twACK) {
		this.twACK = twACK;
	}
	public String getTwACS() {
		return twACS;
	}
	public void setTwACS(String twACS) {
		this.twACS = twACS;
	}
}
