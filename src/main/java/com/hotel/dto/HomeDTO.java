package com.hotel.dto;

import java.util.List;
import java.util.Map;

import com.hotel.entity.OrderEntity;
import com.hotel.entity.PromotionEntity;

public class HomeDTO {
	private int totalRoom;
	private int emptyRoom;
	private List<PromotionEntity> availablePromotion;
	private String revenueToday;
	private String revenueThisMonth;
	private String mostOrderTypeRoom;
	private int percentRoomFree;
	private int countOrderToday;
	private List<List<Map<Object, Object>>> chartTypeRoomDataList;
	private List<List<Map<Object, Object>>> chartRevenueMonthsDataList;
	private List<List<Map<Object, Object>>> chartRevenueQuarterDataList;
	private List<List<Map<Object, Object>>> chartRevenueRecentDaysDataList;
	private int days = 7;

	//
	List<OrderEntity> newOrderList;
	private int countCheckoutOrderToday;
	private List<OrderEntity> orderTodayCheckoutList;

	public List<OrderEntity> getNewOrderList() {
		return newOrderList;
	}

	public void setNewOrderList(List<OrderEntity> newOrderList) {
		this.newOrderList = newOrderList;
	}

	public int getCountCheckoutOrderToday() {
		return countCheckoutOrderToday;
	}

	public void setCountCheckoutOrderToday(int countCheckoutOrderToday) {
		this.countCheckoutOrderToday = countCheckoutOrderToday;
	}

	public List<OrderEntity> getOrderTodayCheckoutList() {
		return orderTodayCheckoutList;
	}

	public void setOrderTodayCheckoutList(List<OrderEntity> orderTodayCheckoutList) {
		this.orderTodayCheckoutList = orderTodayCheckoutList;
	}

	public String getRevenueToday() {
		return revenueToday;
	}

	public void setRevenueToday(String revenueToday) {
		this.revenueToday = revenueToday;
	}

	public List<PromotionEntity> getAvailablePromotion() {
		return availablePromotion;
	}

	public void setAvailablePromotion(List<PromotionEntity> availablePromotion) {
		this.availablePromotion = availablePromotion;
	}

	public int getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	public int getEmptyRoom() {
		return emptyRoom;
	}

	public void setEmptyRoom(int emptyRoom) {
		this.emptyRoom = emptyRoom;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<List<Map<Object, Object>>> getChartRevenueRecentDaysDataList() {
		return chartRevenueRecentDaysDataList;
	}

	public void setChartRevenueRecentDaysDataList(List<List<Map<Object, Object>>> chartRevenueRecentDaysDataList) {
		this.chartRevenueRecentDaysDataList = chartRevenueRecentDaysDataList;
	}

	public List<List<Map<Object, Object>>> getChartRevenueQuarterDataList() {
		return chartRevenueQuarterDataList;
	}

	public void setChartRevenueQuarterDataList(List<List<Map<Object, Object>>> chartRevenueQuarterDataList) {
		this.chartRevenueQuarterDataList = chartRevenueQuarterDataList;
	}

	public List<List<Map<Object, Object>>> getChartRevenueMonthsDataList() {
		return chartRevenueMonthsDataList;
	}

	public void setChartRevenueMonthsDataList(List<List<Map<Object, Object>>> chartRevenueMonthsDataList) {
		this.chartRevenueMonthsDataList = chartRevenueMonthsDataList;
	}

	public List<List<Map<Object, Object>>> getChartTypeRoomDataList() {
		return chartTypeRoomDataList;
	}

	public void setChartTypeRoomDataList(List<List<Map<Object, Object>>> chartTypeRoomDataList) {
		this.chartTypeRoomDataList = chartTypeRoomDataList;
	}

	public int getCountOrderToday() {
		return countOrderToday;
	}

	public void setCountOrderToday(int countOrderToday) {
		this.countOrderToday = countOrderToday;
	}

	private List<TypeRoomDTO> listTypeRoomPercent;

	public List<TypeRoomDTO> getListTypeRoomPercent() {
		return listTypeRoomPercent;
	}

	public void setListTypeRoomPercent(List<TypeRoomDTO> listTypeRoomPercent) {
		this.listTypeRoomPercent = listTypeRoomPercent;
	}

	public int getPercentRoomFree() {
		return percentRoomFree;
	}

	public void setPercentRoomFree(int percentRoomFree) {
		this.percentRoomFree = percentRoomFree;
	}

	public String getMostOrderTypeRoom() {
		return mostOrderTypeRoom;
	}

	public void setMostOrderTypeRoom(String mostOrderTypeRoom) {
		this.mostOrderTypeRoom = mostOrderTypeRoom;
	}

	public String getRevenueThisMonth() {
		return revenueThisMonth;
	}

	public void setRevenueThisMonth(String revenueThisMonth) {
		this.revenueThisMonth = revenueThisMonth;
	}
}
