package com.ifrn.sisgestaohospitalar.model;

public class Administracao {
	
	private static final String usuario = "#administrador_sgh@2022";
	private static final String senha = "#taboleirogrande_sgh@2022";
	private static final String ipMaquina = "192.168.1.6";
	
	public static String getUsuario() {
		return usuario;
	}
	public static String getSenha() {
		return senha;
	}
	public static String getIpmaquina() {
		return ipMaquina;
	}
}