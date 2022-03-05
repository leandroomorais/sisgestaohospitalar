package com.ifrn.sisgestaohospitalar.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Datas {

	public int getIdade(LocalDate dtNascimento, LocalDate dataAtendimento) {
		return Period.between(dtNascimento, dataAtendimento).getYears();
	}

	public DateTimeFormatter competencia() {
		return DateTimeFormatter.ofPattern("yyyyMM");
	}

	public DateTimeFormatter dataBpa() {
		return DateTimeFormatter.ofPattern("yyyyMMdd");
	}

}
