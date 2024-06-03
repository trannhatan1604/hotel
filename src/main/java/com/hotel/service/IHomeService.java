package com.hotel.service;

import java.util.List;
import java.util.Map;

import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.OrderEntity;
import com.hotel.entity.PromotionEntity;

public interface IHomeService {
	int getEmptyRoom();
	List<PromotionEntity> getAvailablePromotion();
	String getRevenueToDay();
	String getRevenueThisMonth();
	String getMostOrderTypeRoom();
	int getPercentRoomFree();
	List<TypeRoomDTO> getTypeRoomPercent();
	int getCountOrderToday();
	List<List<Map<Object, Object>>> getCanvasjsChartData();
	List<List<Map<Object, Object>>> getRevenueMonthsOfYear();
	List<List<Map<Object, Object>>> getQuarterRevenue();
	List<List<Map<Object, Object>>> getRevenueRecentDays(int days);

	List<OrderEntity> getNewOrderList();
	List<OrderEntity> getOrderTodayCheckout();

}
