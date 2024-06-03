package com.hotel.valid;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hotel.valid.impl.MaxPeopleValidator;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxPeopleValidator.class)
public @interface MaxPeople {
	String message() default "Số lượng người vượt quá số lượng tối đa của phòng!";
	String checkField() default "quantity";
	String requiredField() default "roomId";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
