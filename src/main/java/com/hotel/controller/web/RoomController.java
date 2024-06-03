package com.hotel.controller.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.RoomDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.service.IRoomService;
import com.hotel.service.ITypeRoomService;
import com.hotel.util.PriceFormat;

@Controller (value ="RoomOfWebController")
public class RoomController {
	@Autowired
	private ITypeRoomService typeRoomService;
	
	@Autowired
	private IRoomService roomService;
	
	@Autowired
	private PriceFormat priceFormat;
	
	@RequestMapping(value = "/phong", method = RequestMethod.GET)
	public ModelAndView room(@RequestParam(name = "countUser", required = false) Integer countUser, 
							 @RequestParam(name="dateRange",required = false) String dateRange) {
		ModelAndView mav = new ModelAndView("web/room");
		RoomDTO dto = new RoomDTO();
		dto.setDateRange(dateRange);
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
