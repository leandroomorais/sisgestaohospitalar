package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.EstadoCivil;

public class EstadoCivilConverter implements Converter<String, EstadoCivil> {

	@Override
	public EstadoCivil convert(String source) {
		if (!StringUtils.isEmpty(source)) {
			for (EstadoCivil estadoCivil : EstadoCivil.values()) {
				if (estadoCivil.toString().equals(source)) {
					return estadoCivil;
				}
			}
		}
		return null;
	}

}
