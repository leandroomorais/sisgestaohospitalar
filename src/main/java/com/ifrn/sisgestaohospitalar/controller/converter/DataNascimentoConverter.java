package com.ifrn.sisgestaohospitalar.controller.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataNascimentoConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String data) {
		System.out.println(data);
		if (!StringUtils.isEmpty(data)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(data, formatter);
		}
		return null;
	}

}
