package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.converter.FeedBackConverter;
import com.hotel.dto.FeedBackDTO;
import com.hotel.entity.FeedBackEntity;
import com.hotel.repository.FeedBackRespository;
import com.hotel.service.IFeedBackService;

@Service
public class FeedBackService implements IFeedBackService {
	
	@Autowired
	private FeedBackRespository feedBackRespository;

	@Override
	public int getTotalItem(int status) {
		return feedBackRespository.getTotalItem(status);
	}

	@Override
	public List<FeedBackDTO> findFeedBack(int status, Pageable pageable) {
		List<FeedBackEntity> entities = feedBackRespository.listOfFeedBack(status, pageable);
		List<FeedBackDTO> dtos = new ArrayList<FeedBackDTO>();
		for(FeedBackEntity entity:  entities) {
				FeedBackDTO dto = new FeedBackDTO();
				dto = FeedBackConverter.toDTO(entity);
				dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void update(FeedBackDTO dto) {
		 FeedBackEntity entity = new FeedBackEntity();
		    if (dto.getId() != null) {
		        entity = feedBackRespository.findById(dto.getId());
		        
		    }
		    System.out.println((entity != null) +" "+entity);
		    if (entity.getId() != null) {
		        // Cập nhật thông tin của entity từ dto
		        entity = FeedBackConverter.toEntity(dto,entity);
		        feedBackRespository.save(entity);
		    } else {
		        // Tạo mới entity từ dto
		        entity = FeedBackConverter.toEntity(dto);
		        // Lưu mới vào cơ sở dữ liệu
		        feedBackRespository.save(entity);
		    }
		
	}

	@Override
	public FeedBackEntity findOne(int id) {
		return feedBackRespository.findById(id);
	}

	@Override
	public List<FeedBackDTO> findByStatus() {
		List<FeedBackEntity> entities = feedBackRespository.findByStatus(1);
		List<FeedBackDTO> dtos = new ArrayList<FeedBackDTO>();
		for (FeedBackEntity entity : entities) {
			FeedBackDTO dto = FeedBackConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<FeedBackDTO> findAll() {
		// TODO Auto-generated method stub
		List<FeedBackEntity> entities = feedBackRespository.findAll();
		List<FeedBackDTO> dtos = new ArrayList<FeedBackDTO>();
		for (FeedBackEntity entity : entities) {
			FeedBackDTO dto = FeedBackConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}
	

}
