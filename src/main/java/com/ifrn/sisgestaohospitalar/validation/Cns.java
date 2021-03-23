package com.ifrn.sisgestaohospitalar.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.ifrn.sisgestaohospitalar.validator.CnsValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CnsValidator.class})
public @interface Cns {
	String message() default "CNS inválido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
