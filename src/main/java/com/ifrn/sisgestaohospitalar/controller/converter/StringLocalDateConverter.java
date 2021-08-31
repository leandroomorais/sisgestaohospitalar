package com.ifrn.sisgestaohospitalar.controller.converter;

import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringLocalDateConverter implements Converter<LocalDate, String> {
	
	@Override
	public String convert(LocalDate source) {
		return source.toString();
	}
}
