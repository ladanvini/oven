package com.vini.oven.exceptions;

public class MyCustomInternalExceptions extends Exception {
    private String errorCode;
    private String message;

    public MyCustomInternalExceptions() {
	super();
	this.errorCode = "generic";
	this.message = "generic internal exception.";
    }

    public MyCustomInternalExceptions(String message, String errorCode) {
	super(message);
	this.errorCode = errorCode;
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
    }
}
