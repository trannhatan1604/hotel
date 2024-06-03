package com.hotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.StatusEntity;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.repository.StatusRepository;
import com.hotel.service.IStatusService;


@Service
public class StatusService implements IStatusService{

	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public Map<Integer, String> findAll() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		List<StatusEntity> entities = statusRepository.findAll();
		for (StatusEntity item : entities) {
			result.put(item.getId(), item.getStatusname());
		}
		return result;
	}

	@Override
	public StatusEntity findById(Integer id) {
		
		return statusRepository.findById(id);
	}

}
