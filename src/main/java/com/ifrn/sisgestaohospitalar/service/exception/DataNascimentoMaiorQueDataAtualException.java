package com.ifrn.sisgestaohospitalar.service.exception;

public class DataNascimentoMaiorQueDataAtualException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataNascimentoMaiorQueDataAtualException(String message) {
		super(message);
	}

}
