package com.hotel.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hotel.valid.impl.NotNullCheckinDateValidator;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullCheckinDateValidator.class)
@Documented
public @interface NotNullCheckinDate {
	String message() default "Ngày phải là ngày hiện tại hoặc trong tương lai";
    String checkField() default "checkinDate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
