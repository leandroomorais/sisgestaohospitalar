package com.ifrn.sisgestaohospitalar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ifrn.sisgestaohospitalar.model.Cep_Ibge;
import com.ifrn.sisgestaohospitalar.model.Municipio;
import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;

@Controller
@RequestMapping("/municipio")
public class MunicipioController {

	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private Cep_IbgeRepository cepIbgeRepository;

	@GetMapping
	public ResponseEntity<List<Municipio>> autoCompletarPorNome(@RequestParam("term") String nome) {
		return ResponseEntity.ok(municipioRepository.findBynomeMunicipioSiglaUFIgnoreCaseContaining(nome));
	}

	@GetMapping("/cep")
	public ResponseEntity<?> buscaMunicipioCep(@RequestParam("cep") Long cep) {
		Cep_Ibge cepIbge = cepIbgeRepository.findBycep(cep);
		if (cepIbge != null) {
			return ResponseEntity.ok(municipioRepository.findBycodigoIBGE(cepIbge.getCdIbge()));
		}
		return ResponseEntity.notFound().build();
	}

}
