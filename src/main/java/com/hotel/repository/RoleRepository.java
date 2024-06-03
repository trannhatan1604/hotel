package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.OrderEntity;
import com.hotel.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {

}