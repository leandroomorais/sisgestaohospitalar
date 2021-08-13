package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringIntConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		if (!StringUtils.isEmpty(source) || source != "") {
			return Integer.parseInt(source);
		}
		return Integer.valueOf(0);
	}
}
