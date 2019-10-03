package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShiftConlictException extends RuntimeException {

    /**
     *
     */
    
    private static final long serialVersionUID = 1L;

    public ShiftConlictException(){
        super();
    }

    public ShiftConlictException(String message){
        super(message);
    }

    public ShiftConlictException(String message, Throwable cause){
        super(message, cause);
    }

}
