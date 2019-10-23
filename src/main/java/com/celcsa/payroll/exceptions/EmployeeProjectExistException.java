package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason ="Employee Project Exist.")
public class EmployeeProjectExistException extends RuntimeException {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    public EmployeeProjectExistException() {
        super();
    }

    public EmployeeProjectExistException(String message) {
        super(message);
    }

    public EmployeeProjectExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
