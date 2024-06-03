package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.FeedBackConverter;
import com.hotel.dto.Email;
import com.hotel.service.impl.EmailService;

@Controller
public class EmailController {


	private static EmailService emailService;
	
	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@RequestMapping(value = "/send-email", method = RequestMethod.GET)
	private ModelAndView sendEmail() {
		Email email =new Email();
		email.setToEmail("qsuk219@gmail.com");
		email.setSubject("Quên mật khẩu Bình Minh Hotel");
		email.setBody("ab22Fa");
		emailService.sendSimpleMessage(email);
		ModelAndView mav = new ModelAndView("redirect:/dang-nhap");
		return mav;
	}
}
