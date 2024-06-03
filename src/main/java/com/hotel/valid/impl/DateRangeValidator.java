package com.hotel.valid.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hotel.dto.PromotionDTO;
import com.hotel.valid.DateRange;

public class DateRangeValidator implements ConstraintValidator<DateRange, PromotionDTO> {
	private String checkField;

	@Override
	public void initialize(DateRange constraintAnnotation) {
		this.checkField = constraintAnnotation.checkField();

	}

	@Override
	public boolean isValid(PromotionDTO value, ConstraintValidatorContext context) {
		// Value của DateRange
		String dateRange = value.getDateRange();
		String[] times = dateRange.split(" - ");
		if (times.length == 2) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date checkinDate = new Date(format.parse(times[2]).getTime());
				Date checkoutDate = new Date(format.parse(times[1]).getTime());
				if (checkinDate.compareTo(checkoutDate) >= 0) {
					context.buildConstraintViolationWithTemplate("Ngày nhận phòng phải trước ngày trả phòng!")
							.addPropertyNode(checkField).addConstraintViolation();
					return false;
				}
				if (checkoutDate.compareTo(checkinDate) <= 0) {
					context.buildConstraintViolationWithTemplate("Ngày nhận phòng phải trước ngày trả phòng!")
							.addPropertyNode(checkField).addConstraintViolation();
					return false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

}
