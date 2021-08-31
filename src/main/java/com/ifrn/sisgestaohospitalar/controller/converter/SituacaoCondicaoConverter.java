package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.SituacaoCondicao;

@Component
public class SituacaoCondicaoConverter implements Converter<String, SituacaoCondicao> {

	@Override
	public SituacaoCondicao convert(String source) {
		if (!StringUtils.isEmpty(source)) {
			for (SituacaoCondicao situacaoCondicao : SituacaoCondicao.values()) {
				if (situacaoCondicao.name().equals(source)) {
					return situacaoCondicao;
				}
			}
		}
		return null;
	}

}
