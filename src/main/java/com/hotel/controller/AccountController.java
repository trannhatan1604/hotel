package com.hotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.constant.SystemConstant;
import com.hotel.dto.AccountDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.service.impl.AccountService;
import com.hotel.util.SecurityUtils;


@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("register");
		AccountDTO dto = new AccountDTO();
		dto.setId(null);
		mav.addObject("model", dto);
		return mav;
	}
	@RequestMapping(value = "/dang-ky/luu", method = RequestMethod.POST)
	public ModelAndView addUser( @ModelAttribute("model") @Valid AccountDTO dto, BindingResult result ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		//check lỗi
		boolean checkpass = dto.getPassword().equals(dto.getRepeatPassword());
		AccountEntity checkAccount = accountService.findOneByPhone(dto.getPhone());
		
		ModelAndView mav = new ModelAndView();

		if (result.hasErrors() || (!checkpass) 
				|| dto.getLastName().equals("") || (dto.getFirstName().equals("")) || (dto.getPassword().equals("")||dto.getPassword().length()<6) || checkAccount!=null) {
			if(checkAccount!=null)
				result.rejectValue("phone", "" , "Số điện thoại này đã được đăng ký");
			if(!checkpass) {
				result.rejectValue("repeatPassword", "" , "Mật khẩu không trùng khớp");
			}
			if(dto.getLastName().equals(""))
				result.rejectValue("lastName", "notEmpty" , "Họ trống");
			if(dto.getFirstName().equals(""))
				result.rejectValue("firstName", "notEmpty" , "Tên trống");
			if(dto.getPassword().equals("") || dto.getPassword().length()<6)
				result.rejectValue("password", "notEmpty", "Mật khẩu phải có ít nhất 6 ký tự");
			mav.setViewName("register");
			mav.addObject("model", dto);
			return mav;
		}
		else {
			// Mã hóa mật khẩu sử dụng bcrypt
	        String hashedPassword = encoder.encode(dto.getPassword());
	        String fullName = dto.getLastName()+" "+dto.getFirstName();
	        System.out.println("tên"+fullName);
			dto.setFullName(fullName);
			dto.setPassword(hashedPassword);
			dto.setStatus(1);
			dto.setImage("");
			dto.setAccountName(dto.getPhone());
			Integer id = accountService.insertUser(dto);
			System.out.println(id);
			accountService.assignRoleToAccount(id, SystemConstant.CUSTOMER_ROLE);
			mav.setViewName("redirect:/dang-nhap?successRegister=1");
			return mav;
		}
        
	}
	@RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu"); //redirect ve action homePage()
	}
	
	@RequestMapping(value = "/quan-tri/khong-co-quyen", method = RequestMethod.GET)
	public ModelAndView authoritative() {
		ModelAndView mav = new ModelAndView("non_authoritative");
		return mav;
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView logout() {
		if(SecurityUtils.getPrincipal().getFullname()!=null || !SecurityUtils.getPrincipal().getFullname().equals(""))
			return new ModelAndView("redirect:/quan-tri/khong-co-quyen");
		else
			return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
	
	/*
	 * @RequestMapping(value="/quan-tri/thong-tin/cap-nhat", method =
	 * RequestMethod.GET) public ModelAndView
	 * changePassword(@ModelAttribute("model") @Valid AccountDTO dto, BindingResult
	 * result) { ModelAndView mav =new ModelAndView();
	 * 
	 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); //check lỗi
	 * boolean checkpass = dto.getPassword().equals(dto.getRepeatPassword());
	 * 
	 * if(result.hasErrors() || (!checkpass && !dto.getPassword().equals("")) ||
	 * dto.getPassword().equals("")) { if(!checkpass) result.rejectValue("password",
	 * "" , "Mật khẩu không trùng khớp"); else if(dto.getPassword().equals("")) {
	 * result.rejectValue("password", "" , "Mật khẩu không được trống"); }
	 * mav.addObject("id", SecurityUtils.getPrincipal().getId());
	 * mav.addObject("model", dto); mav.setViewName("admin/employee/infor"); return
	 * mav; } else { mav.setViewName("redirect:/quan-tri/thong-tin"); return mav; }
	 * }
	 */
	
	@RequestMapping(value="/quan-tri/thong-tin/doi-mat-khau", method = RequestMethod.POST)
	public ModelAndView change(@ModelAttribute("model") AccountDTO dto) {
		ModelAndView mav = new ModelAndView("admin/employee/changePass");
		System.out.println("pass"+dto.getPassword());
		mav.addObject("model", dto);
		
		return mav;
	}
	
}
