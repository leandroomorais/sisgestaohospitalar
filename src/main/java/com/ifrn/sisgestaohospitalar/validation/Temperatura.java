package com.ifrn.sisgestaohospitalar.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.ifrn.sisgestaohospitalar.validator.TemperaturaValidator;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TemperaturaValidator.class })
public @interface Temperatura {

	String message() default "Temperatura inválida - Valor deve estar entre 1.0 e 50.0 ºC";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
