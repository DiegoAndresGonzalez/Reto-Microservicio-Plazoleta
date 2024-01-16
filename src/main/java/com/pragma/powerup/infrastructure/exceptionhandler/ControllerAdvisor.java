package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.DuplicateDataException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.infrastructure.exception.InvalidRoleException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, ignoredNoDataFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exResponse);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Map<String,String>> handleInvalidRoleEx(
            InvalidRoleException invalidRoleException){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, invalidRoleException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(exResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String,String>> handleInvalidInputEx(
            InvalidInputException invalidInputException){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, invalidInputException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String ,String >> handleDataNotFoundEx(
            DataNotFoundException dataNotFoundException){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, dataNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exResponse);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<Map<String,String>> handleDuplicateEx(
            DuplicateDataException duplicateDataException){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, duplicateDataException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exResponse);
    }

}