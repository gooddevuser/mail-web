package com.springexample.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/send-mail", method = RequestMethod.GET)
	public String showSendMailForm() {
		
		return "send-mail";
	}
	
	@RequestMapping(value = "/send-mail", method = RequestMethod.POST)
	public String sendMail(
			String title, String from, String to, String content) {
		
		//이메일 보내기 구현
		String user = "ohchihooooon@naver.com";
		String passwd = "";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.ssl.enable", "smtp.gmail.com");
//		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, passwd);
			}
		});
	
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(title);
			message.setText(content);
			
			Transport.send(message);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "send-mail";
	}
	
}














