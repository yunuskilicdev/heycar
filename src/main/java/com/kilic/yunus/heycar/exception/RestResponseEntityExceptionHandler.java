package com.kilic.yunus.heycar.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Yunus Kilic
 */
@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {HttpClientErrorException.class})
    protected ResponseEntity<Object> httpClientError(
            RuntimeException ex, WebRequest request) {
        HttpClientErrorException clientErrorException = (HttpClientErrorException) ex;
        String bodyOfResponse = clientErrorException.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), clientErrorException.getStatusCode(), request);
    }

    @ExceptionHandler(value
            = {ConstraintViolationException.class})
    protected ResponseEntity<Object> constraintValidationError(
            RuntimeException ex, WebRequest request) {
        ConstraintViolationException violationException = (ConstraintViolationException) ex;
        String bodyOfResponse = violationException.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleGeneralError(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ErrorMessage.INTERNAL_ERROR_MESSAGE;
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
