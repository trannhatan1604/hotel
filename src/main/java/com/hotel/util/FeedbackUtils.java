package com.hotel.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hotel.dto.FeedBackDTO;
import com.hotel.service.impl.FeedBackService;

@Configuration
@ComponentScan(basePackages = "com.hotel.util")
public class FeedbackUtils {

	@Autowired 
	private FeedBackService service;
	
	public void setListFeedBack(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		List<FeedBackDTO> feedbackList = service.findByStatus();
		request.setAttribute("feedbackList", feedbackList);
		request.setAttribute("countFeedBack", feedbackList.size());
	}
	
}
