package br.com.izifinance.iziregister.email;

import br.com.izifinance.iziregister.model.Register;

public class MessageConfirmation {

	private String sender;
	private String recipient;
	private String subject;
	private String body;

	public MessageConfirmation(String sender, String recipient, String subject, String body) {
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.body = body;
	}
	
	public MessageConfirmation(Register register) {
		this.sender = "izibankthiago@gmail.com";
		this.recipient = register.getEmail();
		this.subject = "Your registration was successfully received";
		this.body = register.toString();
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
