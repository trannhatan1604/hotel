package com.hotel.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.FeedBackConverter;
import com.hotel.dto.FeedBackDTO;
import com.hotel.entity.FeedBackEntity;
import com.hotel.service.IFeedBackService;

@Controller
public class FeedBackController {
	
	@Autowired
	private IFeedBackService feedbackService;

	
	@RequestMapping(value = "/quan-tri/phan-hoi", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/feedback/index");
		FeedBackDTO model = new FeedBackDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(4);
		mav.addObject("model", model);
		List<FeedBackDTO> dtoes = feedbackService.findByStatus();
		mav.addObject("feedbacks", dtoes);
		mav.addObject("countfeedback", dtoes.size());
		return mav;
	}
	@RequestMapping(value = "/quan-tri/phan-hoi/tim-kiem", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") FeedBackDTO dto) {
		dto.setLimit(dto.getLimit());
		dto.setPage(dto.getPage());
		dto.setSearchValue(dto.getSearchValue());
		dto.setStatus(dto.getStatus());
		Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());

		List<FeedBackDTO> feedback = feedbackService.findFeedBack(dto.getStatus(), pageable);
		dto.setListResult(feedback);
		dto.setTotalItem(feedbackService.getTotalItem(dto.getStatus()));
		dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
		
		
		ModelAndView mav = new ModelAndView("admin/feedback/search");
		return mav;
	}
	@RequestMapping(value = "/quan-tri/phan-hoi/chinh-sua", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam(name = "id", required = false) Integer id,@RequestParam(name = "status", required = false) Integer status) {
		//update thong tin phong
		FeedBackEntity entity = feedbackService.findOne(id);
		FeedBackDTO dto = FeedBackConverter.toDTO(entity);
		dto.setStatus(status);
		feedbackService.update(dto);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("redirect:/quan-tri/phan-hoi");
	    return modelAndView;
	}
}
