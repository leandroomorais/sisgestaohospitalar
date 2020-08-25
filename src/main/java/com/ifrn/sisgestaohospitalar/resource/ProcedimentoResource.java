package com.ifrn.sisgestaohospitalar.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifrn.sisgestaohospitalar.model.ProcedimentoSigtap;
import com.ifrn.sisgestaohospitalar.service.ProcedimentoSigtapService;

@Controller
@RequestMapping("/procedimento-resource")
public class ProcedimentoResource {

	@Autowired
	private ProcedimentoSigtapService procedimentoSigtapService;

	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProcedimentoSigtap>> findByNome(HttpServletRequest request) {
//		if (procedimentosSigtap.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
		return ResponseEntity.ok(procedimentoSigtapService.findByNomeProcedimento(request.getParameter("term")));
	}
	
	@RequestMapping(value = "searchByCodigo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ProcedimentoSigtap> findByCodigo(HttpServletRequest request){
		return ResponseEntity.ok(procedimentoSigtapService.findByCodigoProcedimento(request.getParameter("term")));
	}

}
