package com.ifrn.sisgestaohospitalar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 * A classe <code>Role</code> representa os objetos do tipo Role (Regras -
 * Permissões de Acesso), seus atributos e a implementação de Métodos da
 * Interface GrantedAuthority do Spring Security .
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */
@Entity
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
	
	/**Getters and setters*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
