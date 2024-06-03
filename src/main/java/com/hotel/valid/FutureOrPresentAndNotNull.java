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
public @interface FutureOrPresentAndNotNull {
    String message() default "Ngày phải là ngày hiện tại hoặc trong tương lai";
    String checkField1() default "checkinDate";
    String checkField2() default "checkoutDate";
    String requiredField() default "id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}