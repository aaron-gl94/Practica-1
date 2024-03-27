package com.academia.practica1.domain.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.academia.practica1.domain.exceptions.FileExceptions;
import com.academia.practica1.domain.interfaces.IManagerFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager implements IManagerFile {
	
	@Autowired
	private Validator validator;
	
	@Override
	public String[] uploadImage(MultipartFile file) throws FileExceptions {
		
		log.info("uploadImage:filename:{}", file.getName());
		log.info("uploadImage:getContentType:{}", file.getContentType());
		
		String fileName = "";
		
		if (file.isEmpty()) {
			throw new FileExceptions("Archivo no existente.");
		} 
		
		fileName = file.getName();
		isImage(file);
		
		try {
			return validator.fileValidation(fileName);
		} catch(Exception e) {
			throw new FileExceptions("La validación rechazo este archivo.");
		}
    }

    private void isImage(MultipartFile file) throws FileExceptions {    	
    	boolean result = Arrays.asList("image/jpeg", "image/png", "image/gif").contains(file.getContentType());
    	
    	if (!result) {
            throw new FileExceptions("El archivo no es una imagen.");
        }
    }
}
