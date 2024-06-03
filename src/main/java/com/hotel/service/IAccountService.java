    package com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.AccountDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;

public interface IAccountService {
	List<AccountDTO> findAllCustomer(Pageable pageable);
	List<AccountDTO> findAllEmployee(Pageable pageable);
	AccountEntity findById(int id);
	List<AccountDTO> findByName(String name,Pageable pageable);
	List<AccountDTO> findByFullNameContainingAndRoles(String name,RoleEntity role,Pageable pageable);
	int getCountCustomer();
	int getCountEmployee();
	Integer updateAccount(AccountDTO dto);
	void deleteAccount(int id);
	void assignRoleToAccount(int accountId, int roleId);
	List<AccountEntity> findAll();
	AccountEntity findOneByPhone(String phone);
	Integer insertUser(AccountDTO dto);
	AccountDTO findCustomerByPhone(String phone);
	//changepassword
	void changePassword(String password, Integer id);
	boolean findByIdAndPassword(int id, String password);
}

    
