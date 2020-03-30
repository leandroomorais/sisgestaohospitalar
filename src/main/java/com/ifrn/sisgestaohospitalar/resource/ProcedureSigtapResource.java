package com.ifrn.sisgestaohospitalar.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ifrn.sisgestaohospitalar.model.ProcedureSigtap;
import com.ifrn.sisgestaohospitalar.service.ProcedureSigtapService;

@Controller
@RequestMapping("/resource/procedure")
public class ProcedureSigtapResource {

	@Autowired
	private ProcedureSigtapService procedureSigtapService;
	
	@RequestMapping(value="findByNome", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<List<ProcedureSigtap>> findByNome(HttpServletRequest request){
		
		List<ProcedureSigtap> procedures = procedureSigtapService.findByNameprocedure(request.getParameter("term"));
		
		if(procedures.isEmpty()){
			return ResponseEntity.notFound().build();		
		}
		
		return ResponseEntity.ok(procedures);
		
	}

}
