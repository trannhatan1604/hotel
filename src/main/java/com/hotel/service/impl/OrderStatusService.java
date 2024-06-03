package com.hotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.OrderStatusEntity;
import com.hotel.repository.OrderStatusRepository;
import com.hotel.service.IOrderStatusService;

@Service
public class OrderStatusService implements IOrderStatusService{

	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Override
	public Map<Integer, String> findAll() {
		Map<Integer, String> result = new HashMap<Integer, String>();
		List<OrderStatusEntity> entities = orderStatusRepository.findAll();
		for(OrderStatusEntity item: entities) {
			result.put(item.getId(), item.getStatusname());
		}
		return result;
	}

	@Override
	public OrderStatusEntity findById(int id) {
		return orderStatusRepository.findOne(id);
	}
	

}
