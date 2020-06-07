package com.ifrn.sisgestaohospitalar.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe <code>Raca</code> é um utilitário que armazena a lista de códigos e
 * raças fornecido pelo ministério da saúde
 * 
 * @author Leandro Morais
 * @version 1.0, 02/11/2019
 *
 */

public class Raca {

	public final static Map<Integer, String> mapCodigoRaca = new HashMap<Integer, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(01, "BRANCA");
			put(02, "PRETA");
			put(03, "PARDA");
			put(04, "AMARELA");
			put(05, "INDÍGENA");
			put(99, "SEM INFORMAÇÃO");
		}

	};

	public final static List<Integer> listaCodigoRaca = new ArrayList<Integer>(mapCodigoRaca.keySet());
	public final static List<String> listaCodigoRacaNome = new ArrayList<String>(mapCodigoRaca.values());

}
