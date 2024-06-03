package com.hotel.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.RoomDTO;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;

public interface ITypeRoomService {
	List<TypeRoomEntity> findAllRoom();
	
	Map<Integer, String> findAll();
	TypeRoomEntity findById(int id);

	int getTotalItem(String name,int quantity);
	List<TypeRoomDTO> findByNameContaining(String name,int quantity,Pageable pageable);
	
	void updateTypeRoom(TypeRoomDTO dto);
	void deleteTypeRoom(int id);
	
	
}
