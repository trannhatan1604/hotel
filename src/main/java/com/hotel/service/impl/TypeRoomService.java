package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.TypeRoomRespository;
import com.hotel.service.ITypeRoomService;

@Service
public class TypeRoomService implements ITypeRoomService {

	@Autowired
	private TypeRoomRespository typeRoomRespository;

	@Autowired
	private RoomRepository roomRespository;
	
	@Override
	public Map<Integer, String> findAll() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		List<TypeRoomEntity> entities = typeRoomRespository.findAll();
		for (TypeRoomEntity item : entities) {
			result.put(item.getId(), item.getName());
		}
		return result;
	}

	@Override
	public TypeRoomEntity findById(int id) {
		// TODO Auto-generated method stub
		return typeRoomRespository.findById(id);
	}

	//lấy tổng các row
	@Override
	public int getTotalItem(String name,int quantity) {
		
		return typeRoomRespository.getTotalItem(quantity, name);
	}

	@Override
	public List<TypeRoomDTO> findByNameContaining(String name,int quantity,Pageable pageable) {
		String searchValue = "%"+name+"%";
		List<TypeRoomEntity> entitys = typeRoomRespository.listOfRoom(quantity,searchValue, pageable);
		List<TypeRoomDTO> dtos = new ArrayList<TypeRoomDTO>();
		for(TypeRoomEntity entity:  entitys) {
				TypeRoomDTO dto = new TypeRoomDTO();
				dto = TypeRoomConverter.toDTO(entity);
				dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void updateTypeRoom(TypeRoomDTO dto) {
		TypeRoomEntity entity = new TypeRoomEntity();
		 System.out.println("Id: " + dto.getId());
		    if (dto.getId() != null) {
		        entity = typeRoomRespository.findById(dto.getId());
		        System.out.println("Entity ID:" + entity);
		    }

		    if (entity != null) {
		        // Cập nhật thông tin của entity từ dto
		        entity = TypeRoomConverter.toEntity(dto,entity);
		        System.out.println("Entity ID:" + entity.getId());
		        // Lưu thay đổi vào cơ sở dữ liệu
		        typeRoomRespository.save(entity);
		    } else {
		        // Tạo mới entity từ dto
		        entity = TypeRoomConverter.toEntity(dto);
		        // Lưu mới vào cơ sở dữ liệu
		        typeRoomRespository.save(entity);
		    }
		
	}

	@Override
	public void deleteTypeRoom(int id) {
		System.out.println(id);
		TypeRoomEntity entity = typeRoomRespository.findById(id);
		if (entity!=null) {
			roomRespository.deleteRoomByTypeId(id);
			typeRoomRespository.delete(id);
			
		}
		 else {
	        // Phòng không tồn tại trong cơ sở dữ liệu
	    	throw new IllegalArgumentException("Xoá không được.");
	   }
	}

	@Override
	public List<TypeRoomEntity> findAllRoom() {
		return typeRoomRespository.findAll();
	}
		
	
	
}
