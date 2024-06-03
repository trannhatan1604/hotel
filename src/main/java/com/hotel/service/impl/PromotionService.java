package com.hotel.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.converter.PromotionConverter;
import com.hotel.dto.PromotionDTO;
import com.hotel.entity.PromotionEntity;
import com.hotel.repository.OrderRepository;
import com.hotel.repository.PromotionRespository;
import com.hotel.service.IOrderService;
import com.hotel.service.IPromotionService;

@Service
public class PromotionService implements IPromotionService {

	@Autowired
	private PromotionRespository promotionRespository;  

	@Autowired OrderRepository orderRepository;
	
	@Override
	public List<PromotionDTO> findAll(Pageable pageable, String status) {
		List<PromotionEntity> entities = promotionRespository.findAll(pageable).getContent();
		List<PromotionDTO> dtos = new ArrayList<PromotionDTO>();
		
		if(status.equals("isAvailable")) {
			entities = promotionRespository.getPromotionAvailable(pageable);
		}
		else if(status.equals("unused")) {
			entities = promotionRespository.getPromotionUnused(pageable);
		}
		else if(status.equals("expire")) {
			entities = promotionRespository.getPromotionExpire(pageable);
		}
		
		for (PromotionEntity entity : entities) {
			PromotionDTO dto = new PromotionDTO();
			dto = PromotionConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public int countWithFilter(String status) {
		int count = 0;
		if(status.equals("isAvailable")) {
			count = promotionRespository.countPromotionAvailable();
		}
		else if(status.equals("unused")) {
			count = promotionRespository.countPromotionUnused();
		}
		else if(status.equals("expire")) {
			count = promotionRespository.countPromotionExpire();
		}
		return count;
	}
	@Override
	public int getTotalItem() {
		return (int) promotionRespository.count();
	}
	@Override
	public PromotionDTO findById(Integer id) {
		PromotionEntity entity = promotionRespository.findById(id);
		if(entity!=null) {
			PromotionDTO dto = PromotionConverter.toDTO(entity);
			return dto;
		}
		else {
			return null;
		}
	}
	
	public void updatePromotion(PromotionDTO dto) {
		PromotionEntity entity = new PromotionEntity();
		if (dto.getId() != null) {
			entity = promotionRespository.findById(dto.getId());
		}

		if (entity != null) {
			// Cập nhật thông tin của entity từ dto
			entity = PromotionConverter.toEntity(dto, "update");
			// Lưu thay đổi vào cơ sở dữ liệu
			promotionRespository.save(entity);
		} else {
			// Tạo mới entity từ dto
			entity = PromotionConverter.toEntity(dto, "save");

			// Lưu mới vào cơ sở dữ liệu
			promotionRespository.save(entity);
		}
	}
	
	public void deletePromotion(int id) {
		promotionRespository.delete(id);
	}
	@Override
	public Map<Integer, String> findAvailblePromotions() {
		Map<Integer,String> result = new HashMap<>();
		LocalDate today = LocalDate.now();
		Date convertedDate = Date.valueOf(today);
		List<PromotionEntity> entities = promotionRespository.findAvailablePromotions(convertedDate);
		for(PromotionEntity item : entities) {
			result.put(item.getId(), item.getName() + " - " + item.getLevel() + "%");
		}
		
		return result;
	}

	@Override
	public PromotionEntity findEntityById(Integer id) {
		if(id == 0) return null;
		return promotionRespository.findById(id);
	}

	@Override
	public List<PromotionEntity> getPromotionAvailable() {
		// TODO Auto-generated method stub
		return promotionRespository.getPromotionAvailable();
	}
	public boolean isAllowDeletePromotion(int id) {
		return orderRepository.findByPromotion_Id(id).size() == 0;
	}

}
