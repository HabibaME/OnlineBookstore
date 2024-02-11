package com.example.OnlineBookstore.error;

public class ApiRequestException extends RuntimeException {
    public ApiRequestException() {
        super();
    }

    public ApiRequestException(String message) {
        super(message);
    }
}
