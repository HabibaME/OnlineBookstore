package com.example.OnlineBookstore.error;

public class DuplicationRecordException extends RuntimeException {
    public DuplicationRecordException() {
        super();
    }

    public DuplicationRecordException(String message) {
        super(message);
    }
}
