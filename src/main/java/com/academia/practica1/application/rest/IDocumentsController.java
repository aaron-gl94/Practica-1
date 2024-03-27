package com.academia.practica1.application.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/documents")
public interface IDocumentsController {

	// Anotaciones Swagger
	@Operation(summary = "Carga archivos de tipo imagen.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created"),
	@ApiResponse(responseCode = "400", description = "Bad Request"),
	@ApiResponse(responseCode = "404", description = "Error en la url"),
	@ApiResponse(responseCode = "413", description = "Los archivos son demasido pesados"),
	@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	
	// Anotaciones Spring Boot
	@PostMapping(value="/uploadimages", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED) 			// HTTP 201 - Create
	ResponseEntity<String> uploadImages(
			@RequestParam("ine_anverso") MultipartFile ineAnverso,
			@RequestParam("ine_reverso") MultipartFile ineReverso,
			@RequestParam("selfie") MultipartFile selfie
	);
		
}

/*
 * SIGUIENTES PASOS: 
 *    
 * OK ///// 1.- Crear la implementaión de esta interfaz (controller)
 * 2.- Crear prueba de la implmentación caso exito con mock
 * 3.- Crear exceptionHandler (casos error)
 * 4.- Crear pruebas de casos de error
 * 
 */