package com.hotel.security;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.hotel.entity.AccountEntity;
import com.hotel.repository.AccountRespository;

@Component
public class CustomFailHandler implements AuthenticationFailureHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
		String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {

            redirectStrategy.sendRedirect(request, response, "/dang-nhap?nullAccount");
        } else {
        	
            redirectStrategy.sendRedirect(request, response, "/dang-nhap?incorrectAccount");
        }
	}
}
