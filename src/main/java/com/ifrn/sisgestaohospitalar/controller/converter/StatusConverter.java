package com.ifrn.sisgestaohospitalar.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.ifrn.sisgestaohospitalar.enums.Status;

public class StatusConverter implements Converter<String, Status> {

	@Override
	public Status convert(String statusAtendimento) {
		if (!StringUtils.isEmpty(statusAtendimento)) {
			for (Status status : Status.values()) {
				if (status.toString().equals(statusAtendimento)) {
					return status;
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
