package com.ifrn.sisgestaohospitalar.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ifrn.sisgestaohospitalar.service.Ciap2Service;

@Controller
@RequestMapping("/resource")
public class CiapResource {
	
	@Autowired
	private Ciap2Service ciap2Service;
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request){
		return ciap2Service.search(request.getParameter("term"));
	}

}
