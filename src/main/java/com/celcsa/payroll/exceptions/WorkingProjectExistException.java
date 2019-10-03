package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason ="Working Project Exist.")
public class WorkingProjectExistException extends RuntimeException {
    
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    public WorkingProjectExistException() {
        super();
    }

    public WorkingProjectExistException(String message) {
        super(message);
    }

    public WorkingProjectExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
