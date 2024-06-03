package com.hotel.service;

import java.util.Map;

import com.hotel.entity.OrderStatusEntity;

public interface IOrderStatusService {
	Map<Integer, String> findAll();

	OrderStatusEntity findById(int id);
}
