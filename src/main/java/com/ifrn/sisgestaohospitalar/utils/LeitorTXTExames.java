package com.ifrn.sisgestaohospitalar.utils;

import com.ifrn.sisgestaohospitalar.model.ExameSimplificado;
import com.ifrn.sisgestaohospitalar.model.GrupoExame;
import com.ifrn.sisgestaohospitalar.model.Procedimento;
import com.ifrn.sisgestaohospitalar.repository.ExameSimplificadoRepository;
import com.ifrn.sisgestaohospitalar.repository.GrupoExameRepository;
import com.ifrn.sisgestaohospitalar.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class LeitorTXTExames {

	@Autowired
	private GrupoExameRepository grupoExameRepository;
	@Autowired
	private ExameSimplificadoRepository exameSimplificadoRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	public void lerTXTFormaGrupos(String arquivoGrupos) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoGrupos), Charset.forName("UTF-8"));
		String linha;
		List<GrupoExame> gruposExame = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String[] grupoExameDTO = novaLinha.split(";");
			GrupoExame grupoExame = new GrupoExame();
			System.out.println(grupoExameDTO[0]);
			grupoExame.setId(Long.parseLong(grupoExameDTO[0]));
			grupoExame.setNome(grupoExameDTO[1]);
			gruposExame.add(grupoExame);
		}
		grupoExameRepository.saveAll(gruposExame);
	}

	public void lerTXTExames(String arquivoExames) throws IOException {
		BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(arquivoExames), Charset.forName("UTF-8"));
		String linha;
		List<ExameSimplificado> exameSimplificados = new ArrayList<>();
		while ((linha = bufferedReader.readLine()) != null) {
			String novaLinha = new String(linha.getBytes("UTF-8"));
			String[] exameDTO = novaLinha.split(";");
			ExameSimplificado exameSimplificado = new ExameSimplificado();
			Procedimento procedimento = procedimentoRepository.findByCodigo(Long.parseLong(exameDTO[2]));
			GrupoExame grupoExame = grupoExameRepository.getOne(Long.parseLong(exameDTO[1]));
			exameSimplificado.setNome(exameDTO[0]);
			exameSimplificado.setProcedimentoAssociado(procedimento);
			exameSimplificado.setGrupoExame(grupoExame);
			exameSimplificados.add(exameSimplificado);
		}
		exameSimplificadoRepository.saveAll(exameSimplificados);
	}

	public void atualizaGrupo() {
		for (ExameSimplificado exameSimplificado : exameSimplificadoRepository.findAll()) {
			for (GrupoExame grupoExame : grupoExameRepository.findAll()) {
				if (exameSimplificado.getGrupoExame().getId().equals(grupoExame.getId())) {
					if(grupoExame.getExameSimplificados().isEmpty()){
						grupoExame.getExameSimplificados().add(exameSimplificado);
						grupoExameRepository.saveAndFlush(grupoExame);
					}
				}
			}
		}
	}

}
