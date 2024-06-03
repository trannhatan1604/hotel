package com.hotel.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hotel.dto.Email;

@Service
public class EmailService {
	
	private static JavaMailSender mailSender;
	
	@Autowired(required=true)
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

    public void sendSimpleMessage(Email email) {
    	//cách cũ hơn là dùng SimpleMailMessage message
    	MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom("Binh Minh Hotel <binhminhhotel@gmail.com>");
            helper.setTo(email.getToEmail());
            helper.setSubject(email.getSubject());

            // Sử dụng nội dung HTML
            String bodyWithHtml = "<div style='text-align: center;'>" + 
                    "<h1 style='color: green; font-weight: bold;'>Thông báo đổi mật khẩu</h1>" +
                    "<p style='color: black;'>Mật khẩu của bạn đã được đổi thành:</p>" +
                    "<p style='color: red;'>" + email.getBody() + "</p>" +
                    "</div>";
            helper.setText(bodyWithHtml, true); // true để chỉ định nội dung là HTML

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        }   
    }
}
