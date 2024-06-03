package com.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.RoleEntity;
import com.hotel.repository.RoleRepository;
import com.hotel.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<RoleEntity> findAll() {
		return roleRepository.findAll();
	}
 
	
}
