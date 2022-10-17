package com.PI.API.exception;

import org.springframework.http.HttpStatus;

public class BookingException extends RuntimeException{
    private static final Long serialVersionUID=1L;

    private HttpStatus status;

    private String message;

    public BookingException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BookingException(String message, HttpStatus status, String message1) {
        super();
        this.status = status;
        this.message=message;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
