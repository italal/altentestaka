package com.alten.ecomapp.back.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create("EMAIL_ALREADY_USED_TYPE");

    private ErrorConstants() {}
}
