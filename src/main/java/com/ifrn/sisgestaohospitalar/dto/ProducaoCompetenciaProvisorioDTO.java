package com.ifrn.sisgestaohospitalar.dto;

import java.math.BigDecimal;
import java.util.List;


public class ProducaoCompetenciaProvisorioDTO {

	List<InfoProducaoDTO> infoProducaoDTOs;

	private BigDecimal valorTotal;

	public List<InfoProducaoDTO> getInfoProducaoDTOs() {
		return infoProducaoDTOs;
	}

	public void setInfoProducaoDTOs(List<InfoProducaoDTO> infoProducaoDTOs) {
		this.infoProducaoDTOs = infoProducaoDTOs;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
