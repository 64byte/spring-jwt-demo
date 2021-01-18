package com.story.demo.user.exception;

public class AlreadyRegisteredUserException extends RuntimeException {

    public AlreadyRegisteredUserException() {
        super();
    }

    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
