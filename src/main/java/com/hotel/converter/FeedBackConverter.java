package com.hotel.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.constant.SystemConstant;
import com.hotel.dto.FeedBackDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.FeedBackEntity;
import com.hotel.entity.RoomEntity;
import com.hotel.service.IAccountService;
import com.hotel.service.IRoleService;
import com.hotel.service.IRoomService;

@Component
public class FeedBackConverter {

	private static IRoomService roomService;
	private static IAccountService accountService;
	
	@Autowired
	public FeedBackConverter(IRoomService roomService, IAccountService accountService) {
		FeedBackConverter.roomService = roomService;
		FeedBackConverter.accountService=accountService;
	}
	
	public static FeedBackDTO toDTO(FeedBackEntity entity) {
		FeedBackDTO dto = new FeedBackDTO();

		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setId(entity.getId());
		
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus());
		dto.setAccount(entity.getAccount());
		dto.setRoom(entity.getRoom());
		dto.setAccountName(entity.getAccount().getFullName());
		dto.setRoomName(entity.getRoom().getName ());
		dto.setRoomId(entity.getRoom().getId());

		return dto;
	}
	//tạo mới feedback
	public static FeedBackEntity toEntity(FeedBackDTO dto) {
		FeedBackEntity entity = new FeedBackEntity();
		entity.setDescription(dto.getDescription());
		entity.setStatus(1);
		entity.setAccount(dto.getAccount());
		RoomEntity room = roomService.findById(dto.getRoomId());

		entity.setRoom(room);
		AccountEntity account = accountService.findById(dto.getAccountId());
		entity.setAccount(account);
		entity.setStatus(SystemConstant.HABNDL);
		entity.setOrderId(dto.getOrderId());
		return entity;
	}
	//cập nhật feedback
	public static FeedBackEntity toEntity( FeedBackDTO dto, FeedBackEntity entity)
	{
		entity.setDescription(dto.getDescription());
		entity.setStatus(dto.getStatus());
		entity.setRoom(dto.getRoom());
		entity.setAccount(dto.getAccount());
		return entity;
	}
}
