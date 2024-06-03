package com.hotel.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hotel.valid.impl.FutureOrPresentAndNotNullValidator;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureOrPresentAndNotNullValidator.class)
@Documented
public @interface DateRange {
	 String checkField() default "dateRange";
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default {};
}
