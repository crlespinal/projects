package com.celcsa.payroll.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason ="Shift exist.")
public class ShiftExistException extends RuntimeException {

    /**
     *
     */

    private static final long serialVersionUID = 1L;

    public ShiftExistException() {
        super();
    }

    public ShiftExistException(String message) {
        super(message);
    }

    public ShiftExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
