package com.ifrn.sisgestaohospitalar.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.stereotype.Component;
import com.ifrn.sisgestaohospitalar.model.ArquivoBPA;

@Component
public class EscritorTXT {

	public File geraArquivo(ArquivoBPA arquivoBPA) {
		String path = System.getProperty("user.dir") + "/Bpa/";

		try {
			File file = new File(path + "PA" + arquivoBPA.getCnes() + extensaoDoArquivo(arquivoBPA.getCompetencia()));
			FileWriter writer = new FileWriter(file, false);
			PrintWriter printWriter = new PrintWriter(writer);
			printWriter.println(arquivoBPA.toString());
			arquivoBPA.getFolhasBPAConsolidado().forEach(f -> {
				f.getLinhasBPAConsolidado().forEach(l -> {
					printWriter.println(l.toString());
				});
			});
			arquivoBPA.getFolhasBPAIndividualizado().forEach(f -> {
				f.getLinhasBPAIndividualizado().forEach(l -> {
					printWriter.println(l.toString());
				});
			});
			printWriter.flush();
			printWriter.close();

			return file;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String extensaoDoArquivo(String competencia) {
		String mesCompetencia = competencia.substring(4, 6);
		if (mesCompetencia.equals("01")) {
			return ".JAN";
		} else if (mesCompetencia.equals("02")) {
			return ".FEV";
		} else if (mesCompetencia.equals("03")) {
			return ".MAR";
		} else if (mesCompetencia.equals("04")) {
			return ".ABR";
		} else if (mesCompetencia.equals("05")) {
			return "MAI";
		} else if (mesCompetencia.equals("06")) {
			return ".JUN";
		} else if (mesCompetencia.equals("07")) {
			return ".JUL";
		} else if (mesCompetencia.equals("08")) {
			return ".AGO";
		} else if (mesCompetencia.equals("09")) {
			return ".SET";
		} else if (mesCompetencia.equals("10")) {
			return ".OUT";
		} else if (mesCompetencia.equals("11")) {
			return ".NOV";
		} else if (mesCompetencia.equals("12")) {
			return ".DEZ";
		} else
			return ".txt";
	}

}
