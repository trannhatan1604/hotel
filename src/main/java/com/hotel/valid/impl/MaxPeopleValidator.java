package com.hotel.valid.impl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotel.dto.OrderDTO;
import com.hotel.entity.RoomEntity;
import com.hotel.service.IRoomService;
import com.hotel.valid.MaxPeople;

public class MaxPeopleValidator implements ConstraintValidator<MaxPeople, OrderDTO> {
	
	@Autowired
	private IRoomService roomService;
	
	private String checkField;
	private String requiredFiedld;
	

    @Override
    public void initialize(MaxPeople constraintAnnotation) {
    	this.checkField = constraintAnnotation.checkField();
    	this.requiredFiedld = constraintAnnotation.requiredField();
    }

    @Override
    public boolean isValid(OrderDTO value, ConstraintValidatorContext context) {
    	// Value cua quantity
    	Object checkFieldValue = new BeanWrapperImpl(value).getPropertyValue(checkField);
    	// Value cua roomId
    	Object requiredFieldValue = new BeanWrapperImpl(value).getPropertyValue(requiredFiedld);
    	if(checkFieldValue != null && requiredFieldValue != null) {
    		RoomEntity room = roomService.findById((int) requiredFieldValue);
    		if((int) checkFieldValue > room.getTyperoom().getQuantity()) {
    			context.disableDefaultConstraintViolation(); // Ngăn không cho hiển thị lỗi mặc định
	            context.buildConstraintViolationWithTemplate("Số lượng người vượt quá số lượng tối đa của phòng!").addPropertyNode(checkField).addConstraintViolation();
    			return false;
    		}
    		else {
    			return true;
    		}
    	}
    	else {
    		return true;
    	}
      
    }
}