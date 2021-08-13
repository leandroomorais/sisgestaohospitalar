package com.ifrn.sisgestaohospitalar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ifrn.sisgestaohospitalar.validation.Temperatura;

public class TemperaturaValidator implements ConstraintValidator<Temperatura, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if(value >= 00.0 && value <= 50.0) {
			return true;
		}
		return false;
	}
}
