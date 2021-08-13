package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringLongConverter implements Converter<String, Long> {

	@Override
	public Long convert(String source) {
		if (!StringUtils.isEmpty(source)) {
			return Long.parseLong(source);
		}
		return null;
	}

}
