package com.ifrn.sisgestaohospitalar.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperService {

	private static final String JASPER_DIRETORIO = "classpath:jasper/";
	private static final String JASPER_PREFIXO = "report-";
	private static final String JASPER_SUFIXO = ".jasper";

	@Autowired
	private Connection connection;

	@Autowired
	ResourceLoader resourceLoader;

	private Map<String, Object> params = new HashMap<>();

	public JasperService() {
		this.params.put("IMG_DIR", JASPER_DIRETORIO.concat("/img/logo.png"));
		this.params.put("REPORT_LOCALE", new Locale("pt", "BR"));
	}

	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}

	public byte[] exportarPDF(String code) {
		byte[] bytes = null;
		try {
			Resource resource = resourceLoader
					.getResource(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
			InputStream stream = resource.getInputStream();
			JasperPrint print = JasperFillManager.fillReport(stream, params, connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (JRException | IOException e) {
			System.out.println(" EXCEPTION :::::::::::::::::::::::::::::;; " + e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}

	public String getJasperDiretorio() {
		return JASPER_DIRETORIO;
	}

	public String getJasperPrefixo() {
		return JASPER_PREFIXO;
	}

	public String getJasperSufixo() {
		return JASPER_SUFIXO;
	}

}
