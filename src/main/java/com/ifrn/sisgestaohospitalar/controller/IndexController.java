package com.ifrn.sisgestaohospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * A classe Controller <code>IndexController</code> possui os métodos de
 * controle para acesso da página Principal e de Login do Sistema.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Controller
public class IndexController {
	
	/**
	 * Direciona o usuário para a Página de Login
	 * @return 
	 */
	@RequestMapping(method = RequestMethod.GET, path = { "/entrar" })
	public ModelAndView entrar() {
		ModelAndView mv = new ModelAndView("login/login");
		return mv;
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}



}
