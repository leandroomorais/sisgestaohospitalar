package com.ifrn.sisgestaohospitalar.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.service.CidadaoService;

@Controller
@RequestMapping("/cidadao-resource")
public class CidadaoResource {
	@Autowired
	private CidadaoService cidadaoService;
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cidadao> findByCPF(@PathVariable("cpf") String cpf){
		Cidadao cidadao = cidadaoService.findByCpf(cpf);
		if (cidadao != null) {
			return ResponseEntity.ok(cidadao);
		}
		return ResponseEntity.notFound().build();
	}
}
