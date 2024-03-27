package com.academia.practica1.domain.interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.academia.practica1.domain.exceptions.FileExceptions;

public interface IManagerFile {
	String[] uploadImage(MultipartFile file) throws FileExceptions, Exception;
}
