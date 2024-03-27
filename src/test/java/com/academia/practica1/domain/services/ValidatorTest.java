package com.academia.practica1.domain.services;

import static org.junit.jupiter.api.Assertions.*;

// BORRAR ESTA LINEA [import static org.junit.jupiter.api.Assertions.*];

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.academia.practica1.domain.exceptions.FileExceptions;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {
	
	// Inyecta el objeto para preparar 	su ejecución en Test. 
	@InjectMocks
	Validator validator;
	
	@Test
	void when_fileValidation_thenOk() throws Exception {
		assertDoesNotThrow(()-> {
			String[] result = validator.fileValidation("002d2cd0-5bc5-4228-95ff-fdc77be1d925_anverso_2024-03-15.jpg");
			
			assertEquals("002d2cd0-5bc5-4228-95ff-fdc77be1d925", result[0]);
			assertEquals("anverso", result[1]);
			assertEquals("2024-03-15", result[2]);
		});
	}
	
	@Test
	void when_fileValidation_thenUUID_Error() {
		assertThrows(FileExceptions.class, () -> validator.fileValidation("002d2cd0-5bc5-422s8-95ff-fdc77be1d925_anverso_2024-02-13.jpg"));
	}
	
	@Test
	void when_fileValidation_then_CustomerDoc_Error() {
		assertThrows(FileExceptions.class, () -> validator.fileValidation("002d2cd0-5bc5-4228-95ff-fdc77be1d925_otro_2024-02-13.jpg"));
	}
	
	@Test
	void when_fileValidation_then_fileDate_Error() {
		assertThrows(FileExceptions.class, () -> validator.fileValidation("002d2cd0-5bc5-4228-95ff-fdc77be1d925_selfie_2024-02-31.jpg"));
	}
}
