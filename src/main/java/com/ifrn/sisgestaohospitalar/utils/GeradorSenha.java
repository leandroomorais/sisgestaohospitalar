//package com.ifrn.sisgestaohospitalar.utils;
//
//import java.security.SecureRandom;
//import java.util.Random;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
///**
// * A classe <code>GeradorSenha</code> é um utilitário que contém método para
// * geração de senhas aleatórias.
// * 
// * @author Leandro Morais
// * @version 1.0, 02/11/2019
// *
// */
//
//@Component
//public class GeradorSenha {
//
//	private static final String SALT = "salt";
//
//	/**
//	 * Codifica a senha
//	 * 
//	 * @return BCryptPasswordEncoder
//	 */
//	@Bean
//	public static BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
//	}
//
//	/**
//	 * Gera senha aleatória
//	 * 
//	 * @return String
//	 */
//	@Bean
//	@Primary
//	public static String senhaAleatoria() {
//		String SALTCHARS = "ABCEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//		StringBuilder salt = new StringBuilder();
//		Random rnd = new Random();
//
//		while (salt.length() < 18) {
//			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//			salt.append(SALTCHARS.charAt(index));
//		}
//		String saltStr = salt.toString();
//		return saltStr;
//	}
//}
