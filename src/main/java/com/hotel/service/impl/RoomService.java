package com.hotel.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.constant.SystemConstant;
import com.hotel.converter.RoomConverter;
import com.hotel.dto.RoomDTO;
import com.hotel.entity.RoomEntity;
import com.hotel.repository.RoomRepository;
import com.hotel.service.IRoomService;
import com.hotel.service.IStatusService;

@Service
public class RoomService implements IRoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private IStatusService statusService;

	@Override
	public RoomEntity findById(int id) {
		return roomRepository.findById(id);
	}

	@Override
	public List<RoomDTO> listOfRoom(int typeId, String searchValue, Pageable pageable) {

		searchValue = "%" + searchValue + "%";
		List<RoomEntity> entitys = roomRepository.listOfRoom(typeId, searchValue, pageable);
		List<RoomDTO> dtos = new ArrayList<>();
		for (RoomEntity entity : entitys) {
			RoomDTO dto = new RoomDTO();
			dto = RoomConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public int count(int typeId, String searchValue) {
		searchValue = "%" + searchValue + "%";
		return roomRepository.count(typeId, searchValue);
	}

	@Override
	public void updateRoom(RoomDTO dto) {
		RoomEntity entity = new RoomEntity();
		if (dto.getId() != null) {
			entity = roomRepository.findById(dto.getId());
		}

		if (entity != null) {
			// Cập nhật thông tin của entity từ dto
			entity = RoomConverter.toEntity(dto, entity);
			// Lưu thay đổi vào cơ sở dữ liệu
			roomRepository.save(entity);

		} else {
			// Tạo mới entity từ dto
			entity = RoomConverter.toEntity(dto);

			// Lưu mới vào cơ sở dữ liệu
			roomRepository.save(entity);

		}
	}

	@Override
	public void deleteRoom(int id) {
		RoomEntity entity = roomRepository.findById(id);
		if (entity != null) {
			roomRepository.delete(entity.getId());
		} else {
			// Phòng không tồn tại trong cơ sở dữ liệu
			throw new IllegalArgumentException("Xoá không được.");
		}
	}

	@Override
	public List<RoomDTO> findAvailableRooms( int quantity, int typeId,
			String searchValue, Pageable pageable) {
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		searchValue = "%" + searchValue + "%";
		List<RoomEntity> entitys = roomRepository.findAvailableRooms(quantity, typeId,
				searchValue, pageable);
		if (entitys != null) {
			for (RoomEntity entity : entitys) {
				RoomDTO roomdto = RoomConverter.toDTO(entity);
				List<String> listCheckinDate = roomRepository.findCheckInDateByRoomId(roomdto.getId());
				List<String> listCheckoutDate = roomRepository.findCheckOutDateByRoomId(roomdto.getId());
	
				roomdto.setListCheckinDates(listCheckinDate);
				roomdto.setListCheckoutDates(listCheckoutDate);
				dtos.add(roomdto);
			}
		}

		return dtos;
	}
	@Override
	public List<RoomDTO> findAvailableRooms(Integer id) {
		List<RoomDTO> dtos = new ArrayList<RoomDTO>();
		List<RoomEntity> entitys = roomRepository.findAvailableRooms(id);
		if (entitys != null) {
			for (RoomEntity entity : entitys) {
				dtos.add(RoomConverter.toDTO(entity));
			}
		}

		return dtos;
	}
	@Override
	public int countAvailableRooms( int quantity, int typeId, String searchValue) {
		searchValue = "%" + searchValue + "%";
		return roomRepository.countAvailableRooms(quantity, typeId, searchValue);
	}

	@Override
	public void updateRoomStatus(Integer id) {
		RoomEntity entity = findById(id);
		if(entity == null) {
			System.out.println("loi");
			return;
		}
		entity.setStatus(statusService.findById(SystemConstant.UNAVAILABLE));
		roomRepository.save(entity);
	}

	@Override
	public int CountRoomFree(Integer typeroomid) {
		// TODO Auto-generated method stub
		return roomRepository.CountRoomFree(typeroomid);
	}

	@Override
	public Integer countAvailableRoomWebs(Date checkinDate, Date checkoutDate, int typeId) {
		// TODO Auto-generated method stub
		return roomRepository.countAvailableRoomWebs(checkinDate, checkoutDate, typeId);
	}

	@Override
	public int getTotalItem() {
		return (int) roomRepository.count();
	}

	@Override
	public RoomDTO findOneAvailableRoom(Date checkinDate, Date checkoutDate, int typeId) {
		// TODO Auto-generated method stub
		//láy kết quả đầu tiên 

		List<RoomEntity> dtos = roomRepository.findOneAvailableRoom(checkinDate, checkoutDate, typeId);
		 if (dtos.isEmpty()) {
		        return null; // hoặc trả về một đối tượng RoomDTO mặc định
		    }

		RoomDTO dto = RoomConverter.toDTO(dtos.get(0));
		return dto;
	}




}
