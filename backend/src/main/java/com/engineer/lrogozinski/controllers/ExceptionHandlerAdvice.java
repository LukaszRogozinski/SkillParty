package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.exceptions.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.json.JSONObject;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity handleException(DataAccessException e) {
        final JSONObject json = new JSONObject().put("JPA Persistence exception: ", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(json.toString());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleException(ServiceException e) {
        final JSONObject json = new JSONObject().put("ServiceException: ", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(json.toString());
    }

    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    public ResponseEntity handleException(org.springframework.security.authentication.BadCredentialsException e){
        final JSONObject json = new JSONObject().put("BadCredentialsException", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(json.toString());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleException(EmptyResultDataAccessException e) {
        final JSONObject json = new JSONObject().put("Expected result not found", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(json.toString());
    }

}