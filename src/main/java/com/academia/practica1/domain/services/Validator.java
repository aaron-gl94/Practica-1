package com.academia.practica1.domain.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.academia.practica1.domain.exceptions.FileExceptions;
import com.academia.practica1.domain.interfaces.IValidator;

// import lombok.extern.slf4j.Slf4j; @Slf4j

public class Validator implements IValidator {
	
	@Override	
	public String[] fileValidation(String fileName) throws Exception {
		
		this.fileNameRegexValidation(fileName);
		this.fileExtensionValidation(fileName);
		
		// Separando nomenclatura del archivo
		String[]	fileNameSlice	= this.getNameSlices(fileName);
		
		String		fileDateString	= fileNameSlice[2].substring(0, fileNameSlice[2].indexOf("."));
		
		String		fileUuid 		= fileUUIDValidation(fileNameSlice[0]);
		String		fileCustomer	= fileCustomerDocValidation(fileNameSlice[1]);
		String 		fileDate		= fileDateReviewValidation(fileDateString);
		
		return new String[] {fileUuid, fileCustomer, fileDate};
		
	}
	
	private void fileNameRegexValidation(String filename) throws FileExceptions {
		Pattern patron	= Pattern.compile(IValidator.fileRegex);
	    Matcher matcher = patron.matcher(filename);
	    
	    Boolean result	= matcher.matches();
	    
	    if (Boolean.FALSE.equals(result)) {
            throw new FileExceptions("El nombre del archivo no cumple con el patrón indicado.");
        }
	}
	
	private String[] getNameSlices(String fileName) throws FileExceptions {
		
		String[] filenameSplices = fileName.split("_");
		
		if (filenameSplices.length == 3) {			
			return filenameSplices;
		} else {
			throw new FileExceptions("El nombre del archivo no fue segmentado correctamente.");
		}
	}
	
	
	private Boolean fileExtensionValidation(String fileName) {
	  Set<String> validExtensions = new HashSet<>(Arrays.asList("jpg", "jpeg", "png"));
	  
	  String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	  
	  return validExtensions.contains(extension);
	}
	
	private static String fileUUIDValidation(String fileName) throws FileExceptions {
		
		try {
			return UUID.fromString(fileName).toString();
		} catch(IllegalArgumentException e) {
			throw new FileExceptions("El UUID es Invalido."+e);
		}
		
	}
	
	private static String fileCustomerDocValidation(String filename) throws FileExceptions {
	    Set<String> validDocs = new HashSet<>(Arrays.asList("anverso", "reverso", "selfie"));

	    if (validDocs.contains(filename)) {
	        return filename;
	    } else {
	        throw new FileExceptions("Tipo de documento inválido: " + filename);
	    }
	}
	
	private static String fileDateReviewValidation(String fileDate) throws FileExceptions {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(false);						// Desactiva la interpretación flexible de fechas

	    try {
	        Date parsedDate = sdf.parse(fileDate);	// Intenta parsear la fecha
	        return sdf.format(parsedDate);			// Si se parsea correctamente, regresa la fecha formateada
	    } catch (ParseException e) {
	        throw new FileExceptions("Fecha del nombre de documento inválida."); // Si falla, lanza la excepción personalizada
	    }
	}
}