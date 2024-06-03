package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.converter.AccountConverter;
import com.hotel.dto.AccountDTO;
import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;
import com.hotel.repository.AccountRespository;
import com.hotel.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private AccountRespository accountRespository;

	@Override
	public List<AccountDTO> findAllCustomer(Pageable pageable) {
		List<AccountEntity> entitys = accountRespository.findByRoles_id(3, pageable);
		List<AccountDTO> dtos = new ArrayList<AccountDTO>();
		for (AccountEntity entity : entitys) {
			AccountDTO dto = new AccountDTO();
			dto = AccountConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<AccountDTO> findAllEmployee(Pageable pageable) {
		List<AccountEntity> entitys = accountRespository.findByRoles_id(2, pageable);
		List<AccountDTO> dtos = new ArrayList<AccountDTO>();
		for (AccountEntity entity : entitys) {
			AccountDTO dto = new AccountDTO();
			dto = AccountConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public int getCountCustomer() {
		return accountRespository.countByRoles_Id(3); // Giả sử ID của vai trò khách hàng là 2
	}

	@Override
	public int getCountEmployee() {
		return accountRespository.countByRoles_Id(2);
	}

	@Override
	public AccountEntity findById(int id) {
		return accountRespository.findById(id);
	}

	@Override
	public List<AccountDTO> findByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateAccount(AccountDTO dto) {
		AccountEntity entity = new AccountEntity();
		System.out.println("Id: " + dto.getId());
		if (dto.getId() != null) {
			entity = accountRespository.findById(dto.getId());
		}

		if (entity != null) {
			// Cập nhật thông tin của entity từ dto
			entity = AccountConverter.toEntity(dto, entity);
			// Lưu thay đổi vào cơ sở dữ liệu
			accountRespository.save(entity);
			System.out.println(dto.getPassword());
			return entity.getId();
		} else {
			// Tạo mới entity từ dto
			entity = AccountConverter.toEntity(dto);
			System.out.println(dto.getFullName());
			// Lưu mới vào cơ sở dữ liệu
			AccountEntity newAccount = accountRespository.save(entity);
			System.out.println(newAccount.getPassword());
			return accountRespository.getLastInsertedId();
		}

	}

	@Override
	public void deleteAccount(int id) {
		AccountEntity entity = accountRespository.findById(id);
		if (entity != null) {
			accountRespository.delete(id);
		} else {
			// Phòng không tồn tại trong cơ sở dữ liệu
			throw new IllegalArgumentException("Xoá không được.");
		}
	}

	@Override
	public List<AccountDTO> findByFullNameContainingAndRoles(String name, RoleEntity role, Pageable pageable) {
		List<AccountEntity> entitys = accountRespository.findByFullNameContainingAndRoles(name, role, pageable);
		List<AccountDTO> dtos = new ArrayList<AccountDTO>();
		for (AccountEntity entity : entitys) {
			AccountDTO dto = new AccountDTO();
			dto = AccountConverter.toDTO(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	// thêm role cho account
	@Override
	public void assignRoleToAccount(int accountId, int roleId) {
		accountRespository.assignRoleToAccount(accountId, roleId);
	}

	@Override
	public List<AccountEntity> findAll() {
		// TODO Auto-generated method stub
		return accountRespository.findAll();
	}

	@Override
	public AccountEntity findOneByPhone(String phone) {
		return accountRespository.findOneByPhone(phone);
	}

	@Override
	public Integer insertUser(AccountDTO dto) {
		accountRespository.insertUser(dto.getAccountName(), dto.getAddress(), dto.getFullName(), dto.getPassword(), dto.getPhone(), dto.getImage());
		return accountRespository.getLastInsertedId();
	}

	@Override
	public AccountDTO findCustomerByPhone(String phone) {
		System.out.println("trong day: " + phone);
		AccountEntity entity = accountRespository.findByRoles_idAndPhone(3, phone);
		return AccountConverter.toDTO(entity);
	}

	@Override
	public void changePassword(String password, Integer id) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String code = encoder.encode(password);
		accountRespository.updatePassword(code, id);
		
	}

	@Override
	public boolean findByIdAndPassword(int id, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); //check lỗi
		String code = encoder.encode(password);
		
		AccountEntity entity = accountRespository.findById(id);
		boolean code1 = encoder.matches(password, entity.getPassword());
		return code1;
	}
}
