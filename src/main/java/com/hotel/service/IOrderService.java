package com.hotel.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hotel.dto.OrderDTO;
import com.hotel.entity.OrderEntity;

public interface IOrderService {
	List<OrderEntity> findAll();
	List<OrderDTO> findByCustomer_id(Integer id);
	
	List<OrderDTO> ListOfOrder(String searchValue, Date checkinDate, Date checkoutDate,Integer status, Pageable pageable);
	Integer count(String searchValue, Date checkinDate, Date checkoutDate,Integer status);
	OrderDTO findById(Integer id);
	void addOrder(OrderDTO dto);

	void updateOrder(OrderDTO dto);
	int delete(Integer id);
	int updateStatus(Integer id, Integer status);
	
	void addOrderWeb(OrderDTO dto);
	void updateStatusInAdmin(Integer orderId, Integer statusId);
	boolean isAllowAccept(Integer orderId);

	boolean isAllowPaid(Integer orderId);

	boolean isAllowCancel(Integer orderId);

	boolean isRoomAvailableInAdd(Integer roomId, Date checkinDate, Date checkoutDate);
	
	boolean isRoomAvailableInUpdate(Integer orderId,Integer roomId, Date checkinDate, Date checkoutDate); 
}
