package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.model.Cid;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;

@Component
public class CidConverter implements Converter<String, Cid> {

	@Autowired
	private CidRepository cidRepository;

	@Override
	public Cid convert(String source) {
		if (!StringUtils.isEmpty(source)) {
			Cid cid = cidRepository.findByCodigoIgnoreCaseContaining(source);
			if (cid != null) {
				return cid;
			}
		}
		return null;
	}

}
