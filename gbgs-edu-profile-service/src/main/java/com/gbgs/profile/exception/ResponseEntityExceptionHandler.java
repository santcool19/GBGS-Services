package com.gbgs.profile.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gbgs.profile.exception.template.ResponseEntityException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ResponseEntityExceptionHandler {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ResponseEntityException> subErrors;

    private ResponseEntityExceptionHandler() {
        timestamp = LocalDateTime.now();
    }

    ResponseEntityExceptionHandler(HttpStatus status) {
        this();
        this.status = status;
    }

    ResponseEntityExceptionHandler(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ResponseEntityExceptionHandler(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}