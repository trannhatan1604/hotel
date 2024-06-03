package com.hotel.valid.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.hotel.dto.OrderDTO;
import com.hotel.valid.NotNullCheckinDate;

public class NotNullCheckinDateValidator implements ConstraintValidator<NotNullCheckinDate, OrderDTO> {
	private String checkField;

	@Override
	public void initialize(NotNullCheckinDate constraintAnnotation) {
		this.checkField = constraintAnnotation.checkField();
	}

	@Override
	public boolean isValid(OrderDTO value, ConstraintValidatorContext context) {
		// Value cua checkInDate
		Object checkFieldValue = new BeanWrapperImpl(value).getPropertyValue(checkField);
		if (checkFieldValue != null) {
			return true;
		}
		context.disableDefaultConstraintViolation(); // Ngăn không cho hiển thị lỗi mặc định
		context.buildConstraintViolationWithTemplate("Vui lòng nhập ngày đến!")
				.addPropertyNode(checkField).addConstraintViolation();
		return false;
	}

}
