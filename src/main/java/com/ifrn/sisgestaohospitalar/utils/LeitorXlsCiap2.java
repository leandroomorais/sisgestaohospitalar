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
 * A classe <code>LeitorXlsCiap2</code> é um utilitário que contém métodos para
 * a leitura e persistência de dados dos arquivos XLS da Tabela de Classificação
 * Internacional da Atenção Primária - Ciap2.
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

@Service
public class LeitorXlsCiap2 {

	@Autowired
	private Ciap2Repository ciap2Repository;

	/**
	 * Este método realiza a leitura do arquivo XLS da Tabela de Classificação
	 * Internacional da Atenção Primária - Ciap2 e preenche a tabela Ciap2 do Banco
	 * de Dados
	 * 
	 * @param urlFile
	 * @throws BiffException
	 * @throws IOException
	 */
	public void lerCiap2(String urlFile) throws BiffException, IOException {

		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding("Cp1252");

		Workbook workbook = Workbook.getWorkbook(new File(urlFile), workbookSettings);

		Sheet sheet = workbook.getSheet(0);
		int lines = sheet.getRows();

		System.out.println("Iniciando a Leitura da Planilha CIAP2");

		for (int i = 0; i < lines; i++) {
			Cell c3 = sheet.getCell(2, i);

			String tituloOriginal = c3.getContents().toUpperCase();

			Ciap2 ciap2 = new Ciap2();

			ciap2.setTitulo(tituloOriginal);

			ciap2Repository.saveAndFlush(ciap2);
		}

	}

}
