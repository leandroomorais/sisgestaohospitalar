package com.ifrn.sisgestaohospitalar.resource;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ifrn.sisgestaohospitalar.service.Ciap2Service;

/**
 * A classe Controller <code>CIAP2Resouce</code> contém métodos para dados da
 * Tabela CIAP2
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Controller
@RequestMapping("/ciap2-resource")
public class CIAP2Resource {

	@Autowired
	Ciap2Service ciap2Service;

	/**
	 * Retorna a lista com os tipos de motivos de consulta do Cidadão conforme
	 * tabela CIAP2
	 * 
	 * @param request
	 * @return List<String>
	 */
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request) {
		return ciap2Service.search(request.getParameter("term"));
	}

}
