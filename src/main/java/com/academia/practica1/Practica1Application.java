package com.academia.practica1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica1Application {
	
	
	/* CREAR:
	 * capa de dominio
	 * capa de infraestructura
	 * capa de aplicación 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * Actividad: Subir imagen (ine{reverso, frontal}, selfi) y guardar en bd.
	 * 
	 * formato archivo: uuid_Anverso_ddmmaaaa.png
	 * 
	 * Reglas:
	 *  - formato de documento para poder subir 32 car, "anverso" y fecha valida.
	 *  - no generar id secuencial, usar mejor UUID.
	 *  - validar la existencia de estos documentos por id de usuario.
	 *  - agregar fecha en que se usa (formato de fecha a elegir).
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Practica1Application.class, args);
	}
	
	//		
	//		Validator validator = new Validator();
	//		
	//		String[] responseAnverso = validator.fileValidation(UUID.randomUUID()+"_anverso_2024-03-15.jpg");
	//		System.out.print("\n\n Response [INE Anverso]: "+responseAnverso);
	//
	//		String[] responseReverso = validator.fileValidation(UUID.randomUUID()+"_reverso_2024-03-15.jpg");
	//		System.out.print("\n\n Response [INE Reverso]: "+responseReverso);
	//
	//		String[] responseSelfie = validator.fileValidation(UUID.randomUUID()+"_selfie_2024-03-15.jpg");
	//		System.out.print("\n\n Response [Selfie]: "+responseSelfie);
	//	}
}
