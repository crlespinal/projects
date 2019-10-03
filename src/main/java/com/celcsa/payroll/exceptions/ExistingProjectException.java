package com.celcsa.payroll.exceptions;

public class ExistingProjectException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExistingProjectException(){
        super();
    }

    public ExistingProjectException(String message){
        super(message);
    }

    public ExistingProjectException(String message, Throwable cause){
        super(message, cause);
    }

}
