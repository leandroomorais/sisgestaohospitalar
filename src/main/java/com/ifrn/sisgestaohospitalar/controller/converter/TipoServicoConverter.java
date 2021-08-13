//package com.ifrn.sisgestaohospitalar.controller.converter;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.ifrn.sisgestaohospitalar.enums.TipoServico;
//
//@Component
//public class TipoServicoConverter implements Converter<String, TipoServico> {
//
//	@Override
//	public TipoServico convert(String condutaIipoServico) {
//		if (!StringUtils.isEmpty(condutaIipoServico)) {
//			for (TipoServico servico : TipoServico.values()) {
//				if (servico.name().equals(condutaIipoServico)) {
//					return servico;
//				}
//			}
//		}
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
