package com.ifrn.sisgestaohospitalar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ifrn.sisgestaohospitalar.validation.Quantidade;

public class QuantidadeValidator implements ConstraintValidator<Quantidade, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value.equals(0)) {
			return false;
		} else {
			return true;
		}

	}

}
