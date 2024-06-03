package com.hotel.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dto.OrderDTO;
import com.hotel.entity.OrderEntity;
import com.hotel.service.impl.AccountService;
import com.hotel.service.impl.OrderStatusService;
import com.hotel.service.impl.PromotionService;
import com.hotel.service.impl.RoomService;

@Component
public class OrderConverter {
	private static AccountService accountService;
	private static RoomService roomService;
	private static PromotionService promotionService;
	private static OrderStatusService orderStatusService;

	@Autowired
	public OrderConverter(AccountService accountService, RoomService roomService, PromotionService promotionService,
			OrderStatusService orderStatusService) {
		OrderConverter.accountService = accountService;
		OrderConverter.roomService = roomService;
		OrderConverter.promotionService = promotionService;
		OrderConverter.orderStatusService = orderStatusService;
	}

	public static OrderDTO toDTO(OrderEntity entity) {
		if (entity == null) {
			return null; // hoặc xử lý nếu cần thiết
		}
		OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setRoomId(entity.getRoom().getId());
		dto.setCustomerId(entity.getCustomerId());
		dto.setCheckinDate(entity.getCheckinDate());
		dto.setCheckoutDate(entity.getCheckoutDate());
		dto.setStatusId(entity.getStatus().getId());

		dto.setCustomer(accountService.findById(entity.getCustomerId()));
		if (entity.getAccount() != null) {
			dto.setEmployee(entity.getAccount());
			dto.setEmployeeId(entity.getAccount().getId());
		}
		dto.setTotalPrice(entity.getTotalPrice());
		if (entity.getPromotion() != null) {
			dto.setPromotionLevel(entity.getPromotion().getLevel());
			dto.setPromotionId(entity.getPromotion().getId());
		} else {
			dto.setPromotionLevel(0);
			dto.setPromotionId(null); // Hoặc giá trị mặc định khác tùy vào logic của bạn
		}
		dto.setQuantity(entity.getQuantity());
		dto.setRoomName(entity.getRoom().getName());
		return dto;
	}

	public static OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		entity.setAccount(accountService.findById(dto.getEmployeeId()));
		entity.setPromotion(promotionService.findEntityById(dto.getPromotionId()));
		entity.setRoom(roomService.findById(dto.getRoomId()));
		entity.setCheckinDate(dto.getCheckinDate());
		entity.setCheckoutDate(dto.getCheckoutDate());
		entity.setTotalPrice(dto.getTotalPrice());
		entity.setStatus(orderStatusService.findById(dto.getStatusId()));
		entity.setQuantity(dto.getQuantity());
		entity.setCustomerId(dto.getCustomerId());
		// Set trạng thái phòng thành bận
		roomService.updateRoomStatus(dto.getRoomId());
		return entity;
	}

	public static OrderEntity toEntity(OrderDTO dto, OrderEntity entity) {
		if(dto.getEmployeeId() != null) {
			entity.setAccount(accountService.findById(dto.getEmployeeId()));
		}
		else {
			entity.setAccount(null);
		}
		entity.setPromotion(promotionService.findEntityById(dto.getPromotionId()));
		entity.setRoom(roomService.findById(dto.getRoomId()));
		entity.setCheckinDate(dto.getCheckinDate());
		entity.setCheckoutDate(dto.getCheckoutDate());
		entity.setTotalPrice(dto.getTotalPrice());
		entity.setStatus(orderStatusService.findById(dto.getStatusId()));
		entity.setQuantity(dto.getQuantity());
		entity.setCustomerId(dto.getCustomerId());
		// Set trạng thái phòng thành bận
		roomService.updateRoomStatus(dto.getRoomId());
		return entity;
	}
}
