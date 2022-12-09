package ru.ae.coursemodel.exeption;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
