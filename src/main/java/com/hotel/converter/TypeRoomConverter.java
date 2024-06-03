package com.hotel.converter;

import org.springframework.stereotype.Component;

import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;

@Component
public class TypeRoomConverter {
	public static TypeRoomDTO toDTO (TypeRoomEntity entity) {
		TypeRoomDTO dto = new TypeRoomDTO();
		if(entity.getId() != null) {
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setPrice(entity.getPrice());
			dto.setQuantity(entity.getQuantity());
			dto.setRooms(entity.getRooms());
			dto.setImage(entity.getImage());
			dto.setSpace(entity.getSpace());
			dto.setDescription(entity.getDescription());
		}
		return dto;
	}

	//tạo mới nên cần new entity mới
	public static TypeRoomEntity toEntity(TypeRoomDTO dto) {
		TypeRoomEntity entity = new TypeRoomEntity();
		//tạo mới entity
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		entity.setRooms(dto.getRooms());
		entity.setImage(dto.getImage());
		entity.setSpace(dto.getSpace());
		entity.setDescription(dto.getDescription());
	    return entity;
	}
	
	public static TypeRoomEntity toEntity(TypeRoomDTO dto, TypeRoomEntity entity) {
		//chuyển đổi dto thành entity
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		entity.setRooms(dto.getRooms());
		entity.setImage(dto.getImage());
		entity.setSpace(dto.getSpace());
		entity.setDescription(dto.getDescription());
	    return entity;
	}
}
