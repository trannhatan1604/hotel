package com.hotel.controller.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.OrderConverter;
import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.FeedBackDTO;
import com.hotel.dto.OrderDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.service.IOrderService;
import com.hotel.service.ITypeRoomService;
import com.hotel.util.PriceFormat;
import com.hotel.util.SecurityUtils;

@Controller(value = "HomeControllerOfWeb")
public class HomeController {
	
	@Autowired
	private ITypeRoomService typeRoomService;
	
	@Autowired IOrderService oderService;
	
	@Autowired 
	private PriceFormat priceFormat;
	
	@RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
	public ModelAndView homePage(Model model) {
		
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}
	@RequestMapping(value = "/lich-su", method = RequestMethod.GET)
	public ModelAndView roomhistory(@ModelAttribute("model") FeedBackDTO dto) {
		ModelAndView mav = new ModelAndView("web/history");
		//FeedBackDTO dto = new FeedBackDTO();
		List<OrderDTO> dtos = oderService.findByCustomer_id(SecurityUtils.getPrincipal().getId());
		for(OrderDTO d : dtos) {
			d.setPriceFormat(priceFormat.format(d.getTotalPrice()));
		}
		mav.addObject("modelOrder", dtos);
		mav.addObject("model", dto);
		return mav;
	}
	@ModelAttribute("typerooms")
	public List<TypeRoomDTO> getAllType() {
    	List<TypeRoomEntity> entities = typeRoomService.findAllRoom();
    	List<TypeRoomDTO> dtos = new ArrayList<TypeRoomDTO>();
    	for (TypeRoomEntity entity : entities) {
    		TypeRoomDTO dto = new TypeRoomDTO();
			dto = TypeRoomConverter.toDTO(entity);
			dto.setPriceFormat(priceFormat.format(dto.getPrice()));
			dtos.add(dto);
		}
      return dtos;
  }
}
