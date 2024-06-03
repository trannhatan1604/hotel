    package com.hotel.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.dto.AccountDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;
import com.hotel.service.IAccountService;
import com.hotel.service.IRoleService;
import com.hotel.service.impl.StatusService;
import com.hotel.service.impl.TypeRoomService;

@Component
public class AccountConverter {
	private static IRoleService roleService;

	@Autowired
	public AccountConverter(IRoleService roleService) {
		AccountConverter.roleService = roleService;
	}
	public static AccountDTO toDTO(AccountEntity entity) {
		AccountDTO dto = new AccountDTO();

		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setId(entity.getId());
		dto.setAccountName(entity.getAccountName());
		dto.setFullName(entity.getFullName());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		dto.setStatus(entity.getStatus());
		dto.setImage(entity.getImage());
		return dto;
	}
	//tạo mới account
	public static AccountEntity toEntity(AccountDTO dto) {
		 AccountEntity entity = new AccountEntity();
		    entity.setAccountName(dto.getAccountName());
		    entity.setFullName(dto.getFullName());
		    entity.setPhone(dto.getPhone());
		    entity.setPassword(dto.getPassword());
		    entity.setAddress(dto.getAddress());
		    entity.setStatus(dto.getStatus());
		    entity.setImage(dto.getImage());
		    List<RoleEntity> newRole = new ArrayList<RoleEntity>();
		    RoleEntity role = new RoleEntity();
		    role.setId(3);
		    newRole.add(role);
		    // Kiểm tra null trước khi sử dụng
		    entity.setRoles(newRole);
		    
		    return entity;
	}
	//cập nhật account
	public static AccountEntity toEntity( AccountDTO dto, AccountEntity entity)
	{
		entity.setAccountName(dto.getAccountName());
		entity.setFullName(dto.getFullName());
		entity.setPhone(dto.getPhone());
		entity.setImage(dto.getImage());
		entity.setAddress(dto.getAddress());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}
    
