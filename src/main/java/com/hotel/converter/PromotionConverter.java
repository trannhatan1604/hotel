package com.hotel.converter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.hotel.dto.PromotionDTO;
import com.hotel.entity.PromotionEntity;

@Component
public class PromotionConverter {
	public static PromotionDTO toDTO(PromotionEntity entity) {
		PromotionDTO dto = new PromotionDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setLevel(entity.getLevel());
		if(entity.getStartDate() == null || entity.getEndDate() == null) {
//			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
//			dto.setStartdate(new java.sql.Date(new Timestamp(System.currentTimeMillis()).getTime()));
//			dto.setEnddate(new java.sql.Date(new Timestamp(System.currentTimeMillis()).getTime()));
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date date = new java.util.Date();
			dto.setDateRange(dateFormat.format(date) + " - " + dateFormat.format(date));
		}
		else {
			dto.setStartdate(entity.getStartDate());
			dto.setEnddate(entity.getEndDate());
			dto.setDateRange(entity.getStartDate().toString() + " - " + entity.getEndDate().toString());
		}
		dto.setOrders(entity.getOrders());
		return dto;
	}
	
	public static PromotionEntity toEntity(PromotionDTO dto, String action){
		PromotionEntity entity = new PromotionEntity();
		if(action.equals("update")) entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setLevel(dto.getLevel());
		entity.setOrders(dto.getOrders());
		
		String[] dates = dto.getDateRange().split(" - ");		
		System.out.println(dates[0] + dates[1]);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date parsed;
		try {
			parsed = format.parse(dates[0]);
			java.sql.Date date1 = new java.sql.Date(format.parse(dates[0]).getTime());
			java.sql.Date date2 = new java.sql.Date(format.parse(dates[1]).getTime());
			entity.setStartDate(date1);
			entity.setEndDate(date2);
	        System.out.println("check sel date: " + date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
	
//	public static PromotionEntity toEntity(PromotionDTO dto, PromotionEntity entity) {
//		entity.setName(dto.getName());
//		entity.setDescription(dto.getDescription());
//		entity.setLevel(dto.getLevel());
//		System.out.println("check startdate:" + dto.getStartdate());
//		entity.setStartDate(dto.getStartdate());
//		entity.setEndDate(dto.getEnddate());
//		entity.setOrders(dto.getOrders());
//		return entity;
//	}
}
