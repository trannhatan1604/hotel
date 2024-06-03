package com.hotel.service;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.RoomDTO;
import com.hotel.entity.RoomEntity;

public interface IRoomService {
	List<RoomDTO> listOfRoom(int typeId, String searchValue, Pageable pageable);

	int getTotalItem();

	RoomEntity findById(int id);

	int count(int typeId, String searchValue);

	int countAvailableRooms(int quantity, int typeId, String searchValue);

	void updateRoom(RoomDTO roomDTO);

	void deleteRoom(int id);

	List<RoomDTO> findAvailableRooms(int quantity, int typeId, String searchValue, Pageable pageable);

	List<RoomDTO> findAvailableRooms(Integer id);

	void updateRoomStatus(Integer id);

	int CountRoomFree(Integer typeroomid);

	Integer countAvailableRoomWebs(Date checkinDate, Date checkoutDate, int typeId);

	RoomDTO findOneAvailableRoom(Date checkinDate, Date checkoutDate, int typeId);
}
