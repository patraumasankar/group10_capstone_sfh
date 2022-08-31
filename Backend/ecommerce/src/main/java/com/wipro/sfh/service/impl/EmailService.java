package com.wipro.sfh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.wipro.sfh.dto.Email;

/**
 * @author Mani
 * Modified Date: 28-08-2022
 * Description: Email service
 * 
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;

	/**
	 * @author Mani
	 * Modified Date: 27-08-2022
	 * Description: send mail method 
	 * Param: Email
	 * Return Type: void
	 */
	
	public void sendSimpleEmail(Email email) {
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(email.getRecipient());
			mailMessage.setText(email.getMsgBody());
			mailMessage.setSubject(email.getSubject());

			mailSender.send(mailMessage);
			System.out.println("Mail Sent Successfully...");
		}

		catch (Exception e) {
			System.out.println("Error while Sending Mail");
		}

	}
}
