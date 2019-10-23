package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason ="Company Exist.")
public class CompanyExistException extends RuntimeException {
    
    /**
     *
     */

    private static final long serialVersionUID = 1L;

    public CompanyExistException() {
        super();
    }

    public CompanyExistException(String message) {
        super(message);
    }

    public CompanyExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
