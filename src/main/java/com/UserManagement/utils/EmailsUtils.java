package com.UserManagement.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailsUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to,String subject,String body)
	{
		boolean isSent= false;
		
		try {
			MimeMessage mimemessages=mailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimemessages);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(mimemessages);
			isSent=true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return isSent;
	}

}
