package com.hotel.valid.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotel.dto.OrderDTO;
import com.hotel.service.IOrderService;
import com.hotel.valid.FutureOrPresentAndNotNull;

public class FutureOrPresentAndNotNullValidator implements ConstraintValidator<FutureOrPresentAndNotNull, OrderDTO> {

	private String checkField1;
	private String checkField2;
	private String requiredField;

	@Autowired
	private IOrderService orderService;

	@Override
	public void initialize(FutureOrPresentAndNotNull constraintAnnotation) {
		this.checkField1 = constraintAnnotation.checkField1();
		this.checkField2 = constraintAnnotation.checkField2();
		this.requiredField = constraintAnnotation.requiredField();
	}

	@Override
	public boolean isValid(OrderDTO value, ConstraintValidatorContext context) {
		// Value của CheckinDate
		Object checkField1Value = new BeanWrapperImpl(value).getPropertyValue(checkField1);
		// Value của CheckoutDate
		Object checkField2Value = new BeanWrapperImpl(value).getPropertyValue(checkField2);
		// Value của orderid
		Object requiredFieldValue = new BeanWrapperImpl(value).getPropertyValue(requiredField);
		// Value cua room id
		Object roomId = new BeanWrapperImpl(value).getPropertyValue("roomId");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date currentDate;
		boolean isValid = true;

		context.disableDefaultConstraintViolation(); // Ngăn không cho hiển thị lỗi mặc định

		// Kiểm tra null của checkinDate và checkoutDate
		if (checkField1Value == null) {
			context.buildConstraintViolationWithTemplate("Vui lòng nhập ngày nhận phòng!").addPropertyNode(checkField1)
					.addConstraintViolation();
			isValid = false;
		}

		if (checkField2Value == null) {
			context.buildConstraintViolationWithTemplate("Vui lòng nhập ngày trả phòng!").addPropertyNode(checkField2)
					.addConstraintViolation();
			isValid = false;
		}

		if (!isValid) {
			return false;
		}

		try {
			currentDate = formatter.parse(formatter.format(today));
			Date checkinDate = (Date) checkField1Value;
			Date checkoutDate = (Date) checkField2Value;

			// Kiểm tra ngày checkin có lớn hơn ngày checkout không
			if (checkoutDate.compareTo(checkinDate) < 0) {
				context.buildConstraintViolationWithTemplate("Ngày trả phòng phải sau ngày nhận phòng!")
						.addPropertyNode(checkField2).addConstraintViolation();
				return false;
			}
			// Kiểm tra ngày checkin có bé hơn ngày checkout không
			if (checkinDate.compareTo(checkoutDate) > 0) {
				context.buildConstraintViolationWithTemplate("Ngày nhận phòng phải trước ngày trả phòng!")
						.addPropertyNode(checkField1).addConstraintViolation();
				return false;
			}
			// Kiểm tra id khác null (Dựa vào đó biết đang lỗi sẽ apply cho form add hay
			// update)
			if (requiredFieldValue != null) {
				if (!orderService.isRoomAvailableInUpdate((Integer) requiredFieldValue,(Integer) roomId, (Date) checkField1Value, (Date) checkField2Value)) {
					System.out.println("Loi ở checkinDate");
					context.buildConstraintViolationWithTemplate("Đã có người đặt trong khoảng này. Vui lòng chọn lại!")
							.addPropertyNode(checkField1).addConstraintViolation();
					isValid = false;
				}

				if (!orderService.isRoomAvailableInUpdate((Integer) requiredFieldValue,(Integer) roomId, (Date) checkField1Value, (Date) checkField2Value)) {
					System.out.println("Loi ở checkoutDate");
					context.buildConstraintViolationWithTemplate("Đã có người đặt trong khoảng này. Vui lòng chọn lại!")
							.addPropertyNode(checkField2).addConstraintViolation();
					isValid = false;
				}

				return isValid;
			} else {
				// Kiểm tra checkinDate có bé hơn ngày hiện tại không
				if (checkinDate.compareTo(currentDate) < 0) {
					context.buildConstraintViolationWithTemplate("Ngày phải là ngày hiện tại hoặc trong tương lai")
							.addPropertyNode(checkField1).addConstraintViolation();
					isValid = false;
				} else {
					if (!orderService.isRoomAvailableInAdd((Integer) roomId, (Date) checkField1Value,
							(Date) checkField2Value)) {
						System.out.println("Loi ở checkinDate");
						context.buildConstraintViolationWithTemplate(
								"Đã có người đặt trong khoảng này. Vui lòng chọn lại!").addPropertyNode(checkField1)
								.addConstraintViolation();
						isValid = false;
					}
				}

				// Kiểm tra checkoutDate có bé hơn ngày hiện tại không
				if (checkoutDate.compareTo(currentDate) < 0) {
					context.buildConstraintViolationWithTemplate("Ngày phải là ngày hiện tại hoặc trong tương lai")
							.addPropertyNode(checkField2).addConstraintViolation();
					isValid = false;
				} else {
					if (!orderService.isRoomAvailableInAdd((Integer) roomId, (Date) checkField1Value,
							(Date) checkField2Value)) {
						System.out.println("Loi ở checkoutDate");
						context.buildConstraintViolationWithTemplate(
								"Đã có người đặt trong khoảng này. Vui lòng chọn lại!").addPropertyNode(checkField2)
								.addConstraintViolation();
						isValid = false;
					}
				}
			}

			return isValid;

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
}
