package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LongStringConverter implements Converter<Long, String> {

	@Override
	public String convert(Long source) {
		return source.toString();
	}

}
