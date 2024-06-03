package com.hotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	@Query(value = "SELECT * FROM orders where customer_id = ?1 Order By id DESC", nativeQuery = true)
	List<OrderEntity> findByCustomerId(Integer id);

	@Query(value = "SELECT IFNULL(SUM(o.totalprice), 0) \r\n" + "From orders o JOIN room r JOIN typeroom t \r\n"
			+ "Where o.room_id = r.id AND r.typeroom_id = t.id AND MONTH(o.checkindate) = ?1 AND YEAR(o.checkindate) = ?2", nativeQuery = true)
	double getRevenueMonth(int month, int year);

	@Query(value = "SELECT typeroom.name\r\n" + "FROM orders JOIN room JOIN typeroom\r\n"
			+ "WHERE orders.room_id = room.id AND room.typeroom_id = typeroom.id AND YEAR(orders.checkindate) = YEAR(CURRENT_DATE) \r\n"
			+ "GROUP BY typeroom.id\r\n" + "HAVING COUNT(orders.id) = (\r\n" + "    SELECT MAX(mymax)\r\n"
			+ "    FROM (\r\n" + "    	SELECT COUNT(o.id) AS mymax\r\n" + "        FROM orders o JOIN room r\r\n"
			+ "        JOIN typeroom t\r\n"
			+ "        WHERE o.room_id = r.id AND r.typeroom_id = t.id AND YEAR(o.checkindate) = YEAR(CURRENT_DATE) \r\n"
			+ "        GROUP BY t.id\r\n" + "    ) as table1\r\n" + ")", nativeQuery = true)
	String getMostOrderTypeRoom();

	@Query(value = "SELECT COUNT(*) FROM orders where checkindate = CURRENT_DATE", nativeQuery = true)
	int getOrderToday();

	@Query(value = "SELECT IFNULL(ROUND(SUM(o.totalprice) / ?4 * 100), 0)\r\n"
			+ "From orders o JOIN room r JOIN typeroom t \r\n"
			+ "Where o.room_id = r.id AND r.typeroom_id = t.id AND YEAR(o.checkindate) = YEAR(CURRENT_DATE) \r\n"
			+ "AND (MONTH(o.checkindate) IN (?1,?2,?3))", nativeQuery = true)
	long getRevenueQuarter(int month1, int month2, int month3, long totalRevenueYear);

	@Query(value = "SELECT IFNULL(SUM(o.totalprice), 0) \r\n" + "From orders o JOIN room r JOIN typeroom t \r\n"
			+ "Where o.room_id = r.id AND r.typeroom_id = t.id \r\n" + "AND o.checkindate = ?1", nativeQuery = true)
	long getDayRevenue(String date);

	@Query(value = "SELECT o FROM OrderEntity o "
			+ "WHERE (:searchValue = '' OR o.customerId IN (SELECT a.id FROM AccountEntity a WHERE a.fullName LIKE :searchValue )) "
			/*
			 * +
			 * "OR (o.room.id IN (SELECT r.id FROM RoomEntity r WHERE r.name LIKE :searchValue)))"
			 */
			+ " AND (:checkinDate is null OR o.checkinDate = :checkinDate)"
			+ " AND (:checkoutDate is null OR o.checkoutDate = :checkoutDate)"
			+ "AND (:status = 0 OR o.status.id = :status)"
			+"Order By o.id desc")
	List<OrderEntity> listOfOrder(@Param(value = "searchValue") String searchValue,
			@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate,
			@Param("status") Integer status, Pageable pageable);

	@Query(value = "SELECT o FROM OrderEntity o ")
	List<OrderEntity> list();

	@Query(value = "SELECT COUNT(o) FROM OrderEntity o "
			+ "WHERE (:searchValue = '' OR o.customerId IN (SELECT a.id FROM AccountEntity a WHERE a.fullName LIKE :searchValue ))"
			/*
			 * +
			 * "OR (o.room.id IN (SELECT r.id FROM RoomEntity r WHERE r.name LIKE :searchValue)))"
			 */
			+ "AND (:checkinDate is null OR o.checkinDate = :checkinDate)"
			+ "AND (:checkoutDate is null OR o.checkoutDate = :checkoutDate)"
			+ "AND (:status = 0 OR o.status.id = :status)")
	Integer count(@Param(value = "searchValue") String searchValue, @Param("checkinDate") Date checkinDate,
			@Param("checkoutDate") Date checkoutDate, @Param("status") Integer status);

	@Transactional
	@Modifying
	@Query("DELETE FROM OrderEntity o " + "WHERE o.id = :id "
			+ "AND (o.status.id = 2 OR o.status.id = 3 OR o.status.id = 4 OR o.status.id = 5)")
	int deleteOrderById(@Param("id") Integer id);

	@Transactional
	@Modifying
	@Query("UPDATE OrderEntity o SET o.status.id = :statusId" + " WHERE o.id = :id ")
	int updateOrderById(@Param("id") Integer id, @Param("statusId") Integer status);

	@Query("SELECT o FROM OrderEntity o WHERE o.room.id = :roomId AND "
			+ "((o.checkinDate <= :checkinDate AND o.checkoutDate >= :checkinDate) OR "
			+ "(o.checkinDate <= :checkoutDate AND o.checkoutDate >= :checkoutDate) OR "
			+ "(o.checkinDate >= :checkinDate AND o.checkoutDate <= :checkoutDate))")
	List<OrderEntity> findConflictingOrdersInAdd(@Param("roomId") Integer roomId,
			@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate);

	@Query("SELECT o FROM OrderEntity o WHERE o.room.id = :roomId AND o.id <> :orderId AND "
			+ "((o.checkinDate <= :checkinDate AND o.checkoutDate >= :checkinDate) OR "
			+ "(o.checkinDate <= :checkoutDate AND o.checkoutDate >= :checkoutDate) OR "
			+ "(o.checkinDate >= :checkinDate AND o.checkoutDate <= :checkoutDate))")
	List<OrderEntity> findConflictingOrdersInUpdate(@Param("orderId") Integer orderId, @Param("roomId") Integer roomId,
			@Param("checkinDate") Date checkinDate, @Param("checkoutDate") Date checkoutDate);
	
	//
	List<OrderEntity> findByPromotion_Id(int id);

	@Query(value = "SELECT * FROM orders o join account a ON o.customer_id = a.id "
				+ "JOIN room r ON o.room_id = r.id "
				+ "JOIN typeroom t ON r.typeroom_id = t.id "
				+ "WHERE o.checkindate = CURRENT_DATE "
				+ "ORDER BY o.id DESC "
				+ "LIMIT 10", nativeQuery = true)
		public List<OrderEntity> getNewOrderList();

	@Query(value = "SELECT * FROM orders o join account a ON o.customer_id = a.id "
				+ "JOIN room r ON o.room_id = r.id "
				+ "JOIN typeroom t ON r.typeroom_id = t.id "
				+ "WHERE o.checkoutdate = CURRENT_DATE "
						+ "ORDER BY o.id DESC "
						+ "LIMIT 10", nativeQuery = true)
		public List<OrderEntity> getOrderTodayCheckout();

}