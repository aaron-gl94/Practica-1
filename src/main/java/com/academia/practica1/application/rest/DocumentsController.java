package com.academia.practica1.application.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DocumentsController implements IDocumentsController {
    
	@ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> uploadImages(
		@RequestParam("ine_anverso")	MultipartFile ineAnverso,
		@RequestParam("ine_reverso")	MultipartFile ineReverso,
		@RequestParam("selfie")			MultipartFile selfie
		// @RequestParam(value="testOpcional", required = false) MultipartFile optional
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Imágenes cargadas exitosamente.");
    }
    
}