package com.github.bifrurcated.hibernatesecondlevelcache.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionApiAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleException(ResponseStatusException e) {
        return ResponseEntity
            .status(e.getStatusCode())
            .body(e.getMessage());
    }
}
