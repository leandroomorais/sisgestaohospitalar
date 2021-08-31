package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StringDoubleConverter implements Converter<String, Double> {

	@Override
	public Double convert(String source) {
		if (!StringUtils.isEmpty(source) || source != "") {
			return Double.parseDouble(source);
		} else {
			return Double.valueOf(0);
		}
	}

}
