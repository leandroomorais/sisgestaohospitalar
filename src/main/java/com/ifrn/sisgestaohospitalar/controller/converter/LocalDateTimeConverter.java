package com.ifrn.sisgestaohospitalar.controller.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LocalDateTimeConverter implements org.springframework.core.convert.converter.Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		if(!StringUtils.isEmpty(source)) {
			return LocalDateTime.parse(source);
		}
		return null;
	}

}
