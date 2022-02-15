package com.ifrn.sisgestaohospitalar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Cid;
import com.ifrn.sisgestaohospitalar.model.ProcedimentoCid;
import com.ifrn.sisgestaohospitalar.repository.AtendimentoProcedimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.CidRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoCidRepository;

@Controller
@RequestMapping("/procedimentocid")
public class ProcedimentoCidController {

	@Autowired
	private ProcedimentoCidRepository procedimentocidRepository;
	
	@Autowired
	private AtendimentoProcedimentoRepository atendimentoProcedimentoRepository;
	
	@Autowired
	private CidRepository cidRepository;
	
	public List<Cid> cidsglobal = new ArrayList<Cid>();
	public List<Cid> listacidsselect = new ArrayList<Cid>();
	
	@GetMapping("/buscarid/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<ProcedimentoCid> optional = procedimentocidRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarcidsdoprocedimento/{idProcedimentoCid}")
	public ResponseEntity<?> findCidsByProcedimento(@PathVariable("idProcedimentoCid") Long idProcedimentoCid) {
		
		Optional<AtendimentoProcedimento> atendProcedimento = atendimentoProcedimentoRepository.findById(idProcedimentoCid);
		Long codigoProcedimento = atendProcedimento.get().getProcedimento().getCodigo();
		
		List<ProcedimentoCid> procedimentoscid = procedimentocidRepository.findByCodigoProcedimento(codigoProcedimento);
		List<Cid> cids = new ArrayList<Cid>();
		
		if(atendProcedimento.get().getCodigoCid() != null) {
			Cid cid = cidRepository.findByCodigoIgnoreCaseContaining(atendProcedimento.get().getCodigoCid());
			if(cid != null) {
				cids.add(cid);
			}
		}
		
		if(!procedimentoscid.isEmpty()) {
			cids.addAll(cidRepository.findByCodigoProcedimentoCid(codigoProcedimento));
			return ResponseEntity.ok(cids);
		}		
		
		String msg = "Esse procedimento não requer a adição de CID";
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
	}
	
	@GetMapping("/verificaprocedimentocidobrigatorio/{codigoProcedimento}")
	public ResponseEntity<?> verificaCidObrigatorioAoProcedimento(@PathVariable("codigoProcedimento") Long codigoProcedimento) {

		List<ProcedimentoCid> procedimentoscid = procedimentocidRepository.findByCodigoProcedimento(codigoProcedimento);		
		
		if(codigoProcedimento == 203010035 | codigoProcedimento == 203020030) {
			String msg = "O CID é obrigatório";
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
		}
		
		if(!procedimentoscid.isEmpty()) {			
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@GetMapping("/buscarcodigoprocedimentocid/{codigoProcedimento}")
	public ResponseEntity<?> findByCodigoProcedimentoCid(@PathVariable("codigoProcedimento") Long codigoProcedimento) {
		List<ProcedimentoCid> procedimentoscid = procedimentocidRepository.findByCodigoProcedimento(codigoProcedimento);
		List<Cid> cids = new ArrayList<Cid>();
		
		if(!procedimentoscid.isEmpty()) {
			cids = retornaListaCid(procedimentoscid);
			cidsglobal = cids;
			
			return ResponseEntity.ok(cids);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarcid/")
	public ResponseEntity<?> findBuscarCid() {
		return ResponseEntity.ok(cidsglobal);
	}
	
	
	public List<Cid> retornaListaCid(List<ProcedimentoCid> procedimentoscids){
		
		List<Cid> cids = new ArrayList<Cid>();
		
		for(ProcedimentoCid procid : procedimentoscids) {
			cids.add(cidRepository.findByCodigoIgnoreCaseContaining(procid.getCodigoCid()));
		}
		
		return cids;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> adicionarListaCidSelect(@Valid ProcedimentoCid procedimentocid, BindingResult result){
		
		Map<String, String> errors = new HashMap<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		System.out.println("aqui");
		listacidsselect.add(cidRepository.findByCodigoIgnoreCaseContaining(procedimentocid.getCodigoCid()));
		

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/buscarcidselect/")
	public ResponseEntity<?> findBuscarCidSelect(@Param("term") String term) {
		return ResponseEntity.ok(listacidsselect);
	}
	
}
