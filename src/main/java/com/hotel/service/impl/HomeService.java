package com.hotel.service.impl;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotel.converter.TypeRoomConverter;
import com.hotel.dto.ChartLineColumn;
import com.hotel.dto.ChartPie;
import com.hotel.dto.TypeRoomDTO;
import com.hotel.entity.OrderEntity;
import com.hotel.entity.PromotionEntity;
import com.hotel.entity.TypeRoomEntity;
import com.hotel.repository.AccountRespository;
import com.hotel.repository.OrderRepository;
import com.hotel.repository.PromotionRespository;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.TypeRoomRespository;
import com.hotel.service.IHomeService;

@Service
public class HomeService implements IHomeService {
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private TypeRoomRespository typeRoomRespository;
	@Autowired
	private PromotionRespository promotionRespository;
	
	@Autowired AccountRespository accountRespository;

	static final Locale locale = new Locale("vi", "VN");
	static final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

	@Override
	public List<OrderEntity> getNewOrderList() {
		List<OrderEntity> list = orderRepository.getNewOrderList();
		return list;
	}

	@Override
	public List<OrderEntity> getOrderTodayCheckout() {
		List<OrderEntity> list = orderRepository.getOrderTodayCheckout();
		for (OrderEntity orderEntity : list) {
			if(orderEntity.getAccount()==null)
			{
				orderEntity.setAccount(accountRespository.findById(orderEntity.getCustomerId()));
			}
		}
		return list;
	}

	@Override
	public int getEmptyRoom() {
		int roomsCnt = 0;
		roomsCnt = roomRepository.findByStatus_Id(1).size();
		return roomsCnt;
	}

	@Override
	public List<PromotionEntity> getAvailablePromotion() {
		List<PromotionEntity> promotionList = new ArrayList<>();
		Pageable pageable = new PageRequest(0, 5);
		promotionList = promotionRespository.getPromotionAvailable(pageable);
		return promotionList;
	}

	@Override
	public String getRevenueToDay() {
		double revenue = 0;
		LocalDate date = LocalDate.now();
		revenue = orderRepository.getDayRevenue(date.toString());
		return currencyFormatter.format(revenue);
	}

	@Override
	public String getRevenueThisMonth() {
		double revenue = 0;
		LocalDate date = LocalDate.now(); // Create a date object
		revenue = orderRepository.getRevenueMonth(date.getMonthValue(), date.getYear());
		return currencyFormatter.format(revenue);
	}

	@Override
	public String getMostOrderTypeRoom() {
		String typeRoom = "";
		typeRoom = orderRepository.getMostOrderTypeRoom();
		return typeRoom;
	}

	@Override
	public int getPercentRoomFree() {
		int percent = 0;
		percent = roomRepository.getPercentRoomFree();
		return percent;
	}

	@Override
	public List<TypeRoomDTO> getTypeRoomPercent() {
		List<TypeRoomDTO> typeRoomDTOs = new ArrayList<TypeRoomDTO>();
		List<TypeRoomEntity> typeRoomEntities = typeRoomRespository.findAll();
		long totalOrder = orderRepository.count();
		for (TypeRoomEntity room : typeRoomEntities) {
			float percent = 0;
			percent = typeRoomRespository.getPercentByTypeRoomId(totalOrder, room.getId());
			TypeRoomDTO dto = new TypeRoomDTO();
			dto = TypeRoomConverter.toDTO(room);
			dto.setPercent(percent);
			typeRoomDTOs.add(dto);
		}
		return typeRoomDTOs;
	}

	@Override
	public int getCountOrderToday() {
		int count = 0;
		count = orderRepository.getOrderToday();
		return count;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		List<TypeRoomEntity> typeRoomEntities = typeRoomRespository.findAll();
		ChartPie chart = new ChartPie();

		long totalOrder = orderRepository.count();
		float totalPercent = 0;
		for (int i = 0; i < typeRoomEntities.size(); i++) {
			float percent = 0;
			if (i == typeRoomEntities.size() - 1) {
				percent = 100 - totalPercent;
			} else {
				percent = typeRoomRespository.getPercentByTypeRoomId(totalOrder, typeRoomEntities.get(i).getId());
				totalPercent += percent;
			}
			chart.addPoint(typeRoomEntities.get(i).getName(), percent);
		}
		chart.addDataPoint();
		return chart.getList();
	}

	@Override
	public List<List<Map<Object, Object>>> getRevenueMonthsOfYear() {
		ChartLineColumn chart = new ChartLineColumn();
		LocalDate date = LocalDate.now();

		for (int i = 1; i <= date.getMonthValue(); i++) {
			double revenue = orderRepository.getRevenueMonth(i, date.getYear());
			chart.addPoint(String.valueOf(i), revenue);
		}
		chart.addDataPoint();
		return chart.getList();
	}

	@Override
	public List<List<Map<Object, Object>>> getQuarterRevenue() {
		ChartPie chart = new ChartPie();
		LocalDate date = LocalDate.now();
		long totalRevenueYear = typeRoomRespository.getTotalRevenueOfYear(date.getYear());
		double totalPercent = 0;
		for (int i = 0; i <= 3; i++) {
			double percent = 0;
			if (i == 3) {
				percent = 100 - totalPercent;
			} else {
				percent = orderRepository.getRevenueQuarter(i * 3 + 1, i * 3 + 2, i * 3 + 3, totalRevenueYear);
				totalPercent += percent;
			}
			chart.addPoint("Quý " + (i + 1), percent);
		}
		chart.addDataPoint();
		return chart.getList();
	}

	@Override
	public List<List<Map<Object, Object>>> getRevenueRecentDays(int days) {
		ChartLineColumn chart = new ChartLineColumn();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		try {
			today = formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		today.setDate(today.getDate() - days);
		for (int i = 1; i <= days; i++) {
			Date newDate = DateUtils.addDays(today, i);
			long revenue = orderRepository.getDayRevenue(new SimpleDateFormat("yyyy-MM-dd").format(newDate));
			chart.addPoint(String.valueOf(newDate.getTime()), revenue);
		}
		chart.addDataPoint();
		return chart.getList();
	}
}
