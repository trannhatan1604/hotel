package com.hotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.entity.RoomEntity;
import com.hotel.entity.TypeRoomEntity;

public interface TypeRoomRespository extends JpaRepository<TypeRoomEntity, Integer> {


	@Query("SELECT r FROM TypeRoomEntity r WHERE (:searchValue = '' OR r.name LIKE :searchValue)"
			+ "AND (:quantityU = 0 OR r.quantity = :quantityU)")
	List<TypeRoomEntity> listOfRoom(@Param("quantityU") int quantityU, @Param("searchValue") String searchValue, Pageable pageable);

	TypeRoomEntity findById(int id);
	
	@Query("SELECT Count(r) FROM TypeRoomEntity r WHERE (:searchValue = '' OR r.name LIKE :searchValue)"
			+ "AND (:quantityU = 0 OR r.quantity = :quantityU)")
	int getTotalItem(@Param("quantityU") int quantityU, @Param("searchValue") String searchValue);
	
	//thongke
	@Query(value = "SELECT COUNT(orders.id) / ?1 * 100 \r\n"
 			+ "FROM orders JOIN room \r\n"
 			+ "JOIN typeroom \r\n"
 			+ "WHERE typeroom.id = ?2 AND orders.room_id = room.id AND typeroom.id = room.typeroom_id;", nativeQuery = true)
 	float getPercentByTypeRoomId(long totalRoom, int id);
 	
 	@Query(value = "SELECT IFNULL(SUM(o.totalprice), 0)\r\n"
 			+ "From orders o JOIN room r JOIN typeroom t \r\n"
 			+ "Where o.room_id = r.id AND r.typeroom_id = t.id AND YEAR(o.checkindate) = ?1", nativeQuery = true)
 	long getTotalRevenueOfYear(int year);

	
	/*
	 * @Query("SELECT NEW com.hotel.entity.TypeRoomEntity(t.id, t.name, t.description, t.price, t.image, t.space, t.quantity) "
	 * + "FROM RoomEntity r, TypeRoomEntity t " +
	 * "WHERE r.typeroom.id = t.id AND r.id NOT IN " +
	 * "(SELECT o.room.id FROM OrderEntity o " +
	 * "WHERE (o.checkinDate BETWEEN :checkinDate AND :checkoutDate) " +
	 * "OR (o.checkoutDate BETWEEN :checkinDate AND :checkoutDate)) " +
	 * "AND r.typeroom.quantity >= :quantity " +
	 * "AND (:typeId = 0 OR r.typeroom.id = :typeId) " +
	 * "AND (:searchValue = '' OR r.name LIKE :searchValue) " + "AND r.status = 1")
	 * List<TypeRoomEntity> findAvailableTypeRooms(@Param("checkinDate") Date
	 * checkinDate,
	 * 
	 * @Param("checkoutDate") Date checkoutDate, @Param("quantity") int
	 * quantity, @Param("typeId") int typeId,
	 * 
	 * @Param("searchValue") String searchValue, Pageable pageable);
	 */
	
}

