package com.ifrn.sisgestaohospitalar.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.sisgestaohospitalar.model.TokenRedefinicao;
import com.ifrn.sisgestaohospitalar.repository.TokenRedefinicaoRepository;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.utils.ConstrutorEmail;
import com.ifrn.sisgestaohospitalar.utils.GeradorSenha;

/**
 * A classe Controller <code>IndexController</code> possui os métodos de
 * controle para acesso da página de Login, Página Principal e Recuperação de
 * Senha do Sistema.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Controller
public class IndexController {

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ConstrutorEmail construtorEmail;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TokenRedefinicaoRepository tokenRedefinicaoRepository;

	private String tokenRedefinicao;

	private TokenRedefinicao passToken;

	/**
	 * Direciona o usuário para a Página de Login
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, path = { "/entrar" })
	public ModelAndView entrar() {
		ModelAndView mv = new ModelAndView("login/login");
		return mv;
	}

	/**
	 * Direciona o usuário para a Página Principal
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	/**
	 * Direciona o usuário para a página de Recuperação de Senha
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/forget")
	public ModelAndView forget() {
		ModelAndView mv = new ModelAndView("login/forgetPassword");
		return mv;
	}

	/**
	 * Em caso de sucesso, envia para o usuário email contendo as instruções de
	 * recuperação de senha, em caso de falha, exibe mensagem ao usuário.
	 * 
	 * @param request
	 * @param email
	 * @return ModelAndView
	 */
	@RequestMapping("/forgetPassword")
	public ModelAndView forgetPassword(HttpServletRequest request, @RequestParam("email") String email) {
		ModelAndView mv = forget();
		Profissional profissional = profissionalService.findByEmail(email);

		if (profissional == null) {
			mv.addObject("erro", " Email não encontrado em nossa base de dados.");
			return mv;
		}

		String password = GeradorSenha.senhaAleatoria();
		String encyptedPassword = GeradorSenha.passwordEncoder().encode(password);
		profissional.setPassword(encyptedPassword);

		profissionalService.save(profissional);

		String token = UUID.randomUUID().toString();
		profissionalService.gerarTokenRedefinicao(profissional, token);

		String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		MimeMessagePreparator newEmail = construtorEmail.emailRedefinicaoSenha(appUrl, request.getLocale(), token,
				profissional);

		mailSender.send(newEmail);

		mv.addObject("sucesso", " Um email foi enviado para " + email + " com as instruções de redefinição de senha.");

		return mv;
	}

	/**
	 * Se o usuário tiver Token válido, direciona para a página de criação de nova
	 * senha, caso contrário exibe mensagem de erro de Token inválido
	 * 
	 * @param locale
	 * @param token
	 * @return ModelAndView
	 */
	@RequestMapping("/new-password")
	public ModelAndView newPassword(Locale locale, @RequestParam("token") String token) {
		ModelAndView mv = new ModelAndView("login/new-password");
		passToken = profissionalService.getTokenRedefinicao(token);

		if (passToken == null || !token.equals(passToken.getToken())) {
			mv.addObject("erro", "Token inválido");
			return mv;
		} else if (passToken.getToken().equals(token)) {
			tokenRedefinicao = token;
			mv.addObject("mostrar", "Token válido");
		}

		return mv;
	}

	/**
	 * Atualiza a senha do usuário
	 * 
	 * @param password
	 * @param profissional
	 * @throws Exception
	 * @return ModelAndView
	 */
	@PostMapping("/update-password")
	public ModelAndView updatePassword(@RequestParam("password") String password, Profissional profissional)
			throws Exception {

		ModelAndView mv = entrar();
		TokenRedefinicao passToken = profissionalService.getTokenRedefinicao(tokenRedefinicao);

		profissional = passToken.getProfissional();
		profissional.setPassword(new BCryptPasswordEncoder().encode(password));

		profissionalService.save(profissional);

		tokenRedefinicaoRepository.delete(passToken);

		mv.addObject("sucesso", " A nova senha foi cadastrada.");

		return mv;
	}

}
