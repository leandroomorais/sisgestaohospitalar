package com.ifrn.sisgestaohospitalar.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.ifrn.sisgestaohospitalar.validator.QuantidadeValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { QuantidadeValidator.class })
public @interface Quantidade {

	String message() default "Quantidade a ser dispensada não pode ser igual a 0";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
