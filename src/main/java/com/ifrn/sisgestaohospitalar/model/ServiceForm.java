package com.ifrn.sisgestaohospitalar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.ifrn.sisgestaohospitalar.enums.AttendanceStatus;

/**
 * Classe para os Objetos do tipo ServiceForm que inclui atributos, 
 * métodos e relacionamentos do Boletim de Atendimento do Cidadão Usuário
 * @author leand
 *
 */
@Entity
public class ServiceForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date date;
	
	private String numberregister;
	
	private String responsible;
	
	private AttendanceStatus status;
	
	@ManyToOne
	@JoinTable(name="serviceform_citzenuser")
	public CitizenUser citizenUser;
	
	@ManyToOne
	@JoinTable(name="serviceform_professional")
	private Professional professional;
	
	
	
	

}
