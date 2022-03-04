package com.ifrn.sisgestaohospitalar.utils;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Component;

@Component
public class Datas {

	public int getIdade(LocalDate dtNascimento, LocalDate dataAtendimento) {
		return Period.between(dtNascimento, dataAtendimento).getYears();
	}

}
