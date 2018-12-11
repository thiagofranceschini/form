package br.com.izifinance.iziregister.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Mailer {
	
	@Autowired
	private JavaMailSender sender;
	
	@Async
	public void send(MessageConfirmation message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(message.getSender());
		mailMessage.setTo(message.getRecipient());
		mailMessage.setSubject(message.getSubject());
		mailMessage.setText(message.getBody());
		
		sender.send(mailMessage);
	}
}
