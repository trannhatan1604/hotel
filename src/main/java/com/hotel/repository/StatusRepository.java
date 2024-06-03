    package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.StatusEntity;


public interface StatusRepository extends JpaRepository<StatusEntity,Integer> {
	StatusEntity findById(Integer id);
}
    
