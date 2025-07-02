package com.kauan.crud.crud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionGlobalHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception. Check the Documentation.")
                        .details(ex.getMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerNotFound(NotFoundException ex) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception. Check the Documentation.")
                        .details(ex.getMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErrorExceptionDetails> handlerInternalServerErrorException(InternalServerErrorException ex) {
        return new ResponseEntity<>(
                InternalServerErrorExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Internal Server Error. Check the Documentation.")
                        .details(ex.getMessage())
                        .developerMessage(ex.getClass().getName())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
