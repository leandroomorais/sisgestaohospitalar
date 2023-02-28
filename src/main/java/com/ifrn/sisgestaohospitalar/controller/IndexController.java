package com.ifrn.sisgestaohospitalar.controller;

import com.ifrn.sisgestaohospitalar.model.PasswordResetToken;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.PasswordResetTokenRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;
import com.ifrn.sisgestaohospitalar.service.EstabelecimentoService;
import com.ifrn.sisgestaohospitalar.service.ProfissionalService;
import com.ifrn.sisgestaohospitalar.service.UsuarioSecurityService;
import com.ifrn.sisgestaohospitalar.utils.ConstrutorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.UUID;

@Controller
public class IndexController {

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioSecurityService usuarioSecurityService;

	@Autowired
	private ConstrutorEmail construtorEmail;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	private String tempToken;

	/**
	 * Direciona o usuário para a Página de Login
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(method = RequestMethod.GET, path = { "/entrar" })
	public ModelAndView entrar() {
		ModelAndView mv = new ModelAndView("entrar/entrar");
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
		Usuario usuario = usuarioSecurityService.authenticathedUser();
		if(usuario != null && usuario.isFirstAccess()){
			return new ModelAndView("redirect:/primeiro-acesso");
		}
		mv.addObject("estabelecimento", estabelecimentoService.findAll());
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
	@RequestMapping("/recuperar-senha")
	public ResponseEntity<Profissional> forgetPassword(HttpServletRequest request,
													   @RequestParam("email") String email) {
		Profissional profissional = profissionalService.findByEmail(email);
		if (profissional == null) {
			return ResponseEntity.badRequest().build();
		} else {
			Usuario usuario = usuarioRepository.findByUsername(profissional.getCpf());
			if (usuario == null) {
				return ResponseEntity.badRequest().build();
			} else {
				String token = UUID.randomUUID().toString();
				usuarioSecurityService.gerarTokenRedefinicao(usuario, token);
				String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				MimeMessagePreparator newEmail = construtorEmail.emailRedefinicaoSenha(appUrl, request.getLocale(),
						token, profissional);
				mailSender.send(newEmail);
				return ResponseEntity.ok().build();
			}
		}
	}

	/**
	 * Se o usuário tiver Token válido, direciona para a página de criação de nova
	 * senha, caso contrário exibe mensagem de erro de Token inválido
	 *
	 * @param locale
	 * @param token
	 * @return ModelAndView
	 */
	@RequestMapping("/nova-senha")
	public ModelAndView newPassword(Locale locale, @RequestParam("token") String token) {
		ModelAndView mv = new ModelAndView("entrar/nova-senha");
		PasswordResetToken passToken = passwordResetTokenRepository.findByToken(token);
		if (passToken == null || !token.equals(passToken.getToken())) {
			mv.addObject("erro", "Token inválido");
			return mv;
		} else if (passToken.getToken().equals(token)) {
			tempToken = token;
			mv.addObject("mostrar", "Token válido");
		}
		return mv;
	}

	@RequestMapping("/primeiro-acesso")
	public ModelAndView newPasswordFirstAccess(Locale locale) {
		ModelAndView mv = new ModelAndView("entrar/nova-senha-email");
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
	@PostMapping("/cadastra-senha")
	public ModelAndView updatePassword(@RequestParam("password") String password, Profissional profissional)
			throws Exception {
		ModelAndView mv = entrar();
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(tempToken);
		Usuario usuario = passwordResetToken.getUsuario();
		usuario.setPassword(new BCryptPasswordEncoder().encode(password));
		usuarioRepository.saveAndFlush(usuario);
		passwordResetTokenRepository.deleteById(passwordResetToken.getId());
		mv.addObject("sucesso", " A nova senha foi cadastrada.");
		return mv;
	}

	@PostMapping("/primeiro-acesso/senha")
	public ModelAndView updatePasswordFirstAccess(@RequestParam("email") String email, @RequestParam("password") String password)
			throws Exception {
		ModelAndView mv = entrar();
		Usuario usuario = usuarioSecurityService.authenticathedUser();
		usuario.setPassword(new BCryptPasswordEncoder().encode(password));
		usuario.setFirstAccess(false);
		Profissional profissional = profissionalService.findByCpf(usuario.getUsername());
		profissional.setEmail(email);
		profissionalService.save(profissional);
		usuarioRepository.saveAndFlush(usuario);
		mv.addObject("sucesso", " A nova senha e o novo e-mail foi cadastrado.");
		return mv;
	}

}