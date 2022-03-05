package com.ifrn.sisgestaohospitalar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.Atendimento;
import com.ifrn.sisgestaohospitalar.model.AtendimentoProcedimento;
import com.ifrn.sisgestaohospitalar.model.Cidadao;
import com.ifrn.sisgestaohospitalar.model.Endereco;
import com.ifrn.sisgestaohospitalar.model.LinhaBPAIndividualizado;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.model.Profissional;
import com.ifrn.sisgestaohospitalar.repository.EstabelecimentoRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoDetalheRepository;
import com.ifrn.sisgestaohospitalar.utils.Datas;

@Service
public class LinhaBPAIndividualizadoService {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	@Autowired
	private ProcedimentoDetalheRepository procedimentoDetalheRepository;
	@Autowired
	private ProcedimentoOcupacapService procedimentoOcupacapService;

	Datas datas = new Datas();

	public List<LinhaBPAIndividualizado> getLinhasBPAIndividualizado(
			List<AtendimentoProcedimento> procedimentosIndividualizados) {
		List<LinhaBPAIndividualizado> linhasBPAIndividualizado = new ArrayList<>();
		String CNES = getCnes();
		procedimentosIndividualizados.stream().forEach(a -> {
			LinhaBPAIndividualizado linhaBPAIndividualizado = new LinhaBPAIndividualizado();
			Atendimento ATENDIMENTO = a.getAtendimento();
			Cidadao DADOS_CIDADAO = a.getAtendimento().getCidadao();
			Endereco ENDERECO_CIDADAO = a.getAtendimento().getCidadao().getEndereco();
			Profissional PROFISSIONAL = a.getProfissional();
			linhaBPAIndividualizado.setLinhaIdenti("3");
			linhaBPAIndividualizado.setCnes(CNES);
			linhaBPAIndividualizado.setCompetencia(ATENDIMENTO.getDataEntrada().format(datas.competencia()));
			linhaBPAIndividualizado.setCnsProfissional(PROFISSIONAL.getCns());
			linhaBPAIndividualizado.setCboProfissional(procedimentoOcupacapService.getOcupacao(PROFISSIONAL));
			linhaBPAIndividualizado.setDataAtendimento(ATENDIMENTO.getDataEntrada().format(datas.dataBpa()));
			linhaBPAIndividualizado.setCodigoProcedimento(getCodigoProcedimento(a.getProcedimento().getCodigo()));
			linhaBPAIndividualizado.setCnsPaciente(DADOS_CIDADAO.getCns());
			linhaBPAIndividualizado.setSexoPaciente(DADOS_CIDADAO.getSexo());
			linhaBPAIndividualizado.setCodigoIbge(ENDERECO_CIDADAO.getMunicipio().getCodigoIBGE().toString());
			if (exigeCid(a.getProcedimento())) {
				linhaBPAIndividualizado.setCid(a.getCodigoCid());
			}
			linhaBPAIndividualizado.setCid("");
			linhaBPAIndividualizado.setIdade(getIdade(DADOS_CIDADAO.getDataNascimento(), ATENDIMENTO.getDataEntrada()));
			linhaBPAIndividualizado.setQtdProcedimento(Integer.toString(a.getQuantidade()));
			linhaBPAIndividualizado
					.setCaraterAtendimento(Integer.toString(ATENDIMENTO.getCaraterAtendimento().getCodigo()));
			linhaBPAIndividualizado.setOrigemInformacao("BPA");
			linhaBPAIndividualizado.setNumeroAutorizacao("");
			linhaBPAIndividualizado.setNomePaciente(DADOS_CIDADAO.getNome());
			linhaBPAIndividualizado.setDataNascimento(DADOS_CIDADAO.getDataNascimento().format(datas.dataBpa()));
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
		});
		return linhasBPAIndividualizado;

	}

	private String getCnes() {
		return estabelecimentoRepository.findAll().get(0).getCnes();
	}

	private String getCodigoProcedimento(Long codigoProcedimento) {
		return "0" + codigoProcedimento.toString();
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
