package com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.FeedBackDTO;
import com.hotel.entity.FeedBackEntity;

public interface IFeedBackService {
	int getTotalItem(int status);
	List<FeedBackDTO> findFeedBack(int status, Pageable pageable);
	
	void update(FeedBackDTO dto);
	FeedBackEntity findOne(int id);
	List<FeedBackDTO> findByStatus();
	
	List<FeedBackDTO> findAll();
}
