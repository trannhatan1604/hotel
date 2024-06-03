package com.hotel.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.PromotionDTO;
import com.hotel.entity.PromotionEntity;

public interface IPromotionService {
	List<PromotionDTO> findAll(Pageable pageable, String status);
	Map<Integer, String> findAvailblePromotions();
	int getTotalItem();
	PromotionDTO findById(Integer id);
	PromotionEntity findEntityById(Integer id);
	List<PromotionEntity> getPromotionAvailable();
}
