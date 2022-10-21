package com.depromeet.threedays.front.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice(basePackages = "com.depromeet.threedays.front.controller")
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        log.error("handleResourceNotFoundException", resourceNotFoundException);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
