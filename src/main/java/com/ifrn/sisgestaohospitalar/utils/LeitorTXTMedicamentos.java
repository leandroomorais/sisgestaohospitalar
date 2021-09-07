package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifrn.sisgestaohospitalar.model.FormaFarmaceutica;
import com.ifrn.sisgestaohospitalar.model.Medicamento;
import com.ifrn.sisgestaohospitalar.repository.FormaFarmaceuticaRepository;
import com.ifrn.sisgestaohospitalar.repository.MedicamentoRepository;

@Service
public class LeitorTXTMedicamentos {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private FormaFarmaceuticaRepository formaFarmaceuticaRepository;

	public void lerTXTFormaFarmaceutica(String arquivoFormaFarmaceutica) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoFormaFarmaceutica),
				Charset.forName("UTF-8"));
		String linha;
		List<FormaFarmaceutica> formasFarmaceuticas = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String[] formaFarmaceuticaDTO = novaLinha.split(";");
			FormaFarmaceutica formaFarmaceutica = new FormaFarmaceutica();

			// if (formaFarmaceuticaDTO[0] != "" || formaFarmaceuticaDTO[0] != null) {
			formaFarmaceutica.setId(Long.parseLong(formaFarmaceuticaDTO[0]));
			// }
			formaFarmaceutica.setNome(formaFarmaceuticaDTO[1]);
			formaFarmaceutica.setNomeFiltro(formaFarmaceuticaDTO[2]);
			formasFarmaceuticas.add(formaFarmaceutica);
		}
		formaFarmaceuticaRepository.saveAll(formasFarmaceuticas);
	}

	public void lerTXTMedicamentos(String arquivoMedicamento) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoMedicamento),
				Charset.forName("UTF-8"));
		String linha;
		// int i = 0;
		List<Medicamento> medicamentos = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String[] medicamentoDTO = novaLinha.split(";");
			Medicamento medicamento = new Medicamento();

			System.out.println("CODIGO: " + medicamentoDTO[0] + " | Principio ativo: " + medicamentoDTO[1]
					+ " | CONCENTRAÇÃO: " + medicamentoDTO[2] + " | CODIGO FORMA: " + medicamentoDTO[3] + " | FORN.: "
					+ medicamentoDTO[4]);

			// System.out.println("Linha: " + i++ + " | Unidade Fornecimento: " +
			// medicamentoDTO[4]);

			if (medicamentoDTO[0] != "" || medicamentoDTO[0] != null) {
				medicamento.setId(Long.parseLong(medicamentoDTO[0]));
			}
			medicamento.setPrincipioAtivo(medicamentoDTO[1]);
			medicamento.setConcentracao(medicamentoDTO[2]);
			if (medicamentoDTO[3] != "" || medicamentoDTO[3] != null) {
				Optional<FormaFarmaceutica> optional = formaFarmaceuticaRepository
						.findById(Long.parseLong(medicamentoDTO[3]));
				if (optional.isPresent()) {
					medicamento.setFormaFarmaceutica(optional.get());
				}
			}

			medicamento.setUnidadeFornecimento(medicamentoDTO[4]);

			medicamentos.add(medicamento);
		}

		medicamentoRepository.saveAll(medicamentos);

	}

}
