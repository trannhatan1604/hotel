package com.hotel.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.entity.FeedBackEntity;

public interface FeedBackRespository extends JpaRepository<FeedBackEntity, Integer> {
	@Query("SELECT r FROM FeedBackEntity r WHERE " + "(:status = 0 OR r.status = :status) " )
	List<FeedBackEntity> listOfFeedBack(@Param("status") int status, Pageable pageable);

	FeedBackEntity findById(int id);
	
	@Query("SELECT Count(r) FROM FeedBackEntity r WHERE " + "(:status = 0 OR r.status = :status) " )
	int getTotalItem(@Param("status") int status);
	
	List<FeedBackEntity> findByStatus(int status);
	
}

