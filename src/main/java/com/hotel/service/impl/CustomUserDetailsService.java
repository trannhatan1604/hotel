package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hotel.constant.SystemConstant;
import com.hotel.dto.MyUser;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;
import com.hotel.repository.AccountRespository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRespository accountRespository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AccountEntity accountEntity = accountRespository.findOneByAccountNameAndStatus(username, SystemConstant.ACTIVE_STATUS);

		
		// user khong ton tai
		if (accountEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}

		// lay danh sach role cua user(li do mot user co nhieu role)
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : accountEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
		}
		
		// put thong tin user vao security de duy tri thong tin khi user login vao he
		MyUser myuser = new MyUser(accountEntity.getAccountName(), accountEntity.getPassword(), true, true, true, true,
				authorities);
		myuser.setFullname(accountEntity.getFullName());
		myuser.setId(accountEntity.getId());
		myuser.setPhone(accountEntity.getPhone());
		myuser.setAddress(accountEntity.getAddress());
		myuser.setStatus(accountEntity.getStatus());
		myuser.setImg(accountEntity.getImage());
		//tra ve myuser vi MyUser extend tu User va User imp tu UserDetails
		return myuser;
	}

}
