package com.ifrn.sisgestaohospitalar.validator;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CnsValidatorTest {

	@Test
	void testIsValid() {
		String cns = "135.7033.5467.0005";
		CnsValidator cnsValidator = new CnsValidator();
		boolean atual = cnsValidator.isValid(cns, null);
		assertTrue(atual);
	}

}
