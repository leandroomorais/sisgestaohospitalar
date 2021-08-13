package com.ifrn.sisgestaohospitalar.controller.converter;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringDateTimeConverter implements Converter<LocalDateTime, String> {

	@Override
	public String convert(LocalDateTime source) {
		return source.toString();
	}

}
