package com.example.ordermanagerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomerNotFound.class)
    public Object entityNotFoundHandler(CustomerNotFound ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("code", HttpURLConnection.HTTP_NOT_FOUND);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}