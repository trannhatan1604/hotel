package com.hotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
	
	List<RoomEntity> findByStatus_Id(int status);
	
	@Query("SELECT r FROM RoomEntity r WHERE " + "(:typeId = 0 OR r.typeroom.id = :typeId)"
			+ "AND (:searchValue = '' OR r.name LIKE :searchValue)")
	List<RoomEntity> listOfRoom(@Param("typeId") int typeId, @Param("searchValue") String searchValue,
			Pageable pageable);

	RoomEntity findById(int id);

	@Query("SELECT COUNT(r) FROM RoomEntity r " + "WHERE (:typeId = 0 OR r.typeroom.id = :typeId) "
			+ "AND (:searchValue = '' OR r.name LIKE :searchValue)")
	Integer count(@Param("typeId") int typeId, @Param("searchValue") String searchValue);

	@Query("SELECT COUNT(r) FROM RoomEntity r " + "WHERE (:quantity = 0 OR r.typeroom.quantity >= :quantity) "
			+ "AND (:typeId = 0 OR r.typeroom.id = :typeId) " + "AND (:searchValue = '' OR r.name LIKE :searchValue) ")
	Integer countAvailableRooms(@Param("quantity") int quantity, @Param("typeId") int typeId,
			@Param("searchValue") String searchValue);

	@Query("SELECT r FROM RoomEntity r " + "WHERE (:quantity = 0 OR r.typeroom.quantity >= :quantity) "
			+ "AND (:typeId = 0 OR r.typeroom.id = :typeId) " + "AND (:searchValue = '' OR r.name LIKE :searchValue) ")
	List<RoomEntity> findAvailableRooms(@Param("quantity") int quantity, @Param("typeId") int typeId,
			@Param("searchValue") String searchValue, Pageable pageable);

	@Query("SELECT r FROM RoomEntity r WHERE r.id <> :id")
	List<RoomEntity> findAvailableRooms(@Param("id") int id);

	@Query("SELECT o.checkinDate FROM OrderEntity o WHERE o.room.id = :roomId ")
	List<String> findCheckInDateByRoomId(@Param("roomId") Integer roomId);

	@Query("SELECT o.checkoutDate FROM OrderEntity o WHERE o.room.id = :roomId ")
	List<String> findCheckOutDateByRoomId(@Param("roomId") Integer roomId);
	
	// Add dashboard
	@Query(value = "SELECT COUNT(room.id) / t.total * 100\r\n" + "FROM room, (\r\n" + "    SELECT COUNT(*) AS total\r\n"
			+ "    FROM room\r\n" + ") AS t\r\n" + "WHERE room.status_id = 1;", nativeQuery = true)
	int getPercentRoomFree();

	@Query(value = "SELECT COUNT(room.id) FROM room WHERE room.status_id = 1 And room.typeroom_id = :typeroomId", nativeQuery = true)
	int CountRoomFree(@Param("typeroomId") int typeroomId);
	
	@Query("SELECT COUNT(r) FROM RoomEntity r WHERE r.id NOT IN " + "(SELECT o.room.id FROM OrderEntity o "
			+ "WHERE (o.checkinDate BETWEEN :checkinDate AND :checkoutDate) "
			+ "OR (o.checkoutDate BETWEEN :checkinDate AND :checkoutDate)) " 
			+ "AND (:typeId = 0 OR r.typeroom.id = :typeId) " )
	Integer countAvailableRoomWebs(@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate , @Param("typeId") int typeId);
	
	//Lấy phòng đầu 
	@Query("SELECT r \r\n"
			+ "FROM RoomEntity r \r\n"
			+ "WHERE r.id NOT IN (\r\n"
			+ "    SELECT o.room.id \r\n"
			+ "    FROM OrderEntity o \r\n"
			+ "    WHERE (o.checkinDate BETWEEN :checkinDate AND :checkoutDate) \r\n"
			+ "       OR (o.checkoutDate BETWEEN :checkinDate AND :checkoutDate)\r\n"
			+ ") \r\n"
			+ "AND r.typeroom.id = :typeId \r\n")
	List<RoomEntity> findOneAvailableRoom(@Param("checkinDate") Date checkinDate,
			@Param("checkoutDate") Date checkoutDate, @Param("typeId") int typeId);
	
	@Modifying
    @Transactional
    @Query("DELETE FROM RoomEntity r WHERE r.typeroom.id = :typeId")
    int deleteRoomByTypeId(@Param("typeId") int typeId);
}
