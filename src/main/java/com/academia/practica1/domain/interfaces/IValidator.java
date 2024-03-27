package com.academia.practica1.domain.interfaces;

import java.util.Date;
import java.util.UUID;

public interface IValidator {
	String 		fileRegex		= "^([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})(?:_(anverso|reverso|selfie))?_([0-9]{4}-[0-9]{2}-[0-9]{2})\\.(jpg|jpeg|png)$";
    
    UUID		fileUuid		= null;
    String		fileName		= null;
    Date		fileDate		= null;
    String		fileCustomer	= null;
    String		fileExtension	= null;
    
	String[]	fileValidation(String fileName) throws Exception;
}