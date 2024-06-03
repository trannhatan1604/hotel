package com.hotel.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.RoomDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.service.IFeedBackService;
import com.hotel.service.impl.TypeRoomService;
import com.hotel.util.MessageUtil;
import com.hotel.util.PriceFormat;
import com.hotel.util.SaveFile;

@Controller
public class TypeRoomController {
	
	@Autowired
	private TypeRoomService typeroomService;
	
	@Autowired
	private IFeedBackService feedBackService;
	
	@Autowired
	private PriceFormat priceFormat;
	
	@Autowired 
	private MessageUtil messageUtil;
	
	@Autowired
	private SaveFile multipartFile;
	
	@RequestMapping(value = "/quan-tri/loai-phong", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/typeroom/index");
		RoomDTO model = new RoomDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(6);
		mav.addObject("model", model);
		/*
		 * //hiển thị feedback lên header List<FeedBackDTO> dtoes =
		 * feedBackService.findByStatus(); mav.addObject("feedbacks", dtoes);
		 * mav.addObject("countfeedback", dtoes.size());
		 */
		return mav;
	}
	@RequestMapping(value = "/quan-tri/loai-phong/tim-kiem", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") TypeRoomDTO dto, @RequestParam(value="quantity", required = false) int quantity) {
	    dto.setLimit(dto.getLimit());
	    dto.setPage(dto.getPage());
	    dto.setSearchValue(dto.getSearchValue());
	    Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());
	    List<TypeRoomDTO> dtos = typeroomService.findByNameContaining(dto.getSearchValue(), quantity, pageable);
	    //định dạng giá tiền
	    for (TypeRoomDTO typeRoomDTO : dtos) {
	    	typeRoomDTO.setPriceFormat(priceFormat.format(typeRoomDTO.getPrice())+" đ");
		}
	    dto.setListResult(dtos);
		dto.setTotalItem(typeroomService.getTotalItem(dto.getSearchValue(), quantity));
		dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));

	    ModelAndView mav = new ModelAndView("admin/typeroom/search");
	    return mav;
	}
	
	@RequestMapping(value = "/quan-tri/loai-phong/them", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("admin/typeroom/edit");
		TypeRoomDTO dto = new TypeRoomDTO();
		dto.setId(null);
		mav.addObject("model", dto);
		return mav;
	}
	@RequestMapping(value = "/quan-tri/loai-phong/chinh-sua", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("admin/typeroom/edit");
		TypeRoomDTO dto = new TypeRoomDTO();
		TypeRoomEntity entity = new TypeRoomEntity();
		if (id != null) {
			entity = typeroomService.findById(id);
			dto = TypeRoomConverter.toDTO(entity);
			dto.setPriceFormat(priceFormat.format(dto.getPrice()));
		}
		mav.addObject("model", dto);
		return mav;
	}
	@RequestMapping(value = "/quan-tri/loai-phong/luu", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("model") @Valid TypeRoomDTO dto, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		if(dto.getPriceFormat().isEmpty())
		{
			dto.setPriceFormat("0");
		}
		dto.setPriceFormat(dto.getPriceFormat());
		if (dto.getPhoto() == null || dto.getPhoto().isEmpty()) {
			dto.setImage(dto.getImage());
		} else {
			String image = "/template/admin/img/room/" + multipartFile.saveFile(dto.getPhoto());
			dto.setImage(image);
		}
		boolean checkInt = true;
		String priceWithoutDot = "";
		//đổi lại String price về float
		if(!dto.getPriceFormat().isEmpty()) {
			priceWithoutDot = dto.getPriceFormat().replaceAll("\\.", "");
			//check xem phải số không
			checkInt = isInteger(priceWithoutDot);
		}
		if (result.hasErrors() || dto.getPrice() < 0 || dto.getPriceFormat().equals("") || dto.getPriceFormat().equals("0") || dto.getSpace() > 50
				|| !checkInt || Float.parseFloat(priceWithoutDot)<100000) {
			
			if(!checkInt)
				result.rejectValue("priceFormat", "", "Giá phải là kiểu số");
			if(dto.getPrice() < 0)
				result.rejectValue("price", "negativeValue", "Giá loại phòng không được âm");
			if(dto.getPriceFormat().equals("") || dto.getPriceFormat().equals("0"))
				result.rejectValue("priceFormat", "notEmpty", "Vui lòng nhập giá loại phòng");
			if(dto.getSpace() > 50)
				result.rejectValue("space", "", "Phòng rộng tối đa 50m2");
			if(Float.parseFloat(priceWithoutDot)<100000)
				result.rejectValue("price", "", "Giá phòng thấp nhất là 100.000Đ");
			mav.setViewName("admin/typeroom/edit");
			mav.addObject("model", dto);
			return mav;
		} else {
			dto.setPrice(Float.parseFloat(priceWithoutDot));
			typeroomService.updateTypeRoom(dto);
			mav.setViewName("redirect:/quan-tri/loai-phong");
		}
		return mav;
		/*
		 * //lấy lỗi về Map<String, String> message =
		 * messageUtil.getMessageTypeRoom(dto); modelAndView.addObject("message",
		 * message.get("message")); modelAndView.addObject("alert",
		 * message.get("alert"));
		 * 
		 * if(message.get("alert").equals("success")) {
		 * typeroomService.updateTypeRoom(dto);
		 * modelAndView.setViewName("redirect:/quan-tri/loai-phong"); } else {
		 * modelAndView.setViewName("admin/typeroom/edit"); } return modelAndView;
		 */
	}
	@RequestMapping(value = "/quan-tri/loai-phong/xoa", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name = "id", required = false) Integer id) {
		typeroomService.deleteTypeRoom(id);
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("redirect:/quan-tri/loai-phong");
	    return modelAndView;
	}
	
	 public static boolean isInteger(String str) {
	        if (str == null) {
	            return false;
	        }
	        try {
	            Integer.parseInt(str);
	        } catch (NumberFormatException e) {
	            return false;
	        }
	        return true;
	    }
}
