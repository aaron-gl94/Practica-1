package com.academia.practica1.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.academia.practica1.domain.exceptions.FileExceptions;

@ExtendWith(MockitoExtension.class)
class FileManagerTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private FileManager fileManager;

    @Test
    void when_uploadImage_isValid_returnFileDetailsExpected() throws Exception {
        String fileName = "002d2cd0-5bc5-4228-95ff-fdc77be1d925_anverso_2024-03-14.jpeg";
        
        String[] expectedFileDetails = {
        	"002d2cd0-5bc5-4228-95ff-fdc77be1d925",
        	"anverso",
        	"2024-03-14"
        };
        
        MultipartFile fileMock = mock(MultipartFile.class);
        when(fileMock.getName()).thenReturn(fileName);
    	when(fileMock.isEmpty()).thenReturn(false);
    	when(fileMock.getContentType()).thenReturn("image/jpeg");
    	// Stub de Validator [Object behavior]
    	when(validator.fileValidation(fileName)).thenReturn(expectedFileDetails);
    	    	
        assertDoesNotThrow(() -> {
        	String[] actualFileDetails = fileManager.uploadImage(fileMock);
        	
        	assertArrayEquals(expectedFileDetails, actualFileDetails);
        	verify(validator).fileValidation(fileName);
        });   
    }

    @Test
    void when_uploadImage_FileIsEmpty_throwFileExceptions() throws Exception {
        MultipartFile fileMock = mock(MultipartFile.class);
        when(fileMock.isEmpty()).thenReturn(true);

        assertThrows(FileExceptions.class, () -> fileManager.uploadImage(fileMock));
        verify(validator, never()).fileValidation(anyString()); // Verifica que validator no sea llamado
    }

    @Test
    void when_uploadImage_FileIsNotImage_throwFileExceptions() throws Exception {
        String fileName = "file_anverso_2024-03-14.txt";
        MultipartFile fileMock = mock(MultipartFile.class);
        when(fileMock.getName()).thenReturn(fileName);
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getContentType()).thenReturn("text/plain");

        assertThrows(FileExceptions.class, () -> fileManager.uploadImage(fileMock));
        verify(validator, never()).fileValidation(anyString()); // Verifica que validator no sea llamado
    }

    @Test
    void when_uploadImage_validatorThrowsException() throws Exception {
        String fileName = "file_anverso_2024-03-14.jpeg";
        MultipartFile fileMock = mock(MultipartFile.class);
        when(fileMock.getName()).thenReturn(fileName);
        when(fileMock.isEmpty()).thenReturn(false);
        when(fileMock.getContentType()).thenReturn("image/jpeg");
        
        doThrow(new FileExceptions("Error de validación")).when(validator).fileValidation(fileName);

        assertThrows(FileExceptions.class, () -> fileManager.uploadImage(fileMock));
        verify(validator).fileValidation(fileName);
    }
}
