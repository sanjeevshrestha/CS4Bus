package com.cs4.ServiceBus.emailservice;



/**
 * Generic EmailText Class to store Email attributes
 * @author sanjeev
 *
 */
public class EmailText {
	
	/**
	 * 
	 */
	private String emailType;
	/**
	 * 
	 */
	private String emailSubject;
	/**
	 * 
	 */
	private String emailText;
		
	/**
	 * @return
	 */
	public String getEmailType() {
		return emailType;
	}
 
	/**
	 * @param emailType
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType.trim();
	}
 
	/**
	 * @return
	 */
	public String getEmailSubject() {
		return emailSubject;
	}
 
	/**
	 * @param emailSubject
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject.trim();
	}
 
	/**
	 * @return
	 */
	public String getEmailText() {
		return emailText;
	}
 
	/**
	 * @param emailText
	 */
	public void setEmailText(String emailText) {
		this.emailText = emailText.trim();
	}
 
/*	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
*/ 
}