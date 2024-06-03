package com.hotel.controller;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.dto.AccountDTO;
import com.hotel.dto.Email;
import com.hotel.service.IAccountService;
import com.hotel.service.impl.EmailService;

@Controller
public class ForgetPasswordController {
	
	private static EmailService emailService;
	
	@Autowired
	public ForgetPasswordController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Autowired IAccountService accountService;
	
	@RequestMapping(value = "/doi-mat-khau",method = RequestMethod.GET)
	ModelAndView forgetPassword() {
		ModelAndView mav = new ModelAndView("forget_password");
		AccountDTO dto = new AccountDTO();
		dto.setId(null);
		mav.addObject("model", dto);
		return mav;
	}
	@RequestMapping(value = "/gui-mat-khau-moi",method = RequestMethod.POST)
	ModelAndView sendEmail(@ModelAttribute(name="model")AccountDTO dto) {
		ModelAndView mav = new ModelAndView("redirect:/dang-nhap?error=1");
		
		//tìm  khách hàng 
		AccountDTO model = accountService.findCustomerByPhone(dto.getPhone());
		System.out.println("email cần gửi :"+dto.getPhone());
		Email email =new Email();
		email.setToEmail(dto.getEmail());
		email.setSubject("Quên mật khẩu Bình Minh Hotel");
		
		//Random mật khẩu mới
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        String newpass = password.toString();
        
		email.setBody(newpass);
		emailService.sendSimpleMessage(email);
		//cập nhật lại pass mới
		//change đã có sẵn mã hoá Encode rồi
		accountService.changePassword(newpass, model.getId());
		return mav;
	}
}
