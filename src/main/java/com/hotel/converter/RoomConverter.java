package com.hotel.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dto.RoomDTO;
import com.hotel.entity.RoomEntity;
import com.hotel.entity.StatusEntity;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.service.impl.StatusService;
import com.hotel.service.impl.TypeRoomService;

@Component
public class RoomConverter {
	private static TypeRoomService typeRoomService;
	private static StatusService statusService;

	@Autowired
	public RoomConverter(TypeRoomService typeRoomService, StatusService statusService) {
		RoomConverter.typeRoomService = typeRoomService;
		RoomConverter.statusService = statusService;
	}

	public static RoomDTO toDTO(RoomEntity entity) {
		RoomDTO dto = new RoomDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
	
		dto.setTypename(entity.getTyperoom().getName());
		dto.setPrice(entity.getTyperoom().getPrice());
		dto.setRatequantity(entity.getRatequantity());
		dto.setDescription(entity.getTyperoom().getDescription());
		dto.setQuantity(entity.getTyperoom().getQuantity());
		dto.setPrice(entity.getTyperoom().getPrice());
		dto.setStatusname(entity.getStatus().getStatusname());
		dto.setTypeid(entity.getTyperoom().getId());
		dto.setStatusid(entity.getStatus().getId());
		if(dto.getImage().equals("")) {
			dto.setImage("/template/admin/img/room/notphoto.png");
		}
		return dto;
	}

	public static RoomEntity toEntity(RoomDTO dto) {
		 RoomEntity entity = new RoomEntity();
		    entity.setName(dto.getName());
		    entity.setRatequantity(dto.getRatequantity());
		    entity.setImage(dto.getImage());

		    // Update thong tin loai phong vao phong
		    // Trước tiên, kiểm tra xem dto có chứa ID của TypeRoom không
		    if (dto.getTypeid() != null) {
		        TypeRoomEntity typeRoom = typeRoomService.findById(dto.getTypeid());
		        entity.setTyperoom(typeRoom);
		    }

		    // Update thong tin trang thai vao phong
		    // Trước tiên, kiểm tra xem dto có chứa ID của Status không
		    if (dto.getStatusid() != null) {
		        StatusEntity status = statusService.findById(dto.getStatusid());
		        entity.setStatus(status);
		    }

		    return entity;
	}
	public static RoomEntity toEntity(RoomDTO dto, RoomEntity entity) {
		    entity.setName(dto.getName());
		    entity.setRatequantity(dto.getRatequantity());
		    entity.setImage(dto.getImage());

		    // Update thong tin loai phong vao phong
		    // Trước tiên, kiểm tra xem dto có chứa ID của TypeRoom không
		    if (dto.getTypeid() != null) {
		        TypeRoomEntity typeRoom = typeRoomService.findById(dto.getTypeid());
		        entity.setTyperoom(typeRoom);
		    }

		    // Update thong tin trang thai vao phong
		    // Trước tiên, kiểm tra xem dto có chứa ID của Status không
		    if (dto.getStatusid() != null) {
		        StatusEntity status = statusService.findById(dto.getStatusid());
		        entity.setStatus(status);
		    }

		    return entity;
	}
}
