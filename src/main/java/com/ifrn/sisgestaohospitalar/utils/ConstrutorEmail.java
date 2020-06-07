package com.ifrn.sisgestaohospitalar.utils;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.ifrn.sisgestaohospitalar.model.Profissional;

/**
 * A classe <code>ConstrutorEmail</code> é um utilitário que contém métodos para
 * o envio de email contendo instruções para recuperação de senha do usuário.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Component
public class ConstrutorEmail {

	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * Método para construir email com instruções de recuperação de senha
	 * 
	 * @param contextPath
	 * @param locale
	 * @param token
	 * @param profissional
	 * @return messagePreparator
	 */
	public MimeMessagePreparator emailRedefinicaoSenha(String contextPath, Locale locale, String token,
			Profissional profissional) {

		String url = contextPath + "/nova-senha?token=" + token;
		String message = "Por favor, clique neste link para verificar seu email e redefinir sua senha: ";
		Context context = new Context();
		context.setVariable("profissional", profissional);
		context.setVariable("url", url);
		context.setVariable("message", message);
		String text = templateEngine.process("/email/resetPasswordTemplate", context);

		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(profissional.getEmail());
				email.setSubject("Redefinição de senha: " + profissional.getFirstname());
				email.setText(text, true);
				email.setFrom(new InternetAddress("gestaoescolaronline1.0@gmail.com"));

			}
		};

		return messagePreparator;
	}

}
