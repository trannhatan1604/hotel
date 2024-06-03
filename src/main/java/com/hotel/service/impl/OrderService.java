package com.hotel.service.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.constant.SystemConstant;
import com.hotel.converter.OrderConverter;
import com.hotel.dto.OrderDTO;
import com.hotel.dto.PromotionDTO;
import com.hotel.entity.OrderEntity;
import com.hotel.entity.OrderStatusEntity;
import com.hotel.entity.RoomEntity;
import com.hotel.entity.StatusEntity;
import com.hotel.repository.OrderRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.service.IOrderService;
import com.hotel.service.IOrderStatusService;
import com.hotel.service.IPromotionService;
import com.hotel.service.IRoomService;
import com.hotel.service.IStatusService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private IRoomService roomService;
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private IPromotionService promotionService;
	@Autowired
	private IOrderStatusService orderStatusService;
	@Autowired
	private IStatusService statusService;

	@Override
	public List<OrderEntity> findAll() {
		return orderRepository.findAll();
	}

	public void addOrder(OrderDTO dto) {
		try {
			System.out.println("------------------------------");
			System.out.println("Trang thai: " + dto.getStatusId());
			System.out.println("Ma khach hang: " + dto.getCustomerId());
			System.out.println("Ma nhan vien: " + dto.getEmployeeId());
			System.out.println("Ngay check in: " + dto.getCheckinDate());
			System.out.println("Ngay check out: " + dto.getCheckoutDate());
			System.out.println("Ma Phong: " + dto.getRoomId());
			System.out.println("Ma Giam Gia: " + dto.getPromotionId());
			System.out.println("So Luong: " + dto.getQuantity());

			// Get room information
			RoomEntity room = roomService.findById(dto.getRoomId());
			if (room == null) {
				System.out.println("Không tìm thấy thông tin phòng");
				return; // or throw exception if needed
			}

			// Calculate total price
			Float roomPrice = room.getTyperoom().getPrice();
			long millisecondsPerDay = 1000 * 60 * 60 * 24;
			long daysBetween = (dto.getCheckoutDate().getTime() - dto.getCheckinDate().getTime()) / millisecondsPerDay;
			dto.setTotalPrice(daysBetween * roomPrice);

			// Handle promotion if available
			if (dto.getPromotionId() != 0) {
				PromotionDTO promotion = promotionService.findById(dto.getPromotionId());
				if (promotion != null) {
					float promotionLevel = (float) (promotion.getLevel()) / 100;
					dto.setTotalPrice(dto.getTotalPrice() - (dto.getTotalPrice() * promotionLevel));
				}
			} else {
				System.out.println("Không có mã giảm giá");
			}
			// Set order status
			dto.setStatusId(SystemConstant.WAIT);

			// Convert DTO to Entity and save
			OrderEntity entity = OrderConverter.toEntity(dto);
			orderRepository.save(entity);

		} catch (Exception e) {
			System.out.println("Đã xảy ra lỗi khi thêm đơn đặt phòng: " + e.getMessage());
			// Log the exception or handle it accordingly
		}
	}

	@Override
	public void updateOrder(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		if (dto.getId() != null) {
			entity = orderRepository.findOne(dto.getId());
		}

		if (entity != null) {
			System.out.println("check entity ! null");
			entity = OrderConverter.toEntity(dto, entity);
			orderRepository.save(entity);
		}
	}

	@Override
	public List<OrderDTO> findByCustomer_id(Integer id) {
		List<OrderEntity> entities = orderRepository.findByCustomerId(id);
		List<OrderDTO> dtos = new ArrayList<OrderDTO>();
		for (OrderEntity orderEntity : entities) {
			OrderDTO dto = OrderConverter.toDTO(orderEntity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<OrderDTO> ListOfOrder(String searchValue, Date checkinDate, Date checkoutDate, Integer status,
			Pageable pageable) {
		searchValue = "%" + searchValue + "%";
		System.out.println("trong service: " + searchValue + "." + checkinDate + "." + checkoutDate);
		List<OrderEntity> entities = orderRepository.listOfOrder(searchValue, (java.sql.Date) checkinDate,
				(java.sql.Date) checkoutDate, status, pageable);
		/* List<OrderEntity> entities = orderRepository.list(); */
		List<OrderDTO> result = new ArrayList<OrderDTO>();
		for (OrderEntity item : entities) {
			OrderDTO dto = new OrderDTO();
			dto = OrderConverter.toDTO(item);
			result.add(dto);
		}
		return result;
	}

	@Override
	public Integer count(String searchValue, Date checkinDate, Date checkoutDate, Integer status) {
		return orderRepository.count(searchValue, (java.sql.Date) checkinDate, (java.sql.Date) checkoutDate, status);
	}

	@Override
	public OrderDTO findById(Integer id) {
		OrderEntity entity = orderRepository.findOne(id);
		OrderDTO dto = OrderConverter.toDTO(entity);
		return dto;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return orderRepository.deleteOrderById(id);
	}

	@Override
	public int updateStatus(Integer id, Integer status) {
		OrderEntity order = orderRepository.findOne(id);
		RoomEntity room = order.getRoom();
		if (status == SystemConstant.PAID && room.getStatus().getId() != SystemConstant.VAILABLE) {
			StatusEntity roomStatus = statusService.findById(SystemConstant.VAILABLE);
			room.setStatus(roomStatus);
			roomRepository.save(room);
		}
		return orderRepository.updateOrderById(id, status);
	}

	@Override
	public void addOrderWeb(OrderDTO dto) {
		OrderEntity entity = OrderConverter.toEntity(dto);
		orderRepository.save(entity);

	}

	@Override
	public void updateStatusInAdmin(Integer orderId, Integer statusId) {
		OrderEntity order = orderRepository.findOne(orderId);
		OrderStatusEntity status = orderStatusService.findById(statusId);
		RoomEntity room = order.getRoom();
		if (statusId == SystemConstant.ACCEPT && room.getStatus().getId() == SystemConstant.VAILABLE) {
			StatusEntity roomStatus = statusService.findById(SystemConstant.UNAVAILABLE);
			room.setStatus(roomStatus);
			roomRepository.save(room);
		}
		if (statusId == SystemConstant.PAID && room.getStatus().getId() != SystemConstant.VAILABLE) {
			StatusEntity roomStatus = statusService.findById(SystemConstant.VAILABLE);
			room.setStatus(roomStatus);
			roomRepository.save(room);
		}
		order.setStatus(status);
		orderRepository.save(order);
	}

	@Override
	public boolean isAllowAccept(Integer orderId) {
		OrderEntity order = orderRepository.findOne(orderId);
		int status = order.getStatus().getId();
		if (status == SystemConstant.WAIT) {

			return true;
		}
		return false;
	}

	@Override
	public boolean isAllowPaid(Integer orderId) {
		OrderEntity order = orderRepository.findOne(orderId);
		int status = order.getStatus().getId();
		if (status == SystemConstant.ACCEPT) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAllowCancel(Integer orderId) {
		OrderEntity order = orderRepository.findOne(orderId);
		int status = order.getStatus().getId();
		if (status == SystemConstant.ACCEPT || status == SystemConstant.WAIT) {
			return true;
		}
		return false;

	}

	@Override
	public boolean isRoomAvailableInAdd(Integer roomId, Date checkinDate, Date checkoutDate) {
		// Chuyển đổi từ java.util.Date sang java.sql.Date
		java.sql.Date sqlCheckinDate = new java.sql.Date(checkinDate.getTime());
		java.sql.Date sqlCheckoutDate = new java.sql.Date(checkoutDate.getTime());

		// Kiểm tra kiểu dữ liệu của các biến
		System.out.println("sqlCheckinDate type: " + sqlCheckinDate.getClass());
		System.out.println("sqlCheckoutDate type: " + sqlCheckoutDate.getClass());

		List<OrderEntity> conflictingOrders = orderRepository.findConflictingOrdersInAdd(roomId, sqlCheckinDate,
				sqlCheckoutDate

		);
		return conflictingOrders.isEmpty();
	}

	@Override
	public boolean isRoomAvailableInUpdate(Integer orderId, Integer roomId, Date checkinDate, Date checkoutDate) {
		// Chuyển đổi từ java.util.Date sang java.sql.Date
		java.sql.Date sqlCheckinDate = new java.sql.Date(checkinDate.getTime());
		java.sql.Date sqlCheckoutDate = new java.sql.Date(checkoutDate.getTime());

		// Kiểm tra kiểu dữ liệu của các biến
		System.out.println("sqlCheckinDate type: " + sqlCheckinDate.getClass());
		System.out.println("sqlCheckoutDate type: " + sqlCheckoutDate.getClass());

		List<OrderEntity> conflictingOrders = orderRepository.findConflictingOrdersInUpdate(orderId, roomId,
				sqlCheckinDate, sqlCheckoutDate

		);
		return conflictingOrders.isEmpty();
	}

}
