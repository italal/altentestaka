package com.alten.ecomapp.back.web.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

@SuppressWarnings("java:S110") // Inheritance tree of classes should not be too deep
public class InvalidPasswordException extends ErrorResponseException {

    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(
            HttpStatus.BAD_REQUEST,
                ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value()),
            null
        );
    }
}
