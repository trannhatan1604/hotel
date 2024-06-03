package com.hotel.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.entity.AccountEntity;
import com.hotel.entity.RoleEntity;

public interface AccountRespository extends JpaRepository<AccountEntity, Integer> {
	List<AccountEntity> findByRoles_id(Integer id, Pageable pageable);

	AccountEntity findBy(int id);

	List<AccountEntity> findByFullNameContainingAndRoles(String name, RoleEntity role, Pageable pageable);

	AccountEntity findByIdAndPassword(int id, String password);
	
	int countByRoles_Id(Integer id);

	AccountEntity findOneByAccountNameAndStatus(String name, int status);

	AccountEntity findById(int id);

	AccountEntity findOneByPhone(String phone);

	// thêm role cho account
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO account_role (accountid, roleid) VALUES (:accountId, :roleId)", nativeQuery = true)
	void assignRoleToAccount(@Param("accountId") int accountId, @Param("roleId") int roleId);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO account (accountname, address, fullname, password, phone, status, image) VALUES (:accountName, :address, :fullname, :password, :phone, 1, :image)", nativeQuery = true)
	void insertUser(@Param("accountName") String accountName, @Param("address") String address,
			@Param("fullname") String fullname, @Param("password") String password, @Param("phone") String phone, @Param("image") String image);

	// Lấy id mới nhất đươc thêm
	@Query(value = "SELECT max(id) FROM account", nativeQuery = true)
	Integer getLastInsertedId();

	AccountEntity findByRoles_idAndPhone(Integer id, String phone);
	
	//change password
	@Modifying
    @Transactional
    @Query("UPDATE AccountEntity e SET e.password = :password WHERE e.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") int id);
	
	
}
