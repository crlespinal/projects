package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvalidParameterException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidParameterException(){
        super();
    }

    public InvalidParameterException(String message){
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause){
        super(message, cause);
    }
    
}