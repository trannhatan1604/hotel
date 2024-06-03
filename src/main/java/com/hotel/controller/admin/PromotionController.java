package com.hotel.controller.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.dto.PromotionDTO;
import com.hotel.service.impl.PromotionService;
import com.hotel.util.CompareDate;

@Controller
public class PromotionController {
	
	@Autowired
	private CompareDate compareDate;

	@Autowired
	private PromotionService promotionService;

	@RequestMapping(value = "/quan-tri/khuyen-mai", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/promotion/index");
		PromotionDTO model = new PromotionDTO();
		model.setPage(1);
		model.setLimit(4);
		model.setSearchValue("");
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/khuyen-mai/tim-kiem", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") PromotionDTO dto) {

		

		dto.setLimit(dto.getLimit());
		dto.setPage(dto.getPage());  
//		dto.setSearchValue(dto.getSearchValue());
		Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit(), Sort.Direction.DESC, "endDate");
		System.out.println("check page: " + dto.getPage());
		if(dto.getStatus().equals("")) {

			dto.setListResult(promotionService.findAll(pageable, dto.getStatus()));
			dto.setTotalItem(promotionService.getTotalItem());
			dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
		}else {

			dto.setListResult(promotionService.findAll(pageable, dto.getStatus()));
			dto.setTotalItem(promotionService.countWithFilter(dto.getStatus()));
			dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
		}
//		System.out.println("Total page: " + dto.getTotalPage());
//		System.out.println("page: " + dto.getPage());
//		System.out.println("Limit: " + dto.getLimit());
		ModelAndView mav = new ModelAndView("admin/promotion/search");
		mav.addObject("model", dto);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/khuyen-mai/them", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("admin/promotion/edit");
		PromotionDTO dto = new PromotionDTO();
		dto.setId(0);
		mav.addObject("model", dto);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/khuyen-mai/chinh-sua", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("admin/promotion/edit");
		PromotionDTO dto = new PromotionDTO();
		if (id != null) {
			dto = promotionService.findById(id);
		}
		mav.addObject("model", dto);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/khuyen-mai/luu", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("model") @Valid PromotionDTO dto, BindingResult result) {	
		System.out.println(dto.getDescription());
		ModelAndView mav = new ModelAndView();
		String[] dates = dto.getDateRange().split("\\s*-\\s*");
		//formatDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//chuyển LocalDate phải có format
		boolean checkDate = compareDate.CompareDate(LocalDate.parse(dates[0], formatter));
		if(result.hasErrors()||(0 > dto.getLevel() || dto.getLevel() > 100 || String.valueOf(dto.getLevel()).isEmpty() ) || !checkDate){
			
			if(2 > dto.getLevel() || dto.getLevel() > 100|| String.valueOf(dto.getLevel()).isEmpty())
				result.rejectValue("level", "", "Mức giảm giá phải từ 2% - 100%");
			if( !checkDate)
				result.rejectValue("dateRange", "", "Ngày đặt phải từ ngày hiện tại trở lên!");
			mav.setViewName("admin/promotion/edit");
			mav.addObject("model", dto);
			return mav;
		}
		else {
			promotionService.updatePromotion(dto);
			mav.setViewName("redirect:/quan-tri/khuyen-mai");
		    return mav;
		}
		
	}
	
	@RequestMapping(value = "/quan-tri/khuyen-mai/xoa", method = RequestMethod.GET)
	public ModelAndView xoa(@RequestParam int id) {	
		promotionService.deletePromotion(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/quan-tri/khuyen-mai");
	    return modelAndView;
	}
	
}
