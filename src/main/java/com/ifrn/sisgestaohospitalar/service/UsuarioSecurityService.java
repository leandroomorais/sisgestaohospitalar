package com.ifrn.sisgestaohospitalar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.PasswordResetToken;
import com.ifrn.sisgestaohospitalar.model.Usuario;
import com.ifrn.sisgestaohospitalar.repository.PasswordResetTokenRepository;
import com.ifrn.sisgestaohospitalar.repository.UsuarioRepository;

@Service
@Primary
public class UsuarioSecurityService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Nome de usuário não encontrado!");
		}

		return usuario;
	}

	public void gerarTokenRedefinicao(Usuario usuario, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, usuario);
		passwordResetTokenRepository.saveAndFlush(passwordResetToken);
	}

}