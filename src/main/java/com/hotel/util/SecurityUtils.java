package com.hotel.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hotel.dto.MyUser;


//class dung de co cac phuong thuc thao tac voi User
@Component
public class SecurityUtils {
	public static MyUser getPrincipal() {
		//kiểm tra Authentication null - kiểm tra TH chưa đăng nhập 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.getPrincipal() instanceof MyUser) {
	        return (MyUser) authentication.getPrincipal();
	    }
	    return null;
    }
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<String>();
		//get danh sach authorities
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());
		}
		return results;
	}

}
