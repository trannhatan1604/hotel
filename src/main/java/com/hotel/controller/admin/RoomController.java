package com.hotel.controller.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.converter.RoomConverter;
import com.hotel.dto.RoomDTO;
import com.hotel.entity.RoomEntity;
import com.hotel.entity.StatusEntity;
import com.hotel.service.IFeedBackService;
import com.hotel.service.IRoomService;
import com.hotel.service.ITypeRoomService;
import com.hotel.service.impl.StatusService;
import com.hotel.util.MessageUtil;
import com.hotel.util.PriceFormat;
import com.hotel.util.SaveFile;

@Controller
public class RoomController {
	@Autowired
	private IRoomService roomService;

	@Autowired
	private ITypeRoomService typeRoomService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private IFeedBackService feedBackService;

	@Autowired
	private SaveFile multipartFile;

	@Autowired
	private PriceFormat priceFormat;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/phong", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/room/index");
		RoomDTO model = new RoomDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(6);
		mav.addObject("model", model);
		// lấy feedback hiển thị ở header
		/*
		 * List<FeedBackDTO> dtoes = feedBackService.findByStatus();
		 * mav.addObject("feedbacks", dtoes); mav.addObject("countfeedback",
		 * dtoes.size());
		 */
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phong/tim-kiem", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("model") RoomDTO dto) {
		dto.setLimit(dto.getLimit());
		dto.setPage(dto.getPage());
		dto.setSearchValue(dto.getSearchValue());
		dto.setTypeid(dto.getTypeid());
		ModelAndView mav = new ModelAndView("admin/room/search");

		Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());

		List<RoomDTO> rooms = roomService.listOfRoom(dto.getTypeid(), dto.getSearchValue(), pageable);
		for (RoomDTO roomDTO : rooms) {
			if (roomDTO.getImage() == null || roomDTO.getImage().isEmpty()) {
				roomDTO.setImage("/template/admin/img/room/notphoto.png");
			}
			roomDTO.setPriceFormat(priceFormat.format(roomDTO.getPrice()) + " đ");
		}

		dto.setListResult(rooms);
		dto.setTotalItem(roomService.count(dto.getTypeid(), dto.getSearchValue()));
		dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));

		return mav;
	}

	@RequestMapping(value = "/quan-tri/phong/them", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("admin/room/edit");
		RoomDTO dto = new RoomDTO();
		dto.setId(null);
		dto.setImage("/template/admin/img/room/notphoto.png");
		mav.addObject("model", dto);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phong/chinh-sua", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(name = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("admin/room/edit");
		RoomDTO dto = new RoomDTO();
		RoomEntity entity = new RoomEntity();
		// lấy id về để hiển thị qua trang sửa
		if (id != null) {
			entity = roomService.findById(id);
			dto = RoomConverter.toDTO(entity);
			if (dto.getImage() == null || dto.getImage().isEmpty()) {
				dto.setImage("/template/admin/img/room/notphoto.png");
			}
		}
		
		mav.addObject("model", dto);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phong/xoa", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(name = "id", required = false) Integer id) {
		roomService.deleteRoom(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/quan-tri/phong");
		return modelAndView;
	}

	// ModelMap : một lớp truyền từ controller tới view
	@RequestMapping(value = "/quan-tri/phong/luu", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("model") @Valid RoomDTO dto, BindingResult result, ModelMap modelMap) {
		ModelAndView mav = new ModelAndView();
		if (dto.getPhoto() == null || dto.getPhoto().isEmpty()) {
			dto.setImage(dto.getImage());
		} else {
			String image = "/template/admin/img/room/" + multipartFile.saveFile(dto.getPhoto());
			dto.setImage(image);
		}
		if (result.hasErrors()) {
			System.out.println(result.hasErrors());
			mav.setViewName("admin/room/edit");
			mav.addObject("model", dto);
			return mav;
		} else {
			System.out.println("Khong loi");
			roomService.updateRoom(dto);
			mav.setViewName("redirect:/quan-tri/phong");
		}
		return mav;
		// báo lỗi
		/*
		 * Map<String, String> message = messageUtil.getMessageRoom(dto);
		 * modelAndView.addObject("message", message.get("message"));
		 * modelAndView.addObject("alert", message.get("alert"));
		 * if(message.get("alert").equals("success")) { roomService.updateRoom(dto);
		 * modelAndView.setViewName("redirect:/quan-tri/phong");
		 * 
		 * } else { modelAndView.setViewName("admin/room/edit");
		 * 
		 * }
		 */

	}

	@ModelAttribute("typerooms")
	public Map<Integer, String> getAllType() {
		return typeRoomService.findAll();
	}

	@ModelAttribute("statuses")
	public Map<Integer, String> getAllStatus() {
		return statusService.findAll();
	}

}
