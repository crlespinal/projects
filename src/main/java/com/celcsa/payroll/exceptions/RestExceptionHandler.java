package com.celcsa.payroll.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestExceptionHandler
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = WorkingProjectExistException.class)
    public ResponseEntity<?> handleWorkingProjectExistException(WorkingProjectExistException exception) {

        CustomErrorResponse r = new CustomErrorResponse();
        r.setTimestamp(LocalDateTime.now());
        r.setError(StringUtils.isEmpty(exception.getMessage()) ? "Project Already Exist." : exception.getMessage());
        r.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<?> handleException(InvalidParameterException exception) {

        CustomErrorResponse r = new CustomErrorResponse();
        r.setTimestamp(LocalDateTime.now());
        r.setError(exception.getMessage());
        r.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ShiftConlictException.class)
    public ResponseEntity<?> handleException(ShiftConlictException exception) {

        CustomErrorResponse r = new CustomErrorResponse();
        r.setTimestamp(LocalDateTime.now());
        r.setError(exception.getMessage());
        r.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(r, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<?> handleDefaultException(Exception exception) {

        CustomErrorResponse r = new CustomErrorResponse();
        r.setTimestamp(LocalDateTime.now());
        r.setError("Internal Error");
        r.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}