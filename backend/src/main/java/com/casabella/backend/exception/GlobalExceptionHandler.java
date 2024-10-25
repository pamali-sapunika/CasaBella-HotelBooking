package com.casabella.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    //For Duplicate entries
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> hanldeDuplicateEntry(DataIntegrityViolationException ex){
        return new ResponseEntity<>("Already Exists", HttpStatus.CONFLICT);
    }

    // For custom @ValidEmail annoation
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ResponseEntity<Map<String, String>> handleEmailValidationExceptions(MethodArgumentNotValidException ex){
    //     Map<String, String> errors = new HashMap<>();

    //     for(FieldError error : ex.getBindingResult().getFieldErrors()){
    //         errors.put(error.getField(), error.getDefaultMessage());
    //     }

    //     return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    // }
    
}
