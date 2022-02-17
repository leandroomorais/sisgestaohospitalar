package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Endereco;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.Lotacao;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoDetalheRepository;

@Service
public class LinhaBPAIndividualizadoService {

	@Autowired
	private ArquivoBPAService arquivoBPAService;
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired
	private ProcedimentoDetalheRepository procedimentoDetalheRepository;

	DateTimeFormatter formaterCompetencia = DateTimeFormatter.ofPattern("yyyyMM");
	DateTimeFormatter formaterYYYYmmAA = DateTimeFormatter.ofPattern("yyyyMMdd");

	public List<LinhaBPAIndividualizado> getLinhasBPAIndividualizado(String mes) {
		List<LinhaBPAIndividualizado> linhasBPAIndividualizado = new ArrayList<>();
		arquivoBPAService.filtraProcedimentos(arquivoBPAService.getAtendimentoProcedimentos(mes));
		for (AtendimentoProcedimento atendimentoProcedimento : arquivoBPAService.getProcedimentosIndividualizados()) {
			LinhaBPAIndividualizado linhaBPAIndividualizado = new LinhaBPAIndividualizado();

			Atendimento ATENDIMENTO = atendimentoProcedimento.getAtendimento();
			Cidadao DADOS_CIDADAO = atendimentoProcedimento.getAtendimento().getCidadao();
			Endereco ENDERECO_CIDADAO = atendimentoProcedimento.getAtendimento().getCidadao().getEndereco();
			Profissional PROFISSIONAL = atendimentoProcedimento.getProfissional();

			linhaBPAIndividualizado.setLinhaIdenti("3");
			linhaBPAIndividualizado.setCnes(getCnes());
			linhaBPAIndividualizado.setCompetencia(ATENDIMENTO.getDataEntrada().format(formaterCompetencia));
			linhaBPAIndividualizado.setCnsProfissional(PROFISSIONAL.getCns());
			linhaBPAIndividualizado.setCboProfissional(getCboProfissional(PROFISSIONAL));
			linhaBPAIndividualizado.setDataAtendimento(ATENDIMENTO.getDataEntrada().format(formaterYYYYmmAA));
			linhaBPAIndividualizado.setCodigoProcedimento(
					getCodigoProcedimento(atendimentoProcedimento.getProcedimento().getCodigo()));
			linhaBPAIndividualizado.setCnsPaciente(DADOS_CIDADAO.getCns());
			linhaBPAIndividualizado.setSexoPaciente(DADOS_CIDADAO.getSexo());
			linhaBPAIndividualizado.setCodigoIbge(ENDERECO_CIDADAO.getMunicipio().getCodigoIBGE().toString());
			if (exigeCid(atendimentoProcedimento.getProcedimento())) {
				linhaBPAIndividualizado.setCid(atendimentoProcedimento.getCodigoCid());
			}
			linhaBPAIndividualizado.setCid("");
			linhaBPAIndividualizado.setIdade(getIdade(DADOS_CIDADAO.getDataNascimento(), ATENDIMENTO.getDataEntrada()));
			linhaBPAIndividualizado.setQtdProcedimento(Integer.toString(atendimentoProcedimento.getQuantidade()));
			linhaBPAIndividualizado
					.setCaraterAtendimento(Integer.toString(ATENDIMENTO.getCaraterAtendimento().getCodigo()));
			linhaBPAIndividualizado.setOrigemInformacao("BPA");
			linhaBPAIndividualizado.setNumeroAutorizacao("");
			linhaBPAIndividualizado.setNomePaciente(DADOS_CIDADAO.getNome());
			linhaBPAIndividualizado.setDataNascimento(DADOS_CIDADAO.getDataNascimento().format(formaterYYYYmmAA));
			linhaBPAIndividualizado.setRacaCor(Integer.toString(DADOS_CIDADAO.getCodigoRaca().getCodigo()));
			linhaBPAIndividualizado.setEtnia("");
			linhaBPAIndividualizado.setNacionalidade(Integer.toString(DADOS_CIDADAO.getCodigoNacionalidade()));
			linhaBPAIndividualizado.setCodigoServico("");
			linhaBPAIndividualizado.setCodigoClassificacao("");
			linhaBPAIndividualizado.setCodigoSequenciaEquipe("");
			linhaBPAIndividualizado.setCodigoAreaEquipe("");
			linhaBPAIndividualizado.setCodigoCnpjEmpresa("");
			linhaBPAIndividualizado.setCepPaciente(ENDERECO_CIDADAO.getCep());
			linhaBPAIndividualizado.setLogradouroPaciente(Long.toString(ENDERECO_CIDADAO.getLogradouro().getCodigo()));
			linhaBPAIndividualizado.setEnderecoPaciente(ENDERECO_CIDADAO.getNomeLogradouro());
			linhaBPAIndividualizado.setComplementoEndereco(ENDERECO_CIDADAO.getComplemento());
			linhaBPAIndividualizado.setNumeroEndereco(ENDERECO_CIDADAO.getNumero());
			linhaBPAIndividualizado.setBairroEndereco(ENDERECO_CIDADAO.getBairro());
			linhaBPAIndividualizado.setTelefonePaciente(DADOS_CIDADAO.getTelefone());
			linhaBPAIndividualizado.setEmailPaciente(DADOS_CIDADAO.getEmail());
			linhaBPAIndividualizado.setIdentificacaoEquipe("");
			linhaBPAIndividualizado.setFim("");
			linhasBPAIndividualizado.add(linhaBPAIndividualizado);
		}
		return linhasBPAIndividualizado;
	}

	private String getCnes() {
		return estabelecimentoRepository.findAll().get(0).getCnes();
	}

	private String getCodigoProcedimento(Long codigoProcedimento) {
		return "0" + codigoProcedimento.toString();
	}

	private String getCboProfissional(Profissional profissional) {
		String cboProfissional = null;
		for (Lotacao lotacao : profissional.getLotacoes()) {
			if (lotacao.getCnes().equals(getCnes())) {
				cboProfissional = lotacao.getCodigoCBO();
			}
		}
		return cboProfissional;
	}

	private String getIdade(LocalDate nascimentoPaciente, LocalDateTime dataAtendimento) {
		int periodo = Period.between(nascimentoPaciente, dataAtendimento.toLocalDate()).getYears();
		return Integer.toString(periodo);
	}

	private boolean exigeCid(Procedimento procedimento) {
		if (procedimentoDetalheRepository.exigeCid(procedimento.getCodigo()) != null) {
			return true;
		}
		return false;
	}
}
