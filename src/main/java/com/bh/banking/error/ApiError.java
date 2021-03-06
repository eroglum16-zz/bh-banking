package com.bh.banking.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    public ApiError(HttpStatus status) {
        setCurrentTimestamp();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        setCurrentTimestamp();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiError(HttpStatus status, String message, Throwable ex) {
        setCurrentTimestamp();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    private void setCurrentTimestamp() {
        timestamp = LocalDateTime.now();
    }
}
