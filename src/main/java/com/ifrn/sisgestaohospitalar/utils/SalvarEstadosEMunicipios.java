package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.ifrn.sisgestaohospitalar.model.Cep_Ibge;
import com.ifrn.sisgestaohospitalar.model.Estado;
import com.ifrn.sisgestaohospitalar.model.Municipio;
import com.ifrn.sisgestaohospitalar.repository.Cep_IbgeRepository;
import com.ifrn.sisgestaohospitalar.repository.EstadoRepository;
import com.ifrn.sisgestaohospitalar.repository.MunicipioRepository;

public class SalvarEstadosEMunicipios {

	public void lerCSV(MunicipioRepository municipioRepository, EstadoRepository estadoRepository) {
		String arquivoCSVURL = System.getProperty("user.dir")
				+ "/src/main/resources/estados_municipios/listaMunicipiosBrasil.csv";
		BufferedReader bufferedReader = null;
		String linha = "";
		String csvDivisor = ";";

		try {
			bufferedReader = Files.newBufferedReader(Paths.get(arquivoCSVURL), Charset.forName("ISO-8859-1"));
			while ((linha = bufferedReader.readLine()) != null) {
				String[] municipioArquivo = linha.split(csvDivisor);
				Municipio municipio = new Municipio();
				Estado estado = new Estado();

				estado = estadoRepository.findBysiglaUF(municipioArquivo[3]);
				if (municipioArquivo[3].equals(estado.getSiglaUF())) {
					municipio.setCodigoIBGE(Long.parseLong(municipioArquivo[1]));
					municipio.setCodigoIBGE7(Long.parseLong(municipioArquivo[2]));
					municipio.setNomeMunicipio(municipioArquivo[4].toUpperCase());
					municipio.setNomeMunicipioSiglaUF(
					municipioArquivo[4].toUpperCase() + "-" + municipioArquivo[3].toUpperCase());
					municipio.setEstado(estado);
					municipioRepository.save(municipio);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void lerCSVEstados(EstadoRepository estadoRepository) {
		String arquivoCSVEstadoURL = System.getProperty("user.dir")
				+ "/src/main/resources/estados_municipios/Lista_EstadosBrasileiros.CSV";
		BufferedReader bufferedReader = null;
		String linha = "";
		String csvDivisor = ";";
		List<Estado> estados = new ArrayList<Estado>();
		try {
			bufferedReader = Files.newBufferedReader(Paths.get(arquivoCSVEstadoURL), Charset.forName("ISO-8859-1"));
			while ((linha = bufferedReader.readLine()) != null) {
				String[] estadoArquivo = linha.split(csvDivisor);
				Estado estado = new Estado();
				estado.setCodigoUF(Integer.parseInt(estadoArquivo[0]));
				estado.setSiglaUF(estadoArquivo[1].toUpperCase());
				estado.setNomeUF(estadoArquivo[2].toUpperCase());
				estados.add(estado);
			}
			estadoRepository.saveAll(estados);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void lerCEP_Codigo(Cep_IbgeRepository cepIbgeRepository) {
		String arquivoCSVCepCodigo = System.getProperty("user.dir")
				+ "/src/main/resources/estados_municipios/S_CEP.CSV";
		BufferedReader bufferedReader = null;
		String linha = "";
		String csvDivisor = ";";
		List<Cep_Ibge> cep_Ibges = new ArrayList<Cep_Ibge>();
		try {
			bufferedReader = Files.newBufferedReader(Paths.get(arquivoCSVCepCodigo), Charset.forName("ISO-8859-1"));
			while ((linha = bufferedReader.readLine()) != null) {
				String[] cepArquivo = linha.split(csvDivisor);

				Cep_Ibge relCepIbge = new Cep_Ibge();
				relCepIbge.setCep(Long.parseLong(cepArquivo[0]));
				relCepIbge.setCdIbge(Long.parseLong(cepArquivo[1]));
				cep_Ibges.add(relCepIbge);
			}
			cepIbgeRepository.saveAll(cep_Ibges);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
