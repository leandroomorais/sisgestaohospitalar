package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.CaraterAtendimento;

public class CaraterAtendimentoConverter implements Converter<String, CaraterAtendimento> {

	@Override
	public CaraterAtendimento convert(String caraterAtendimento) {
		if (!StringUtils.isEmpty(caraterAtendimento)) {
			for (CaraterAtendimento carater : CaraterAtendimento.values()) {
				if (carater.toString().equals(caraterAtendimento)) {
					return carater;
				}
			}
		}
		return null;
	}

}
