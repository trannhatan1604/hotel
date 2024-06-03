package com.hotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.entity.PromotionEntity;

public interface PromotionRespository extends JpaRepository<PromotionEntity, Integer> {
	
	//tự lấy ngày hiện tại
	@Query(value = "from PromotionEntity t where CURDATE() BETWEEN startdate AND enddate")
	public List<PromotionEntity> getPromotionAvailable(Pageable pageable);
	
	@Query(value = "from PromotionEntity t where CURDATE() BETWEEN startdate AND enddate")
	public List<PromotionEntity> getPromotionAvailable();
	
	@Query(value = "select COUNT(t) from PromotionEntity t where CURDATE() BETWEEN startdate AND enddate")
	public int countPromotionAvailable();
	
	@Query(value = "from PromotionEntity t where CURDATE() < startdate")
	public List<PromotionEntity> getPromotionUnused(Pageable pageable);
	
	@Query(value = "Select COUNT(t) from PromotionEntity t where CURDATE() < startdate")
	public int countPromotionUnused();
	
	@Query(value = "from PromotionEntity t where CURDATE()  > enddate")
	public List<PromotionEntity> getPromotionExpire(Pageable pageable);
	
	@Query(value = "Select COUNT(t) from PromotionEntity t where CURDATE() > enddate")
	public int countPromotionExpire();
	
	PromotionEntity findById(int id);
	
	@Query("SELECT p FROM PromotionEntity p "
			+ "WHERE :orderDate BETWEEN p.startDate AND p.endDate")
	List<PromotionEntity> findAvailablePromotions(@Param("orderDate") Date orderDate);
}

