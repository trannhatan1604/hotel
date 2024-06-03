package com.hotel.service;

import java.util.List;
import java.util.Map;

import com.hotel.entity.StatusEntity;

public interface IStatusService {
	Map<Integer, String> findAll();
	StatusEntity findById(Integer id);
}
