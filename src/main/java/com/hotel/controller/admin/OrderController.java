package com.hotel.controller.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hotel.converter.AccountConverter;
import com.hotel.converter.RoomConverter;
import com.hotel.dto.AccountDTO;
import com.hotel.dto.OrderDTO;
import com.hotel.dto.RoomDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoomEntity;
import com.hotel.service.IAccountService;
import com.hotel.service.IOrderService;
import com.hotel.service.IOrderStatusService;
import com.hotel.service.IPromotionService;
import com.hotel.service.IRoomService;
import com.hotel.service.ITypeRoomService;
import com.hotel.util.PriceFormat;

@Controller
//@SessionAttributes("model")
public class OrderController {
	@Autowired
	private ITypeRoomService typeRoomService;

	@Autowired
	private IAccountService accountService;

	/*
	 * @Autowired private IStatusService statusService;
	 */

	@Autowired
	private IRoomService roomService;

	@Autowired
	private IPromotionService promotionService;

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderStatusService orderStatusService;
	
	@Autowired
	private PriceFormat priceFormat;

	private final int PAGE_COUNT = 3;
	private String dateRange;
	private Integer orderId;

	@RequestMapping(value = "/quan-tri/phieu-dat", method = RequestMethod.GET)
	private ModelAndView listOfOrder() {
		ModelAndView mav = new ModelAndView("/admin/order/list");
		OrderDTO model = new OrderDTO();
		model.setPage(1);
		model.setSearchValue("");
		model.setLimit(6);
		model.setCheckinDate(null);
		model.setCheckoutDate(null);
		mav.addObject("model", model);
		
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phieu-dat/tim-kiem", method = RequestMethod.POST)
	private ModelAndView search(@ModelAttribute("model") OrderDTO dto) {
		ModelAndView mav = new ModelAndView("/admin/order/search");
		// Format lai dinh dang ngay
		if (dto.getCheckinDate() != null) {
			dto.convertCheckInDate(dto.getCheckinDate().toString());
		}
		if (dto.getCheckoutDate() != null) {
			dto.convertCheckOutDate(dto.getCheckoutDate().toString());
		}
		System.out.println("Ngay check in: " + dto.getCheckinDate());
		Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());
		List<OrderDTO> orders = orderService.ListOfOrder(dto.getSearchValue(), dto.getCheckinDate(),
				dto.getCheckoutDate(), dto.getStatusId(), pageable);
	
		dto.setListResult(orders);
		dto.setTotalItem(orderService.count(dto.getSearchValue(), dto.getCheckinDate(), dto.getCheckoutDate(),
				dto.getStatusId()));
		
		System.out.println("tong item: " + dto.getTotalItem());
		dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
		System.out.println("so trang: " + dto.getTotalPage());
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phieu-dat/chinh-sua", method = RequestMethod.GET)
	private ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView mav = new ModelAndView("/admin/order/edit");
		OrderDTO orderDTO = orderService.findById(id);
		orderDTO.setOldPrice((float) 1);
		orderDTO.setNewPrice((float) 2);
		if (orderDTO != null) {
			// Them thuoc tinh description va quantity trong RoomDTO
			// set them description va quantity trong roomConverter
			
			
			// Them mot findAvailableRooms ko tham so trong repo, service
			List<RoomDTO> roomDTOs = roomService.findAvailableRooms(orderDTO.getRoomId());
			orderId = id;

			mav.addObject("listRoom", roomDTOs);
			mav.addObject("orderModel", orderDTO);
			 boolean isAllowPaid = orderService.isAllowPaid(orderDTO.getId());
			    boolean isAllowAccept = orderService.isAllowAccept(orderDTO.getId());
			    boolean isAllowCancel = orderService.isAllowCancel(orderDTO.getId());
			    mav.addObject("isSameType", 0);
			    mav.addObject("isAllowPaid", isAllowPaid);
			    mav.addObject("isAllowAccept", isAllowAccept);
			    mav.addObject("isAllowCancel", isAllowCancel);
		}
		return mav;
	}

	@RequestMapping(value = "/quan-tri/phieu-dat/doi-phong", method = RequestMethod.GET)
	private ModelAndView changeRoom(@RequestParam(value = "newId") Integer newId,
			@RequestParam("oldId") Integer oldId) {
		ModelAndView mav = new ModelAndView("/admin/order/room");

		RoomEntity newRoom = roomService.findById(newId);
		RoomEntity oldRoom = roomService.findById(oldId);

		OrderDTO orderDTO = orderService.findById(orderId);

		// Kiểm tra xem có phòng cũ có giống loại giống giống loại của phòng mới hay
		// không
		// Thêm hai trường oldPrice và newPrice trong OrderDTO
		if (newRoom.getTyperoom().getId() != oldRoom.getTyperoom().getId()) {
			Date currentDate = new Date();

			Float oldRoomPrice = oldRoom.getTyperoom().getPrice();
			long millisecondsPerDay = 1000 * 60 * 60 * 24;
			long daysBetween = (currentDate.getTime() - orderDTO.getCheckinDate().getTime()) / millisecondsPerDay;
			
			orderDTO.setOldPrice(daysBetween * oldRoomPrice);
			System.out.println("daybetween 1 : " + daysBetween);
			Float newRoomPrice = newRoom.getTyperoom().getPrice();
			daysBetween = ((orderDTO.getCheckoutDate().getTime() - currentDate.getTime()) / millisecondsPerDay) +1;
			
			System.out.println("daybetween 2 : " + daysBetween);
			orderDTO.setNewPrice(daysBetween * newRoomPrice);
			System.out.println("Gia cu: " + orderDTO.getOldPrice());
			System.out.println("Gia moi: " + orderDTO.getNewPrice());
			orderDTO.setTotalPrice(orderDTO.getNewPrice() + orderDTO.getOldPrice());
			mav.addObject("isSameType", 0);
		} else {
			orderDTO.setNewPrice((float)0);
			orderDTO.setOldPrice((float)0);
			mav.addObject("isSameType", 1);
		}
		mav.addObject("orderModel", orderDTO);
		RoomDTO roomDTO = RoomConverter.toDTO(newRoom);
		if(roomDTO.getImage().equals(""))
		{
			roomDTO.setImage("/template/admin/img/room/notphoto.png");
			/*
			 * String format = priceFormat.format(roomDTO.getPrice());
			 * roomDTO.setPriceFormat(format);
			 */
		}
		mav.addObject("roomModel", roomDTO);
		return mav;
	}
	@RequestMapping(value = "/quan-tri/phieu-dat/khach-hang", method = RequestMethod.GET)
	private ModelAndView loadCustumer(@RequestParam(value="customerId") Integer id) {
		ModelAndView mav = new ModelAndView("/admin/order/customer");
		AccountEntity customerEntity = accountService.findById(id);
		AccountDTO customerDTO = AccountConverter.toDTO(customerEntity);
		if(customerDTO.getImage().equals("")) {
			customerDTO.setImage("/template/admin/img/account/notphoto.png");
		}
		mav.addObject("customerModel", customerDTO);
		return mav;
	}
	@RequestMapping(value = "/quan-tri/phieu-dat/cap-nhat-trang-thai", method = RequestMethod.GET)
	private ModelAndView updateStatus(@RequestParam(value = "orderId") Integer orderId, @RequestParam(value = "statusId") Integer statusId) {
		orderService.updateStatus(orderId, statusId);
		return new ModelAndView("redirect:/quan-tri/phieu-dat/chinh-sua?id=" + orderId.toString());
	}
	@RequestMapping(value = "/quan-tri/phieu-dat/luu", method = RequestMethod.POST)
	private ModelAndView updateOrder(@ModelAttribute("orderModel") @Valid OrderDTO dto, BindingResult result) {
		// Format lai dinh dang ngay
		if (dto.getCheckinDate() != null) {
			dto.convertCheckInDate(dto.getCheckinDate().toString());
		}
		if (dto.getCheckoutDate() != null) {
			dto.convertCheckOutDate(dto.getCheckoutDate().toString());
		}

		if (dto.getId() == null) {
			System.out.println("Null..");
		}
	/*	System.out.println("Check-in Date: " + dto.getCheckinDate());
		System.out.println("Checkout Date: " + dto.getCheckoutDate());
		System.out.println("Quantity: " + dto.getQuantity());
		System.out.println("Total Price: " + dto.getTotalPrice());
		System.out.println("Status: " + dto.getStatusId());
		System.out.println("Customer ID: " + dto.getCustomerId());
		System.out.println("Employee ID: " + dto.getEmployeeId());
		System.out.println("Promotion Level: " + dto.getPromotionLevel());
		System.out.println("Room ID: " + dto.getRoomId());
		System.out.println("Promotion ID: " + dto.getPromotionId());
		System.out.println("Old Price: " + dto.getOldPrice());
		System.out.println("New Price: " + dto.getNewPrice());*/
		boolean checkEqualDate = dto.getCheckinDate().toString().equals(dto.getCheckoutDate().toString());
		System.out.println(checkEqualDate);
		if (result.hasErrors() || checkEqualDate) {
			if(checkEqualDate)
			{
				result.rejectValue("checkinDate", "", "Không được đặt phòng trong 1 ngày");
			}
			System.out.println("Loi");
			ModelAndView mav = new ModelAndView("/admin/order/edit");
			List<RoomDTO> roomDTOs = roomService.findAvailableRooms(dto.getRoomId());
			mav.addObject("orderModel", dto);
			mav.addObject("isSameType", 0);
			mav.addObject("listRoom", roomDTOs);
			return mav;
		} else {
			System.out.println("Khong Loi");
			orderService.updateOrder(dto);
			ModelAndView mav = new ModelAndView("redirect:/quan-tri/phieu-dat");
			return mav;
		}
	}

	@RequestMapping(value = "/quan-tri/phieu-dat/xoa", method = RequestMethod.GET)
	private ModelAndView deleteOrder(@RequestParam(value = "id", required = false) Integer id,
			RedirectAttributes redirectAttributes) {
		int result = orderService.delete(id);
		if (result != 0) {
			ModelAndView mav = new ModelAndView("redirect:/quan-tri/phieu-dat");
			return mav;
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Không thể xóa phiếu đặt đã thanh toán!");
			return new ModelAndView("redirect:/quan-tri/phieu-dat");
		}

	}

	@RequestMapping(value = "/quan-tri/dat-phong", method = RequestMethod.GET)
	private ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/order/index");
		RoomDTO dto = new RoomDTO();
		dto.setPage(1);
		dto.setSearchValue("");
		dto.setLimit(PAGE_COUNT);
		dto.setTotalPage(0);
		LocalDate today = LocalDate.now();
		LocalDate futureMonth = today.plusMonths(1);

		// Định dạng ngày theo yyyy/MM/dd
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		dateRange = today.format(formatter) + " -  " + futureMonth.format(formatter);
		dto.setDateRange(dateRange);
		mav.addObject("roomModel", dto);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setQuantity(1);
		mav.addObject("orderModel", orderDTO);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/dat-phong/danh-sach-phong", method = RequestMethod.POST)
	private ModelAndView listOfRoom(@ModelAttribute("roomModel") RoomDTO dto) {
		ModelAndView mav = new ModelAndView("admin/order/listroom");
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();
		Pageable pageable = new PageRequest(dto.getPage() - 1, dto.getLimit());
		System.out.println("***So luong: " + dto.getQuantity());
		rooms = roomService.findAvailableRooms( dto.getQuantity(), dto.getTypeid(),
				dto.getSearchValue(), pageable);
		
		dto.setListResult(rooms);
		dto.setTotalItem(roomService.countAvailableRooms( dto.getQuantity(),
				dto.getTypeid(), dto.getSearchValue()));
		dto.setTotalPage((int) Math.ceil((double) dto.getTotalItem() / dto.getLimit()));
		
		return mav;
	}

	@RequestMapping(value = "/quan-tri/dat-phong/luu", method = RequestMethod.POST)
	private ModelAndView addOrder(@ModelAttribute("orderModel") @Valid OrderDTO dto, BindingResult result) {
		// Format lai dinh dang ngay
		if (dto.getCheckinDate() != null) {
			dto.convertCheckInDate(dto.getCheckinDate().toString());
		}
		if (dto.getCheckoutDate() != null) {
			dto.convertCheckOutDate(dto.getCheckoutDate().toString());
		}
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println("Trường: " + error.getField());
				System.out.println("Thông điệp lỗi: " + error.getDefaultMessage());
			}
			ModelAndView mav = new ModelAndView("admin/order/index");
			RoomDTO roomDTO = new RoomDTO();
			roomDTO.setPage(1);
			roomDTO.setSearchValue("");
			roomDTO.setLimit(PAGE_COUNT);
			roomDTO.setTotalPage(0);
			LocalDate today = LocalDate.now();
			LocalDate futureMonth = today.plusMonths(1);

			// Định dạng ngày theo yyyy/MM/dd
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

			dateRange = today.format(formatter) + " -  " + futureMonth.format(formatter);
			roomDTO.setDateRange(dateRange);
			mav.addObject("roomModel", roomDTO);
			mav.addObject("orderModel", dto);
			return mav;
		} else {

			try {
				orderService.addOrder(dto);
				ModelAndView mav = new ModelAndView("redirect:/quan-tri/phieu-dat");
				return mav;
			} catch (Exception e) {
				System.out.println("Message" + e.getMessage());
			}
		}

		return null;

	}

	@RequestMapping(value = "/renderTotalPrice", method = RequestMethod.GET)
	@ResponseBody
	private Float renderTotalPrice(@RequestParam(value = "checkinDate", required = false) Date checkinDate,
			@RequestParam(value = "checkoutDate", required = false) Date checkoutDate) {
		try {
			return (float) 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/updateTotalPrice", method = RequestMethod.GET)
	private Float updateTotalPrice(@RequestParam(value = "value", required = false) String value) {
		return null;
	}

	@ModelAttribute("typerooms")
	public Map<Integer, String> getAllType() {
		return typeRoomService.findAll();
	}

	 @ModelAttribute("orderStatusMap")
	    public Map<Integer, String> getAllStatus() {
	        Map<Integer, String> orderStatusMap = new HashMap<>();
	        orderStatusMap.put(1, "Đã thanh toán");
	        orderStatusMap.put(2, "Chưa thanh toán");
	        orderStatusMap.put(3, "Hủy");
	        orderStatusMap.put(4, "Chờ duyệt");
	        orderStatusMap.put(5, "Duyệt");
	        return orderStatusMap;
	    }
	 @ModelAttribute("quantitys")
	    public Map<Integer, String> getAllQuantity() {
	        Map<Integer, String> quantitysMap = new HashMap<>();
	        quantitysMap.put(1, "1 người");
	        quantitysMap.put(2, "2 người");
	        quantitysMap.put(3, "3 người");
	        quantitysMap.put(4, "4 người");
	        quantitysMap.put(5, "5 người");
	        return quantitysMap;
	    }
	@ModelAttribute("promotions")
	public Map<Integer, String> getAllPromotion() {
		return promotionService.findAvailblePromotions();
	}

	@ModelAttribute("orderStatuses")
	public Map<Integer, String> getAllOrderStatus() {
		return orderStatusService.findAll();
	}
}
