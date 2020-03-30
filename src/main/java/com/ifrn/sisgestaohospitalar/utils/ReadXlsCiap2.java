package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifrn.sisgestaohospitalar.model.Ciap2;
import com.ifrn.sisgestaohospitalar.repository.Ciap2Repository;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * Realiza a leitura do arquivo XLS contendo a tabela Ciap2 (Classificação
 * Internacional da Atenção Primária) usando a API JXL e persite os dados no
 * Banco de Dados
 * 
 * @param urlFile
 * @throws BiffException
 * @throws IOException
 */

@Service
public class ReadXlsCiap2 {

	@Autowired
	private Ciap2Repository ciap2Repository;

	public void readAndSaveCiap2(String urlFile) throws BiffException, IOException {

		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("Cp1252");

		Workbook workbook = Workbook.getWorkbook(new File(urlFile), workbookSettings);

		Sheet sheet = workbook.getSheet(0);
		int lines = sheet.getRows();

		System.out.println("Iniciando a Leitura da Planilha CIAP2");

		for (int i = 0; i < lines; i++) {

			Cell c1 = sheet.getCell(0, i);
			Cell c3 = sheet.getCell(2, i);

			String codeciap = c1.getContents().toUpperCase();
			String title = c3.getContents().toUpperCase();

			Ciap2 ciap2 = new Ciap2();

			ciap2.setCodeciap2(codeciap);
			ciap2.setTitle(title);

			ciap2Repository.saveAndFlush(ciap2);
		}

	}

}
