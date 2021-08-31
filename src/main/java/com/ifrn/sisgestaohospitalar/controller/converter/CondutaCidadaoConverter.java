package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.CondutaCidadao;

@Component
public class CondutaCidadaoConverter implements Converter<String, CondutaCidadao> {

	@Override
	public CondutaCidadao convert(String condutaCidadao) {
		if (!StringUtils.isEmpty(condutaCidadao)) {
			for (CondutaCidadao conduta : CondutaCidadao.values()) {
				if (conduta.name().equals(condutaCidadao)) {
					return conduta;
				}
			}
		}

		return null;
	}
}
