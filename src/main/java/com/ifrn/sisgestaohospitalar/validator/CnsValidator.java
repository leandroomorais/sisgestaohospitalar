package com.ifrn.sisgestaohospitalar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ifrn.sisgestaohospitalar.validation.Cns;

public class CnsValidator implements ConstraintValidator<Cns, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		value = value.replace(".", "");
		if (value.matches("[1-2]\\d{10}00[0-1]\\d") || value.matches("[7-9]\\d{14}")) {
			return somaPonderada(value) % 11 == 0;
		}
		return false;
	}

	private int somaPonderada(String s) {
		char[] cs = s.toCharArray();
		int soma = 0;
		for (int i = 0; i < cs.length; i++) {
			soma += Character.digit(cs[i], 10) * (15 - i);
		}
		return soma;
	}

}
