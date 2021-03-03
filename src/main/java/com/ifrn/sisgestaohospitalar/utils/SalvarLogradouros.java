package com.ifrn.sisgestaohospitalar.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.ifrn.sisgestaohospitalar.model.Logradouro;
import com.ifrn.sisgestaohospitalar.repository.LogradouroRepository;

public class SalvarLogradouros {
	
	public void lerCsvLogradouro(LogradouroRepository logradouroRepository) {
		String arquivoCSVURL = System.getProperty("user.dir")
				+ "/src/main/resources/logradouros/logradouros.csv";
		BufferedReader bufferedReader = null;
		String linha = "";
		String csvDivisor = ";";

		try {
			bufferedReader = Files.newBufferedReader(Paths.get(arquivoCSVURL), Charset.forName("ISO-8859-1"));
			while ((linha = bufferedReader.readLine()) != null) {
				String[] logradouro = linha.split(csvDivisor);
				Logradouro logradouroObj = new Logradouro();
				logradouroObj.setCodigoLogradouro(Long.parseLong(logradouro[0]));
				logradouroObj.setDescLogradouro(logradouro[1]);
				logradouroRepository.save(logradouroObj);
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
	
}
