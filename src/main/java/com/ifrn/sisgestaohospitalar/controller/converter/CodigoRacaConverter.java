package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.CodigoRaca;

@Component
public class CodigoRacaConverter implements Converter<String, CodigoRaca> {

	@Override
	public CodigoRaca convert(String raca) {
		System.out.println(raca);
		if (!StringUtils.isEmpty(raca)) {
			for (CodigoRaca codigoRaca : CodigoRaca.values()) {
				if (codigoRaca.getCodigo() == Integer.parseInt(raca)) {
					return codigoRaca;
				}
			}
		}
		return null;
	}
}
