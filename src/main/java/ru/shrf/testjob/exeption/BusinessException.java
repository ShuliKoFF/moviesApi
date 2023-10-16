package ru.shrf.testjob.exeption;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
