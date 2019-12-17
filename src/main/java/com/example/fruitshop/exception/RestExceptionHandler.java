package com.example.fruitshop.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //other exception handlers

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity <String> handleEntityNotFound(
            Exception ex) {
        String message = ex.getMessage();

        return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
    }
}