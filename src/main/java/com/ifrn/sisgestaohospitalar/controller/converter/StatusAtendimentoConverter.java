package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.StatusAtendimento;

public class StatusAtendimentoConverter implements Converter<String, StatusAtendimento> {

	@Override
	public StatusAtendimento convert(String statusAtendimento) {
		if(!StringUtils.isEmpty(statusAtendimento)) {
			for(StatusAtendimento status : StatusAtendimento.values()){
				if(status.toString().equals(statusAtendimento)) {
					return status;
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
