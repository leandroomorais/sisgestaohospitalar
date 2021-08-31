package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoubleStringConverter implements Converter<Double, String> {

	@Override
	public String convert(Double source) {
		return source.toString();
	}

}
